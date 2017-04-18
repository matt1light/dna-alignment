package alignment;

import java.util.*;

public class TracebackNode <Data> {

	// The parent node. The node one level higher in the tree structure. This
	// node is it's child.
	private TracebackNode <Data> parent;
	// A list of all of the children of the traceback node.
	private ArrayList<TracebackNode <Data>> children;
	// The data contained in these nodes are elements. These refer to specific
	// indexes of the array, containing score and direction
	private Data element;

	// Constructor takes, parent, children and element arguments
	public TracebackNode(TracebackNode<Data> parent, ArrayList<TracebackNode <Data>> children, Data element) {
		this.parent = parent;
		this.children = children;
		this.element = element;
	}

	// Default constructor initializes the children array list as it will be
	// added to and not set
	public TracebackNode() {
		this.children = new ArrayList<TracebackNode <Data>>();
	}

	// Getters and setters
	public TracebackNode<Data> getParent() {
		return parent;
	}

	public void setParent(TracebackNode<Data> parent) {
		this.parent = parent;
	}

	public ArrayList<TracebackNode <Data>> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<TracebackNode <Data>> children) {
		this.children = children;
	}

	public Data getElement() {
		return element;
	}

	public void setElement(Data element) {
		this.element = element;
	}

	// Checks if the node is a root (has no parent)
	public boolean isRoot() {
		return (this.parent == null);
	}

	// Checks if the node is a leaf (has no children)
	public boolean isLeaf() {
		return (this.children.isEmpty());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TracebackNode<Data> other = (TracebackNode<Data>) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
	}

	// Constructs a child node referring to the Element element
	// Sets the child node's parent to the calling node
	public TracebackNode<Data> addChildNode(Data element) {
		TracebackNode<Data> node = new TracebackNode<Data>();
		node.setElement(element);
		node.setParent(this);
		this.getChildren().add(node);
		return node;
	}
}
