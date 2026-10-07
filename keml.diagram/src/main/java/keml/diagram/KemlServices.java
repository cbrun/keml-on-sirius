package keml.diagram;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import keml.Author;
import keml.Conversation;
import keml.ConversationPartner;
import keml.Information;
import keml.InformationLink;
import keml.InformationLinkType;
import keml.KemlFactory;
import keml.LifeLine;
import keml.Message;
import keml.NewInformation;
import keml.PreKnowledge;
import keml.ReceiveMessage;
import keml.SendMessage;

/** Queries and editing operations shared by the diagram, its tools and layout. */
public class KemlServices {
    private static final KemlFactory FACTORY = KemlFactory.eINSTANCE;

    public Conversation kemlConversation(EObject object) {
        for (EObject current = object; current != null; current = current.eContainer()) {
            if (current instanceof Conversation conversation) {
                return conversation;
            }
        }
        throw new IllegalArgumentException("The element must belong to a KEML conversation.");
    }

    public List<LifeLine> kemlLifeLines(Conversation conversation) {
        List<LifeLine> result = new ArrayList<>();
        if (conversation.getAuthor() != null) {
            result.add(conversation.getAuthor());
        }
        result.addAll(conversation.getConversationPartners().stream()
                .sorted(Comparator.comparingInt(ConversationPartner::getXPosition)).toList());
        return result;
    }

    public List<Message> kemlMessages(Conversation conversation) {
        return conversation.getAuthor() == null ? List.of() : conversation.getAuthor().getMessages().stream()
                .sorted(Comparator.comparingInt(Message::getTiming)).toList();
    }

    public List<Information> kemlInformation(Conversation conversation) {
        List<Information> result = new ArrayList<>();
        if (conversation.getAuthor() != null) {
            result.addAll(conversation.getAuthor().getPreknowledge());
            for (Message message : this.kemlMessages(conversation)) {
                if (message instanceof ReceiveMessage received) {
                    result.addAll(received.getGenerates());
                }
            }
        }
        return result;
    }

    public List<InformationLink> kemlLinks(Conversation conversation) {
        return this.kemlInformation(conversation).stream().flatMap(information -> information.getCauses().stream()).toList();
    }

    public String kemlParticipantLabel(LifeLine lifeLine) {
        return lifeLine.getName() == null || lifeLine.getName().isBlank()
                ? lifeLine instanceof Author ? "Author" : "Partner" : lifeLine.getName();
    }

    public String kemlMessageLabel(Message message) {
        int step = this.kemlMessages(this.kemlConversation(message)).indexOf(message) + 1;
        return "%02d  %s".formatted(step, this.compact(message.getContent(), 140));
    }

    public String kemlInformationLabel(Information information) {
        String kind = information.isIsInstruction() ? "Instruction" : information instanceof PreKnowledge ? "Pre-knowledge" : "Knowledge";
        String trust = information.getCurrentTrust() == null ? "" : " · trust " + Math.round(information.getCurrentTrust() * 100) + "%";
        // The cylinder truncates visually, so resizing it can reveal the full message.
        return kind + trust + "\n" + (information instanceof PreKnowledge
                ? Objects.requireNonNullElse(information.getMessage(), "").strip() : this.compact(information.getMessage(), 200));
    }

    public int kemlKnowledgeHeight(Information information) {
        int height = Math.max(96, 48 + 20 * (int) Math.ceil(this.compact(information.getMessage(), 200).length() / 30.0));
        return information instanceof PreKnowledge ? height * 3 / 2 : height;
    }

    private String compact(String text, int limit) {
        String value = Objects.requireNonNullElse(text, "").strip().replaceAll("\\s+", " ");
        return value.length() <= limit ? value : value.substring(0, limit - 1) + "…";
    }

    public ConversationPartner kemlCreatePartner(Conversation conversation) {
        this.author(conversation);
        ConversationPartner partner = FACTORY.createConversationPartner();
        partner.setName("Partner " + (conversation.getConversationPartners().size() + 1));
        partner.setXPosition(300 * (conversation.getConversationPartners().size() + 1));
        conversation.getConversationPartners().add(partner);
        return partner;
    }

    public SendMessage kemlSend(EObject context) {
        Conversation conversation = this.kemlConversation(context);
        SendMessage message = FACTORY.createSendMessage();
        message.setCounterPart(this.partner(context));
        message.setContent("New message");
        this.addMessage(conversation, message);
        return message;
    }

    public ReceiveMessage kemlReceive(EObject context) {
        Conversation conversation = this.kemlConversation(context);
        ReceiveMessage message = FACTORY.createReceiveMessage();
        message.setCounterPart(this.partner(context));
        message.setContent("New response");
        this.addMessage(conversation, message);
        return message;
    }

