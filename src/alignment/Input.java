package alignment;
import java.util.*;

public class Input {
	

		private  DNA dna1;
		private DNA dna2;
		private double matchScore;
		private double mismatchPenalty;
		private double gapPenalty;
		//constructor
		
		private boolean validSequence(String sequence, String constraints){
			for (char character: sequence.toCharArray()){
				if (constraints.indexOf(character) < 0){
					System.out.println("This is not a valid input");
					System.out.println("Ensure that you only use the characters in " + constraints);
					return false;
				}
			}
			return true;
		}
		
		public Input(DNA dna1, DNA dna2, double matchScore, double mismatchPenalty, double gapPenalty) {
			this.dna1 = dna1;
			this.dna2 = dna2;
			this.matchScore = matchScore;
			this.mismatchPenalty = mismatchPenalty;
			this.gapPenalty = gapPenalty;
		}
		//default constructor
		public Input()
		{
		}
		//getters and setters
		public DNA getDna1() {
			return dna1;
		}

		public void setDna1(DNA dna1) {
			this.dna1 = dna1;
		}

		public DNA getDna2() {
			return dna2;
		}

		public void setDna2(DNA dna2) {
			this.dna2 = dna2;
		}

		public double getMatchScore() {
			return matchScore;
		}

		public void setMatchScore(double matchScore) {
			this.matchScore = matchScore;
		}

		public double getMismatchPenalty() {
			return mismatchPenalty;
		}

		public void setMismatchPenalty(double mismatchPenalty) {
			this.mismatchPenalty = mismatchPenalty;
		}

		public double getGapPenalty() {
			return gapPenalty;
		}

		public void setGapPenalty(double gapPenalty) {
			this.gapPenalty = gapPenalty;
		}
		
		public void printSequences(){
			System.out.println(this.dna1.getSequence());
			System.out.println(this.dna2.getSequence());
		}
		
		public void getSequenceAndInts(){
			Scanner sc = new Scanner(System.in);
			System.out.println("What characters would you like to allow in this alignment?: ");
			String constraints = sc.nextLine();
			
			String sequence1;
			do {
			    System.out.println("Enter your first sequence to be aligned:");
			    sequence1 = sc.nextLine();
			} while (!this.validSequence(sequence1, constraints));
			// Verify that the input is valid
			
			String sequence2;
			do {
			    System.out.println("Enter your second sequence to be aligned");
			    sequence2 = sc.nextLine();
			} while (!this.validSequence(sequence2, constraints));
			//Verify that the input is valid
			
			double match = 0;
			do {
				System.out.println("Please enter a match score: ");

				try {
					match = Math.abs(sc.nextDouble());
				}
				catch (InputMismatchException e) {
					System.out.println("This is not a valid input");
					System.out.println("Ensure that you type a number");
					sc.next();
				}
			} while (match <= 0);
			
			
			double gap = -1;
			do {
				System.out.println("Please enter a gap penalty: ");

				try {
					gap = Math.abs(sc.nextDouble());
				}
				catch (InputMismatchException e) {
					System.out.println("This is not a valid input");
					System.out.println("Ensure that you type a number");
					sc.next();
				}
			} while (gap < 0);
			
			double mismatch = -1;
			do {
				System.out.println("Please enter a mismatch penalty: ");

				try {
					mismatch = Math.abs(sc.nextDouble());
				}
				catch (InputMismatchException e) {
					System.out.println("This is not a valid input");
					System.out.println("Ensure that you type a number");
					sc.next();
				}
			} while (mismatch < 0);
						
			this.setDna1(new DNA(1, " " + sequence1));
			this.setDna2(new DNA(2, " " + sequence2));
			this.setMatchScore(match);
			this.setMismatchPenalty(mismatch);
			this.setGapPenalty(gap);
			
		}
		
}
		//case exceptions
		
	