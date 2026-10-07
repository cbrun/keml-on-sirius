package keml.io.graphml;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class PositionalInformation {
	
	private Float xLeft = null;
	private Float xRight = null;
	private Float yLow = null;
	private Float yHigh = null;

	public PositionalInformation(Float xLeft, Float xRight, Float yLow, Float yHigh) {
		this.xLeft = xLeft;
		this.xRight = xRight;
		this.yLow = yLow;
		this.yHigh = yHigh;
	}	
	
	public PositionalInformation (Node node) {
		Element e = (Element) node;
		NamedNodeMap geo = e.getElementsByTagName("y:Geometry").item(0).getAttributes();
		String x = geo.getNamedItem("x").getNodeValue();
		this.xLeft = Float.parseFloat(x);
		String width = geo.getNamedItem("width").getNodeValue();
		this.xRight = xLeft + Float.parseFloat(width);
		String y = geo.getNamedItem("y").getNodeValue();
		this.yLow = Float.parseFloat(y);
		String height = geo.getNamedItem("height").getNodeValue();
		this.yHigh = yLow + Float.parseFloat(height);
	}
	
	public boolean touchesOnRight(PositionalInformation pos) {
		return floatEquality( xLeft, pos.getxRight() ) 
				&& floatEquality (yLow, pos.getyLow() )
				&& floatEquality (yHigh, pos.getyHigh());
	}
	
	public boolean isOnLine(PositionalInformation lifeLinePos) {
		return ( lifeLinePos.getxLeft() - xLeft <= 0 && lifeLinePos.getxRight() - xRight >=0);
	}
	
	@Override
	public String toString() {
		return "PositionalInformation [xLeft=" + xLeft + ", xRight=" + xRight + ", yLow=" + yLow + ", yHigh=" + yHigh
				+ "]";
	}

	public Float getxLeft() {
		return xLeft;
	}
	public void setxLeft(Float xLeft) {
		this.xLeft = xLeft;
	}
	public Float getxRight() {
		return xRight;
	}
	public void setxRight(Float xRight) {
		this.xRight = xRight;
	}
	public Float getyLow() {
		return yLow;
	}
	public void setyLow(Float yLow) {
		this.yLow = yLow;
	}
	public Float getyHigh() {
		return yHigh;
	}
	public void setyHigh(Float yHigh) {
		this.yHigh = yHigh;
	}

	private static boolean floatEquality(Float f1, Float f2) {
		return Math.abs(f1-f2) < 0.001F;
	}

}
