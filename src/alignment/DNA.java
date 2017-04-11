package alignment;

public class DNA {
	
	private int id;
	private String sequence;
	
	//Constructor, takes id and sequence as arguments
	//Could randomly generate id, but not necessary as only two dna objects will be made in the current version of the program
	public DNA(int id, String sequence){
		this.id = id;
		this.sequence = " " + sequence;
	}
	//default constructor
	public DNA(){
	}
	//Getters and Setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSequence() {
		return sequence;
	}
	
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
}
