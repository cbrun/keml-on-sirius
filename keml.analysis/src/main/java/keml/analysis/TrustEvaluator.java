package keml.analysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;
import org.javatuples.Pair;

import keml.Conversation;
import keml.ConversationPartner;
import keml.Information;
import keml.InformationLink;
import keml.NewInformation;
import keml.PreKnowledge;
import keml.ReceiveMessage;

// takes a conversation and prepares it for trust analysis, each analysis modifies initial and current trust scores
public class TrustEvaluator {
	// +1 is absolute trust, -1 is absolute distrust, 0 is ignore - no knowledge about that node

	Conversation conv;
	List<String> partners;
	List<ReceiveMessage> receives;  //starting point for work on knowledge part
	List<NewInformation> newInfos;
	List<PreKnowledge> preKnowledge;
	
	int weight;
	
	
	public TrustEvaluator(Conversation conv, int weight) {
		this.conv = conv;
		this.partners = ConversationAnalyser.getPartnerNames(conv); //works as header row
		this.receives = ConversationAnalyser.getReceives(conv);
		this.newInfos = ConversationAnalyser.getNewInfos(receives);
		this.preKnowledge = conv.getAuthor().getPreknowledge();
		
		this.weight = weight;
	}
	
	public static List<Pair<String,Map<String, Float>>> standardTrustConfigurations(EList<ConversationPartner> convoPartners) {
		ArrayList<Pair<String,Map<String, Float>>> res = new ArrayList<>();

		Map<String, Float> valuesPerPartner10 = new HashMap<String, Float>(); // LLM && other 1.0
		Map<String, Float> valuesPerPartner0510 = new HashMap<String, Float>(); // LLM 0.5 | other 1.0
		Map<String, Float> valuesPerPartner1005 = new HashMap<String, Float>(); // LLM 1.0 | other 0.5
		Map<String, Float> valuesPerPartner05 = new HashMap<String, Float>(); // LLM && other 0.5

		for (ConversationPartner cp : convoPartners) {
			valuesPerPartner10.put(cp.getName(), 1.0F);
			valuesPerPartner05.put(cp.getName(), 0.5F);

			if (cp.getName().equals("LLM")) {
				valuesPerPartner0510.put("LLM", 0.5F);
				valuesPerPartner1005.put("LLM", 1.0F);
			} else {
				valuesPerPartner0510.put(cp.getName(), 1.0F);
				valuesPerPartner1005.put(cp.getName(), 0.5F);
			}
		}

		res.add(new Pair<String,Map<String, Float>>("a", valuesPerPartner10));
		res.add(new Pair<String,Map<String, Float>>("b", valuesPerPartner0510));
		res.add(new Pair<String,Map<String, Float>>("c", valuesPerPartner1005));
		res.add(new Pair<String,Map<String, Float>>("d", valuesPerPartner05));

		return res;
	}
	
	public void writeRowAnalysis(String path, List<Pair<String,Map<String, Float>>> trustInPartners, Float authorValue) throws IOException {
		
		WorkbookController wbc = new WorkbookController();
		wbc.initialize(newInfos, preKnowledge);
		
		trustInPartners.forEach(p -> {
			var res = analyse(p.getValue1(), authorValue);
			wbc.addTrusts(res, p.getValue0());
		});
		wbc.write(path);
	}
	
	public void writeSingleAnalysis(String path, Map<String, Float> trustInPartner, Float authorValue) throws IOException {
		
		var res = analyse(trustInPartner, authorValue);
		WorkbookController wbc = new WorkbookController();
		wbc.initialize(newInfos, preKnowledge);
		wbc.addTrusts(res, "Trust");
		wbc.write(path);
	}
	
	public HashMap<Information, Pair<Float, Float>> analyseWithDefault() {
		Float authorValue = 1.0F;
		Map<String, Float> valuesPerPartner = new HashMap<String, Float>();
		partners.forEach(p -> {
			valuesPerPartner.put(p, 1.0F);
		});
		// modify LLM credibility:
		valuesPerPartner.put("LLM", 0.5F);
		return analyse(valuesPerPartner, authorValue);
	}
	