    /** The UI names send/receive relative to the selected actor, while EMF names them relative to the Author. */
    public Message kemlSendFrom(EObject context, LifeLine recipient) {
        this.sameConversation(context, recipient);
        Conversation conversation = this.kemlConversation(context);
        if ((context == conversation || context == conversation.getAuthor()) && recipient instanceof ConversationPartner partner) {
            return this.kemlSend(partner);
        }
        if (context instanceof ConversationPartner partner && recipient == conversation.getAuthor()) {
            return this.kemlReceive(partner);
        }
        throw new IllegalArgumentException("Choose an exchange between the Author and a different conversation partner.");
    }

    public Message kemlReceiveAt(EObject context, LifeLine sender) {
        this.sameConversation(context, sender);
        Conversation conversation = this.kemlConversation(context);
        if ((context == conversation || context == conversation.getAuthor()) && sender instanceof ConversationPartner partner) {
            return this.kemlReceive(partner);
        }
        if (context instanceof ConversationPartner partner && sender == conversation.getAuthor()) {
            return this.kemlSend(partner);
        }
        throw new IllegalArgumentException("Choose an exchange between the Author and a different conversation partner.");
    }

    public List<LifeLine> kemlMessagePeers(EObject context) {
        Conversation conversation = this.kemlConversation(context);
        if (context == conversation || context == conversation.getAuthor()) {
            return new ArrayList<>(conversation.getConversationPartners());
        }
        if (context instanceof ConversationPartner && conversation.getAuthor() != null) {
            return List.of(conversation.getAuthor());
        }
        return List.of();
    }

    /** A reply reverses the existing message's direction and keeps its counterpart. */
    public Message kemlReply(Message message) {
        return message instanceof SendMessage ? this.kemlReceive(message) : this.kemlSend(message);
    }

    private Author author(Conversation conversation) {
        if (conversation.getAuthor() == null) {
            Author author = FACTORY.createAuthor();
            author.setName("Author");
            conversation.setAuthor(author);
        }
        return conversation.getAuthor();
    }

    private ConversationPartner partner(EObject context) {
        if (context instanceof ConversationPartner partner) {
            return partner;
        }
        if (context instanceof Message message && message.getCounterPart() != null) {
            return message.getCounterPart();
        }
        throw new IllegalArgumentException("Choose the conversation partner explicitly.");
    }

    private void addMessage(Conversation conversation, Message message) {
        Author author = this.author(conversation);
        int timing = author.getMessages().stream().mapToInt(Message::getTiming).max().orElse(-1);
        message.setTiming(Math.addExact(timing, 1));
        author.getMessages().add(message);
    }

    public PreKnowledge kemlAddPreKnowledge(Conversation conversation) {
        PreKnowledge knowledge = FACTORY.createPreKnowledge();
        knowledge.setMessage("Prior knowledge");
        knowledge.setInitialTrust(1.0f);
        knowledge.setCurrentTrust(1.0f);
        this.author(conversation).getPreknowledge().add(knowledge);
        return knowledge;
    }

    public NewInformation kemlAddInformation(ReceiveMessage received) {
        NewInformation information = FACTORY.createNewInformation();
        information.setMessage("New knowledge");
        information.setInitialTrust(0.5f);
        information.setCurrentTrust(0.5f);
        received.getGenerates().add(information);
        return information;
    }

    public NewInformation kemlAddInstruction(ReceiveMessage received) {
        NewInformation instruction = this.kemlAddInformation(received);
        instruction.setIsInstruction(true);
        instruction.setMessage("New instruction");
        return instruction;
    }

    public InformationLink kemlLinkTo(Information source, Information target, String typeName) {
        this.sameConversation(source, target);
        if (source == target) {
            throw new IllegalArgumentException("Choose two different knowledge cards.");
        }
        InformationLinkType type = InformationLinkType.getByName(typeName);
        if (type == null) {
            throw new IllegalArgumentException("Unknown KEML link type: " + typeName);
        }
        return source.getCauses().stream().filter(link -> link.getTarget() == target && link.getType() == type).findFirst().orElseGet(() -> {
            InformationLink link = FACTORY.createInformationLink();
            link.setTarget(target);
            link.setType(type);
            source.getCauses().add(link);
            return link;
        });
    }

    public Message kemlUseInformation(Information information, Message message) {
        this.sameConversation(information, message);
        if (!(message instanceof SendMessage sent)) {
            throw new IllegalArgumentException("Knowledge can be used by a sent message.");
        }
        if (!sent.getUses().contains(information)) {
            sent.getUses().add(information);
        }
        return sent;
    }

