package keml.analysis;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.csv.CSVPrinter;

import keml.Information;
import keml.InformationLinkType;
import keml.NewInformation;
import keml.PreKnowledge;
import keml.ReceiveMessage;

public class InformationPartAnalyser {
	
	List<String> partners; //works as headers
	int dimension; // for analysis of message connections
	List<PreKnowledge> preKnowledge;
	List<ReceiveMessage> receives;  //starting point for work on knowledge part
	List<NewInformation> newInfos;


	public InformationPartAnalyser(List<String> partners, List<ReceiveMessage> receives, List<PreKnowledge> preKnowledge) {
		this.partners = partners;
		dimension = (partners.size()+1)*2;
		this.receives = receives;
		this.newInfos = ConversationAnalyser.getNewInfos(receives);
		this.preKnowledge = preKnowledge;
	}
	
	public void writeInformationConnections(CSVPrinter csvPrinter) throws IOException {
		writeMatrix(connectionMatrix(), csvPrinter);
	}

	/** Shared by the CSV writer and the interactive analysis results. */
	String[][] connectionMatrix() {
		//matrix holds fact and instruction entry for the author (for pre-knowledge) and each partner
		int[][] countAttacks = new int[dimension][dimension];
		int[][] countSupports = new int[dimension][dimension];
		
		Stream.concat(newInfos.stream(), preKnowledge.stream())
			.forEach(info -> {
				int index = getIndexOfInfo(info);
				info.getCauses().forEach(link -> {
					int partnerIndex = getIndexOfInfo(link.getTarget());
					if (link.getType() == InformationLinkType.SUPPORT || link.getType() == InformationLinkType.STRONG_SUPPORT) {
						countSupports[index][partnerIndex] +=1;
					}
					if (link.getType() == InformationLinkType.ATTACK || link.getType() == InformationLinkType.STRONG_ATTACK) {
						countAttacks[index][partnerIndex] +=1;
					}
					// todo analyse supplements?
				});
			});
		
		String[][] text = combineMatrices(countAttacks, countSupports);
		return text;
	}


	// writes both matrices a, b into one having entries "a/b"
	// assumption is that a and b are square matrices and have the same length dimensions
	private String[][] combineMatrices(int[][] a, int[][]b) {
		String[][] result = new String[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j=0; j < dimension; j++) {
				result[i][j] = a[i][j]+"/"+b[i][j];
			}
		}
		return result;
	}
	
	String[] headers() {
		String[] headers = new String[dimension];
		for (int i = 0; i< partners.size(); i++) {
			headers[2*i] = partners.get(i) + " F";
			headers[2*i+1] = partners.get(i) + " I";
		}
		headers[dimension-2]= "Author F";
		headers[dimension-1]= "Author I";
		return headers;
	}

	// writes both matrices A, B into one having entries "a/b"
	private void writeMatrix(String[][] m, CSVPrinter csvPrinter) throws IOException {
		String[] headers = headers();
		csvPrinter.print("Attacks/Supports");
		csvPrinter.printRecord(headers);
		for (int i = 0; i < dimension; i++) {
			csvPrinter.print(headers[i]);
			csvPrinter.printRecord(m[i]);
		}
	}
	
	private int getIndexOfInfo(Information info) {
		int offset;
		if (info.isIsInstruction()) offset = 1; else offset = 0;
		int partnerIndex;
		if (info instanceof NewInformation) {
			partnerIndex = partners.indexOf(((NewInformation) info).getSourceConversationPartner().getName());
		} else {
			partnerIndex = partners.size();
		}
		return 2*partnerIndex + offset;
	}

}
