package keml.io.graphml;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GraphMLUtils {
	
	public static String readColor(Node node) {
		Element e = (Element) node;
		NamedNodeMap fill = e.getElementsByTagName("y:Fill").item(0).getAttributes();
		return fill.getNamedItem("color").getNodeValue();
	}
	
	public static String readLabel(Node node) {
		Element e = (Element) node;
		return readLabel(e, "y:NodeLabel");
	}
	
	public static String readLabel(Element e, String elementName) {
		String label = "";
		NodeList nodeLabels = e.getElementsByTagName(elementName);
		for (int i=0; i<nodeLabels.getLength(); i++) {
			Node childNode = nodeLabels.item(i);
			if (childNode.getAttributes().getNamedItem("hasText") == null) {//a text exists
				label = childNode.getChildNodes().item(0).getNodeValue(); //TODO is item 0 guaranteed?
				label = cleanLabel(label);
			}
		}
		return label;
	}
	
	public static String cleanLabel(String s) {
		return s.trim().replace('\n', ' ');
	}

}