	public HashMap<Information, Pair<Float, Float>> analyse(Map<String, Float> valuePerPartner, Float authorValue) {
		assignInitialTrust(valuePerPartner, authorValue);
		evaluate();
		
		HashMap<Information, Pair<Float, Float>> res = new HashMap<Information, Pair<Float, Float>> ();
		
		preKnowledge.forEach(pre -> res.put(pre, new Pair<Float, Float>(pre.getInitialTrust(), pre.getCurrentTrust())));
		newInfos.forEach(info -> res.put(info, new Pair<Float, Float>(info.getInitialTrust(), info.getCurrentTrust())));
		
		return res;
	}
	
	public void assignInitialTrust(Map<String, Float> valuePerPartner, Float authorValue) {
		preKnowledge.forEach(p -> {
			p.setInitialTrust(authorValue);
			p.setCurrentTrust(authorValue);
		});
		newInfos.forEach(info -> {
			Float v = valuePerPartner.get(info.getSourceConversationPartner().getName());
			info.setInitialTrust(v);
			info.setCurrentTrust(v);
		});
	}
	
	public void evaluate() {
		HashSet<Information> toVisit = Stream.concat(preKnowledge.stream(), newInfos.stream()).collect(Collectors.toCollection(HashSet::new));
		//List<Information> toVisit = Stream.concat(preKnowledge.stream(), newInfos.stream()).toList().reversed();// start with last time stamp
		
		while (toVisit.size()>0) {
			int remaining = toVisit.size();
			for (Iterator<Information> i = toVisit.iterator(); i.hasNext(); ) {
				 Information info = i.next();
				 if (ready(info, toVisit)) {
					 info.setCurrentTrust(currentNodeTrust(info));
					 i.remove();
				 }
			}
			if (toVisit.size() == remaining) {
				System.err.println(toVisit.toString());
				for (Information i: toVisit) {
					for(InformationLink l : i.getTargetedBy()) {
						System.err.println(l.getSource().getMessage() + " -> "+i.getMessage());
					}
				}
				throw new IllegalArgumentException("Endless loop of "+ toVisit.size() +" nodes - please check the argumentation graph");
			}
		}
	}
	
	private boolean ready(Information info, HashSet<Information> toVisit) {
		for (InformationLink informationLink : info.getTargetedBy()) {
			if (toVisit.contains(informationLink.getSource()))
				return false;
		}
		return true;
	}
	
	private Float currentNodeTrust(Information info) {
		return limitTo1(info.getInitialTrust()
			+ getRepetitionScore(info)
			+ weight*argumentationScore(info));
	}
	
	// relies on current trust 
	private float argumentationScore(Information info) {
		return (float) info.getTargetedBy().stream()
			.mapToDouble(link -> score(link))
			.sum();
	}
	
	private float score(InformationLink link) {
		float edgeWeight;
		switch(link.getType()) {
		case SUPPORT:
			edgeWeight = 0.5f;
			break;
		case STRONG_SUPPORT:
			edgeWeight = 1.0f;
			break;
		case ATTACK:
			edgeWeight = -0.5f;
			break;
		case STRONG_ATTACK:
			edgeWeight = -1.0f;
			break;
		case SUPPLEMENT:
		default:
			edgeWeight = 0.0f;
			break;	
		}
		// TODO should we ignore nodes that have negative trust or let them work in opposite direction? Currently opposite:
		return edgeWeight*link.getSource().getCurrentTrust();
	}
	
	private float getRepetitionScore(Information info) {
		return receives.isEmpty() ? 0F : (float) info.getRepeatedBy().size()/receives.size();
	}

	private static Float limitTo1(Float f) {
		if (f<-1) return -1F;
		if (f>1) return 1F;
		return f;
	}
	
}
