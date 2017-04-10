package alignment;
import java.util.*;

public class TracebackTree {

	private TracebackNode root;
	private ArrayList<TracebackNode> leaves;
	
	
	public TracebackTree(TracebackNode root, ArrayList<TracebackNode> leaves) {
		this.root = root;
		this.leaves = leaves;
	}
	
	public TracebackTree(){
		this.root = new TracebackNode();
		this.leaves = new ArrayList<TracebackNode>();
	}

	public TracebackNode getRoot() {
		return root;
	}

	public void setRoot(TracebackNode root) {
		this.root = root;
	}

	public ArrayList<TracebackNode> getLeaves() {
		return leaves;
	}

	public void setLeaves(ArrayList<TracebackNode> leaves) {
		this.leaves = leaves;
	}
	
	public void addLeaf(TracebackNode leaf){
		this.leaves.add(leaf);
	}
	
	public void setupRoot(Element element){
		TracebackNode root = new TracebackNode();
		root.setElement(element);
		this.setRoot(root);
		this.getLeaves().add(root);
	}
}
