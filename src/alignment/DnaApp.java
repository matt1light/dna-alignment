package alignment;

public class DnaApp {

	// Main method runs on object initialization of DNAAPP
	public static void main(String[] args) {
		Input input = new Input();
		Traceback traceback = new Traceback();
		DnaSequencer dnasequencer = new DnaSequencer();
		dnasequencer.setInput(input);
		dnasequencer.setTraceback(traceback);
		dnasequencer.simulate();
	}

}
