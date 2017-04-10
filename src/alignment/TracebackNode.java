package alignment;
import java.util.*;

public class TracebackNode {
	
	//The parent node. The node one level higher in the tree structure. This node is it's child.
	private TracebackNode parent;
	//A list of all of the children of the traceback node.
	private ArrayList<TracebackNode> children;
	//The data contained in these nodes are elements. These refer to specific indexes of the array, containing score and direction
	private Element element;
	
	public TracebackNode(TracebackNode parent, ArrayList<TracebackNode> children, Element element) {
		this.parent = parent;
		this.children = children;
		this.element = element;
	}
	
	public TracebackNode(){
		this.children = new ArrayList<TracebackNode>();
	}
	
	public TracebackNode getParent() {
		return parent;
	}
	public void setParent(TracebackNode parent) {
		this.parent = parent;
	}
	public ArrayList<TracebackNode> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<TracebackNode> children) {
		this.children = children;
	}
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	
	public boolean isRoot(){
		return (this.parent != null);
	}
	
	public boolean isLeaf(){
		return (this.children.isEmpty());
	}
	
	//Constructs a child node referring to the Element element
	//Sets the child node's parent to the calling node
	public TracebackNode addChildNode(Element element){
		TracebackNode node = new TracebackNode();
		node.setElement(element);
		node.setParent(this);
		this.getChildren().add(node);
		return node;
	} 
}