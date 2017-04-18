package alignment;

import java.util.*;

public class Input {

	private DNA dna1;
	private DNA dna2;
	private double matchScore;
	private double mismatchPenalty;
	private double gapPenalty;

	// constructor
	public Input(DNA dna1, DNA dna2, double matchScore, double mismatchPenalty, double gapPenalty) {
		this.dna1 = dna1;
		this.dna2 = dna2;
		this.matchScore = matchScore;
		this.mismatchPenalty = mismatchPenalty;
		this.gapPenalty = gapPenalty;
	}

	// default constructor
	public Input() {
	}

	// getters and setters
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

	public void printSequences() {
		System.out.println(this.dna1.getSequence());
		System.out.println(this.dna2.getSequence());
	}

	// Gets User input of the two sequences and the three score parameters
	// First it asks the user to input a string to represent the set of
	// allowable characters to be aligned
	// Next it gets both sequences
	// If any of the characters in the sequences are not contained in
	// constraints, it prompts the user to try again
	// Next it gets the three scoring parameters
	// If the input are not doubles (or ints), or if matchScore is 0 it prompts
	// the user to try again
	// All values are stored as positive integers (absolute value is taken),
	// It is assumed that matchScore is positive and should be added, and that
	// the two penalties should be subtracted
	public void getSequenceAndInts() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What characters would you like to allow in this alignment?: ");
		String constraints = sc.nextLine();

		String sequence1;
		do {
			System.out.println("Enter your first sequence to be aligned:");
			sequence1 = sc.nextLine();
		} while (!this.validSequence(sequence1, constraints));

		String sequence2;
		do {
			System.out.println("Enter your second sequence to be aligned");
			sequence2 = sc.nextLine();
		} while (!this.validSequence(sequence2, constraints));

		double match = 0;
		do {
			System.out.println("Please enter a match score: ");

			try {
				match = Math.abs(sc.nextDouble());
			} catch (InputMismatchException e) {
				System.out.println("This is not a valid input");
				System.out.println("Ensure that you type non-zero number");
				sc.next();
			}
		} while (match <= 0);

		double gap = -1;
		do {
			System.out.println("Please enter a gap penalty: ");

			try {
				gap = Math.abs(sc.nextDouble());
			} catch (InputMismatchException e) {
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
			} catch (InputMismatchException e) {
				System.out.println("This is not a valid input");
				System.out.println("Ensure that you type a number");
				sc.next();
			}
		} while (mismatch < 0);

		this.setDna1(new DNA(sequence1));
		this.setDna2(new DNA(sequence2));
		this.setMatchScore(match);
		this.setMismatchPenalty(mismatch);
		this.setGapPenalty(gap);

	}

	// Checks if all of characters in sequence are contained in the string
	// constraints
	// Returns true all are. Returns false if any are not.
	// Returns false if sequence is 1 or smaller
	private boolean validSequence(String sequence, String constraints) {
		if (sequence.length() <= 1) {
			System.out.print("Input must be longer than one character");
			return false;
		}
		for (char character : sequence.toCharArray()) {
			if (constraints.indexOf(character) < 0) {
				System.out.println("This is not a valid input");
				System.out.println("Ensure that you only use the characters in " + constraints);
				return false;
			}
		}
		return true;
	}

}
