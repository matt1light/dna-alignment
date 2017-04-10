package alignment;

import java.util.*;

public class DPM {

	// The width (number of columns) in the matrix
	private int width;
	// The height (number of rows) in the matrix
	private int height;
	// A list of a list of the elements in the matrix. 2d array. First index
	// represents row number, second represents column.
	private ArrayList<ArrayList<Element>> elements;
	
	//Prints the matrix to the console
	//If the value of full is true, it will print the populated version of the matrix
	//If the value of full is false, it will print the initialized unpopulated version of the matrix
	private void print(String dnaSequence1, String dnaSequence2, boolean full) {
		System.out.print("\n\t");
		for (char character : dnaSequence1.toCharArray()) {
			System.out.print(character + "\t");
		}
		for (int i = 0; i < this.getHeight(); i++) {
			ArrayList<Element> row = this.getElement().get(i);
			char character = dnaSequence2.toCharArray()[i];
			System.out.print("\n\n");
			System.out.print(character + "\t");
			for (Element element : row) {
				if (full)
					System.out.print(element.getScore() + "\t");
				else
					System.out.print("0" + "\t");
			}
		}
		System.out.print("\n\n");
	}
	
	//This is the Needleman-Wunsch equation for both match and mismatches
	//This method is called when populating the matrix and checking the diagonal score of a tile
	private double score(int row, int column, String dnaSequence1, String dnaSequence2, double matchScore,
			double mismatchPenalty) {
		if (dnaSequence1.charAt(column) == dnaSequence2.charAt(row)) {
			return matchScore;
		} else {
			return mismatchPenalty;
		}
	}

	// Constructor
	public DPM(int width, int height, ArrayList<ArrayList<Element>> elements) {
		this.width = width;
		this.height = height;
		this.elements = elements;
	}

	// Default constructor, sets the two int attributes to 0 and initializes the
	// 2d Arraylist as elements will be added to it, instead of being set.
	public DPM() {
		this.width = 0;
		this.height = 0;
		this.elements = new ArrayList<ArrayList<Element>>();
	}

	// Getters and setters
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ArrayList<ArrayList<Element>> getElement() {
		return elements;
	}

	public Element getElementByIndex(int index1, int index2) {
		return elements.get(index1).get(index2);
	}

	public void setElementScoreByIndex(int row, int column, double value) {
		this.elements.get(row).get(column).setScore(value);
	}

	public void setElement(ArrayList<ArrayList<Element>> elements) {
		this.elements = elements;
	}

	// Initializes the matrix.
	// Adds Elements to elements based on the height of the width and the array.
	// All element values are set to zero
	public void initialize() {
		for (int i = 0; i < height; i++) {
			ArrayList<Element> row = new ArrayList<Element>();
			this.getElement().add(row);
			for (int j = 0; j < width; j++) {
				row.add(new Element(0, i, j));
			}
		}
	}

	public Element getLastElement() {
		return this.getElementByIndex(height - 1, width - 1);
	}

	//Populates all of the element values and directions in the matrix based on Needleman-Wunsch Algorithm
	//For more information on the Needlemen-Wunsch Algorithm go to https://en.wikipedia.org/wiki/Needleman%E2%80%93Wunsch_algorithm
	public void populate(Input input) {
		//Initializes each of the elements in the first row of the matrix, with the value of gap penalty, multiplied by its horizontal index
		//Sets each of their left directions to true
		for (int i = 1; i < this.getWidth(); i++) {
			double score;
			if (input.getGapPenalty() == 0) score = 0;
			else score = -1 * input.getGapPenalty() * i;
			this.setElementScoreByIndex(0, i, score);
			this.getElementByIndex(0, i).setLeft(true);
		}
		//Initializes each of the elements in the first column of the matrix, with the value of gap penalty, multiplied by its vertical index
		//Sets each of their up directions to true
		for (int i = 1; i < this.getHeight(); i++) {
			double score;
			if (input.getGapPenalty() == 0) score = 0;
			else score = (-1 * input.getGapPenalty() * i);
			this.setElementScoreByIndex(i, 0, score);
			this.getElementByIndex(i, 0).setUp(true);
		}
		//Uses the Needleman Wunsch equations to for each element in the array.
		//Determines which direction or directions have the highest value
		//Sets the elements value and directions
		for (int i = 1; i < this.getHeight(); i++) {
			for (int j = 1; j < this.getWidth(); j++) {
				double diag = this.getElementByIndex(i - 1, j - 1).getScore()
						+ this.score(i, j, input.getDna1().getSequence(), input.getDna2().getSequence(),
								input.getMatchScore(), input.getMismatchPenalty());
				double left = this.getElementByIndex(i, j - 1).getScore() - input.getGapPenalty();
				double up = this.getElementByIndex(i - 1, j).getScore() - input.getGapPenalty();
				Element element = this.getElementByIndex(i, j);

				if (diag >= left && diag >= up) {
					element.setDiagonal(true);
					element.setScore(diag);
				}
				if (left >= diag && left >= up) {
					element.setLeft(true);
					element.setScore(left);
				}
				if (up >= diag && up >= left) {
					element.setUp(true);
					element.setScore(up);
				}
			}
		}
	}


	//Prints out the populated matrix.
	//publicly accessible method for printing
	public void printFull(String dnaSequence1, String dnaSequence2) {
		this.print(dnaSequence1, dnaSequence2, true);
	}

	//Prints out the initialized matrix. (not populated/ all values 0)
	//publicly accessible method for printing
	public void printEmpty(String dnaSequence1, String dnaSequence2) {
		this.print(dnaSequence1, dnaSequence2, false);
	}

}
