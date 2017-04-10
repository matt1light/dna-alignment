package alignment;

import java.util.*;

public class DnaSequencer {
	
	public static void main(String[] args){
		Input input = new Input();
		Traceback traceback = new Traceback();
		DnaSequencer dnasequencer = new DnaSequencer();
		dnasequencer.setInput(input);
		dnasequencer.setTraceback(traceback);
		dnasequencer.simulate();
		dnasequencer.printInitializedMatrix();
		dnasequencer.printPopulatedMatrix();
//		dnasequencer.getTraceback().printTracebackTree(dnasequencer.getTraceback().getRoot());
		dnasequencer.getTraceback().printAllAlignments(input.getDna1(), input.getDna2());
	}

	private DPM matrix;
	private Input input;
	private Traceback traceback;

	public DnaSequencer(DPM matrix, Input input, Traceback traceback) {
		this.matrix = matrix;
		this.input = input;
		this.traceback = traceback;
	}

	public DnaSequencer() {
	}

	public DPM getDPM() {
		return matrix;
	}

	public void setDPM(DPM matrix) {
		this.matrix = matrix;
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public Traceback getTraceback() {
		return traceback;
	}

	public void setTraceback(Traceback traceback) {
		this.traceback = traceback;
	}

	private void menu() {
		
		String choice = "";
		do {

			// while case is not exit, go through the menu
			System.out.println("\nThe optimal alignment alignment score is " + this.getDPM().getLastElement().getScore());
			System.out.println("What would you like to do next?");
			System.out.println("\t1: print both sequences");
			System.out.println("\t2: print initialized matrix");
			System.out.println("\t3: result of Needleman-Wunsch algorithm (print entire matrix)");
			System.out.println("\t4: print one optimal alignment");
			System.out.println("\t5: print all optimal alignments");
			System.out.println("\t6: enter two new sequences");
			System.out.println("\t7: exit\n");
			System.out.println("Choose an option: ");

			Scanner sc = new Scanner(System.in);
			choice = sc.next();

				switch(choice){
				
				case "1":
					System.out.println("Printing the two sequences...\n\n");
					this.input.printSequences();
					break;
				case "2":
					System.out.println("Printing the initialized matrix...\n\n");
					this.printInitializedMatrix();
					break;
				case "3":
					System.out.println("Printing the populated Needleman-Wunsch matrix...\n\n");
					this.printPopulatedMatrix();
					break;
				case "4":
					System.out.println("Printing one optimal alignment...\n\n");
					this.traceback.printAlignment(this.getInput().getDna1(), this.getInput().getDna2(), 0);;
					break;
				case "5":
					System.out.println("Printing all " + this.getTraceback().getRoutes().size() + " optimal alignments");
					this.traceback.printAllAlignments(this.getInput().getDna1(), this.getInput().getDna2());
					break;
				case "6":
					System.out.println(" ");
					this.simulate();
					break;
				case "7":
					System.out.print("Exiting...");
					sc.close();
					System.exit(0);
					break;
				default:
					System.out.println("Invalid input please try again\n");
				}
			} while(choice != "7");
	}

	private void getUserInputs() {
		this.input.getSequenceAndInts();
	}
	
	//TODO add spaces in between each dna and in between each matrix value
	//Prints the initialized matrix without values
	private void printInitializedMatrix() {
		this.getDPM().printEmpty(this.getInput().getDna1().getSequence(), this.getInput().getDna2().getSequence());
	}
	
	private void printPopulatedMatrix(){
		this.getDPM().printFull(this.getInput().getDna1().getSequence(), this.getInput().getDna2().getSequence());
	}
	//This could definitely be dryer.

	
	private void simulate(){
		this.getUserInputs();
		this.initializeMatrix();
		this.populateMatrix();
		this.traceback();
		this.menu();
	}
	
	private void initializeMatrix(){
		this.setDPM(new DPM());
		this.getDPM().setWidth(this.getInput().getDna1().getSequence().length());
		this.getDPM().setHeight(this.getInput().getDna2().getSequence().length());
		this.getDPM().setElement(new ArrayList<ArrayList<Element>>());
		this.getDPM().initialize();
	}
	
	private void populateMatrix(){
		this.getDPM().populate(this.getInput());
	}
	
	
	
	//initializes the traceback and creates a Traceback object that contains all the optimal DNA alignments
	//TODO this currently functions like a tree and does not check whether a node has been made already
	private void traceback(){
		// initializes the root and adds it to the leaves list
		this.getTraceback().runTraceback(this.getDPM());	
	}
	
	
	
	
	//This is a helper function, not called by the user
	
	
}
	
