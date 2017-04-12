package alignment;

// This class is used to run our application
// Originally the main method was in DnaSequencer, 
// but it has been separated in case there are other applications 
// in which we would want to use the DnaSequencer Class
public class DnaApp {

	// Main method runs on object initialization of DNAAPP
	public static void main(String[] args) {
		DnaSequencer dnasequencer = new DnaSequencer();
		dnasequencer.run();
	}

}
