package alignment;

public class Element {

	// score is the value of the matrix index determined by Needleman-Wunsch
	private double score;
	// These three booleans represent directions that the cell can contain
	// All valid cells (other than index 0, 0) should have 1 - 3 of these be
	// true
	private boolean left;
	private boolean up;
	private boolean diagonal;
	// The row of the matrix that this element is contained within
	private int row;
	// The column of the matrix that this element is contained within
	private int column;

	// Constructor takes arguments score, left, up, diagonal, row and column
	public Element(double score, boolean left, boolean up, boolean diagonal, int row, int column) {
		this.score = score;
		this.left = left;
		this.up = up;
		this.diagonal = diagonal;
		this.row = row;
		this.column = column;
	}

	// Overloaded constructor setting all values except for direction
	public Element(double score, int row, int column) {
		this(score, false, false, false, row, column);
	}

	// Default constructor sets everything to false, or 0
	public Element() {
		this(0, false, false, false, 0, 0);
	}

	// Getters and setters
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDiagonal() {
		return diagonal;
	}

	public void setDiagonal(boolean diagonal) {
		this.diagonal = diagonal;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	// Checks to see if the Element is the first element in the matrix (index 0,
	// 0)
	public boolean isFirst() {
		return (this.column == 0 && this.row == 0);
	}

}
