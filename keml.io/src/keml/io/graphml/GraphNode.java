package keml.io.graphml;

public class GraphNode {
	private String id;
	private NodeType type;
	private String label;
	private PositionalInformation pos;
	
	
	
	public GraphNode(String id, NodeType type, String label) {
		super();
		this.id = id;
		this.type = type;
		this.label = label;
	}
	public GraphNode(String id, NodeType type, String label, PositionalInformation pos) {
		super();
		this.id = id;
		this.type = type;
		this.label = label;
		this.pos = pos;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public NodeType getType() {
		return type;
	}
	public void setType(NodeType type) {
		this.type = type;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public PositionalInformation getPos() {
		return pos;
	}
	public void setPos(PositionalInformation pos) {
		this.pos = pos;
	}
	@Override
	public String toString() {
		return "GraphNode [id=" + id + ", type=" + type + ", label=" + label + ", pos=" + pos + "]";
	}
	
}