    public Message kemlRepeatInformation(Message message, Information information) {
        this.sameConversation(message, information);
        if (!(message instanceof ReceiveMessage received)) {
            throw new IllegalArgumentException("Knowledge can be repeated by a received message.");
        }
        if (!received.getRepeats().contains(information)) {
            received.getRepeats().add(information);
        }
        return received;
    }

    public Information kemlForgetUse(Information information, SendMessage sent) {
        sent.getUses().remove(information);
        return information;
    }

    public Information kemlForgetRepeat(Information information, ReceiveMessage received) {
        received.getRepeats().remove(information);
        return information;
    }

    public Conversation kemlDeletePartner(ConversationPartner partner) {
        Conversation conversation = this.kemlConversation(partner);
        this.kemlMessages(conversation).stream().filter(message -> message.getCounterPart() == partner)
                .forEach(this::kemlDeleteElement);
        EcoreUtil.delete(partner, true);
        return conversation;
    }

    public Conversation kemlDeleteElement(EObject object) {
        Conversation conversation = this.kemlConversation(object);
        List<Information> removed = new ArrayList<>();
        if (object instanceof Information information) {
            removed.add(information);
        }
        object.eAllContents().forEachRemaining(child -> {
            if (child instanceof Information information) {
                removed.add(information);
            }
        });
        // An InformationLink's target is mandatory: remove incident links instead of leaving dangling links.
        for (Information information : removed) {
            for (InformationLink link : List.copyOf(information.getTargetedBy())) {
                EcoreUtil.delete(link, true);
            }
        }
        EcoreUtil.delete(object, true);
        return conversation;
    }

    private void sameConversation(EObject source, EObject target) {
        if (this.kemlConversation(source) != this.kemlConversation(target)) {
            throw new IllegalArgumentException("The two elements must belong to the same conversation.");
        }
    }

    /** A short editable example inspired by the web editor's published Log4j conversation. */
    public Conversation kemlExampleConversation() {
        Conversation conversation = FACTORY.createConversation();
        conversation.setTitle("Log4j repository search");
        ConversationPartner llm = this.kemlCreatePartner(conversation);
        llm.setName("LLM");
        ConversationPartner browser = this.kemlCreatePartner(conversation);
        browser.setName("Browser");
        PreKnowledge versions = this.kemlAddPreKnowledge(conversation);
        versions.setMessage("Log4j versions 2.0-beta through 2.17.0");
        PreKnowledge fixes = this.kemlAddPreKnowledge(conversation);
        fixes.setMessage("Exclude the security fixes 2.3.2 and 2.12.4");

        SendMessage question = this.kemlSend(llm);
        question.setContent("Find GitHub repositories using Log4j 2.0 to 2.17.0");
        question.getUses().add(versions);
        ReceiveMessage answer = this.kemlReceive(llm);
        answer.setContent("No real-time web access; here is how to search GitHub");
        NewInformation search = this.kemlAddInstruction(answer);
        search.setMessage("Search GitHub: log4j version:2.0..2.17.0");
        NewInformation steps = this.kemlAddInstruction(answer);
        steps.setMessage("Steps to search GitHub as a user");
        this.kemlLinkTo(steps, search, "SUPPLEMENT");

        SendMessage execute = this.kemlSend(browser);
        execute.setContent("Execute the suggested search");
        execute.getUses().add(search);
        ReceiveMessage results = this.kemlReceive(browser);
        results.setContent("7 search results, not yet checked in depth");
        NewInformation urls = this.kemlAddInformation(results);
        urls.setMessage("7 URLs of unknown repositories");
        urls.setCurrentTrust(1.0f);
        this.kemlLinkTo(urls, search, "SUPPORT");

        SendMessage exclude = this.kemlSend(llm);
        exclude.setContent("Also exclude versions 2.3.2 and 2.12.4");
        exclude.getUses().add(fixes);
        ReceiveMessage revised = this.kemlReceive(llm);
        revised.setContent("An adapted search string");
        NewInformation adapted = this.kemlAddInstruction(revised);
        adapted.setMessage("log4j version:2.0..2.17.0 -2.3.2 -2.12.4");
        this.kemlLinkTo(adapted, search, "SUPPLEMENT");

        SendMessage compare = this.kemlSend(browser);
        compare.setContent("Use the latest search and compare with the previous results");
        compare.getUses().add(adapted);
        ReceiveMessage checked = this.kemlReceive(browser);
        checked.setContent("5 results from before, but most repositories are empty");
        NewInformation empty = this.kemlAddInformation(checked);
        empty.setMessage("Most repositories are empty; none use Gradle");
        empty.setCurrentTrust(0.9f);
        this.kemlLinkTo(empty, urls, "ATTACK");
        checked.getRepeats().add(urls);
        return conversation;
    }
}
