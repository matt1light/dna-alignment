package alignment;

import java.util.Random;

public class DNA {

	public static int lastId = 0;
	private int id;
	private String sequence;
	
	//Constructor, takes id and sequence as arguments
	//Could randomly generate id, but not necessary as only two dna objects will be made in the current version of the program
	public DNA(int id, String sequence){
		this.id = id;
		this.sequence = sequence;
	}
	//overloaded constructor with an id of one more than the last DNA strand's id and buffer space for matrix
	public DNA(String sequence){
		this(++lastId, " " + sequence);
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
