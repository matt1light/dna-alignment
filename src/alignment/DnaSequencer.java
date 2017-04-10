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
	}

	private DPM matrix;
	private Input input;
	private Traceback traceback;
	
	private void getUserInputs() {
		this.input.getSequenceAndInts();
	}
	
	//Prints the initialized matrix without values
	private void printInitializedMatrix() {
		this.getDPM().printEmpty(this.getInput().getDna1().getSequence(), this.getInput().getDna2().getSequence());
	}
	
	//Prints the Needleman Wunsch populated matrix
	private void printPopulatedMatrix(){
		this.getDPM().printFull(this.getInput().getDna1().getSequence(), this.getInput().getDna2().getSequence());
	}

	// gets user inputs performs calculations and opens menu
	private void simulate(){
		this.getUserInputs();
		this.initializeMatrix();
		this.populateMatrix();
		this.traceback();
		this.menu();
	}
	
	//Initializes the matrix DPM from user inputs
	private void initializeMatrix(){
		this.setDPM(new DPM());
		this.getDPM().setWidth(this.getInput().getDna1().getSequence().length());
		this.getDPM().setHeight(this.getInput().getDna2().getSequence().length());
		this.getDPM().setElement(new ArrayList<ArrayList<Element>>());
		this.getDPM().initialize();
	}
	
	private void menu() {
			
		String choice = "";
		do {

			// while case is not exit, go through the menu
			System.out.println("\nThe optimal alignment score is " + this.getDPM().getLastElement().getScore());
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
	
	//Populates the matrix with values calculated using Needlman Wunsch
	private void populateMatrix(){
		this.getDPM().populate(this.getInput());
	}
	
	//initializes the traceback and creates a Traceback object that contains all the optimal DNA alignments
	private void traceback(){
		// initializes the root and adds it to the leaves list
		this.getTraceback().runTraceback(this.getDPM());	
	}
	
	
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

	

	
	
}
	
