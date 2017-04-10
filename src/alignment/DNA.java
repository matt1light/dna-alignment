package alignment;

public class DNA {
	
	private int id;
	private String sequence;
	
	public DNA(int id, String sequence){
		this.id = id;
		this.sequence = sequence;
	}
	
	public DNA(){
	}
	
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
