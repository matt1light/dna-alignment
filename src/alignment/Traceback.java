package alignment;

import java.util.ArrayList;

public class Traceback {

	// Root of the tree. In this case it will refer to the last element in the
	// matrix.
	private TracebackNode<Element> root;
	// List of leaves of the tree
	private ArrayList<TracebackNode<Element>> leaves;
	// List of the first node of each of the optimal alignments
	private ArrayList<TracebackNode<Element>> routes;

	// Constructor
	public Traceback(TracebackNode<Element> root, ArrayList<TracebackNode<Element>> leaves, ArrayList<TracebackNode<Element>> routes) {
		this.root = root;
		this.leaves = leaves;
		this.routes = routes;
	}

	// Default constructor
	public Traceback() {
		this.root = new TracebackNode<Element>();
		this.leaves = new ArrayList<TracebackNode<Element>>();
		this.routes = new ArrayList<TracebackNode<Element>>();
	}

	public TracebackNode<Element> getRoot() {
		return root;
	}

	public void setRoot(TracebackNode<Element> root) {
		this.root = root;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((leaves == null) ? 0 : leaves.hashCode());
		result = prime * result + ((root == null) ? 0 : root.hashCode());
		result = prime * result + ((routes == null) ? 0 : routes.hashCode());
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
		Traceback other = (Traceback) obj;
		if (leaves == null) {
			if (other.leaves != null)
				return false;
		} else if (!leaves.equals(other.leaves))
			return false;
		if (root == null) {
			if (other.root != null)
				return false;
		} else if (!root.equals(other.root))
			return false;
		if (routes == null) {
			if (other.routes != null)
				return false;
		} else if (!routes.equals(other.routes))
			return false;
		return true;
	}

	public ArrayList<TracebackNode<Element>> getLeaves() {
		return leaves;
	}

	public void setLeaves(ArrayList<TracebackNode<Element>> leaves) {
		this.leaves = leaves;
	}

	public ArrayList<TracebackNode<Element>> getRoutes() {
		return routes;
	}

	public void setRoutes(ArrayList<TracebackNode<Element>> routes) {
		this.routes = routes;
	}

	// Initializes the root and adds it to the leaves list.
	// Element is the element that the root refers to
	private void setupRoot(Element element) {
		root.setElement(element);
		this.getLeaves().add(root);
	}

	// public class used to initialize the root and then traceback the optimal
	// alignments
	public void runTraceback(DPM matrix) {
		this.setupRoot(matrix.getLastElement());
		this.traceFromLeaves(matrix);
	}

	// Adds all the next nodes as children of "parent" determined by the
	// direction value in the matrix.
	// Parent is the node for which it's children are added
	// matrix is the DPM for which the traceback is performed on
	// Returns true if any children are added, and false if none are.
	private boolean addNextNodes(TracebackNode<Element> parent, DPM matrix) {
		Element element = parent.getElement();
		ArrayList<TracebackNode<Element>> leaves = this.getLeaves();
		boolean success = false;
		if (element.isLeft()) {
			leaves.add(parent.addChildNode(matrix.getElementByIndex(element.getRow(), element.getColumn() - 1)));
			success = true;
		}
		if (element.isUp()) {
			leaves.add(parent.addChildNode(matrix.getElementByIndex(element.getRow() - 1, element.getColumn())));
			success = true;
		}
		if (element.isDiagonal()) {
			leaves.add(parent.addChildNode(matrix.getElementByIndex(element.getRow() - 1, element.getColumn() - 1)));
			success = true;
		}
		return success;
	}

	// Traces back all optimal routes found in matrix
	// Capped off at 1000 optimal alignments
	// Adds all optimal alignments to routes
	private void traceFromLeaves(DPM matrix) {
		ArrayList<TracebackNode<Element>> leaves = this.getLeaves();
		while (!leaves.isEmpty() && routes.size() < 1000) {
			int index = leaves.size() - 1;
			if (!this.addNextNodes(leaves.get(index), matrix)) {
				routes.add(leaves.get(index));
			}
			leaves.remove(index);
		}
	}

	// Prints the optimal alignment determined by the traceback and stored in
	// routes.
	// Navigates from the node of index "index" through to the route and prints
	// the corresponding letters/gaps.
	public void printAlignment(DNA dna1, DNA dna2, int index) {
		TracebackNode<Element> current = this.routes.get(index);
		String sequence1 = dna1.getSequence();
		String sequence2 = dna2.getSequence();
		int counter = 1;
		while (current.getParent() != null) {
			if (current.getElement().getRow() == current.getParent().getElement().getRow()) {
				sequence2 = sequence2.substring(0, counter) + "_" + sequence2.substring(counter, sequence2.length());
			}
			if (current.getElement().getColumn() == current.getParent().getElement().getColumn()) {
				sequence1 = sequence1.substring(0, counter) + "_" + sequence1.substring(counter, sequence1.length());
			}
			counter++;
			current = current.getParent();
		}
		System.out.println("\n" + sequence1);
		System.out.println(sequence2);
	}

	// Prints all alignments of dna1 and dna2
	// Calls printAlignment for every index in routes.
	public void printAllAlignments(DNA dna1, DNA dna2) {
		for (int i = 0; i < routes.size(); i++) {
			this.printAlignment(dna1, dna2, i);
		}
	}
	
	
}