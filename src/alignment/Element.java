package alignment;

public class Element {

	private double score;
	private boolean left;
	private boolean up;
	private boolean diagonal;
	private int row;
	private int column;
	// change directions and location to an array if we want

	public Element(double score, boolean left, boolean up, boolean diagonal, int row, int column) {
		this.score = score;
		this.left = left;
		this.up = up;
		this.diagonal = diagonal;
		this.row = row;
		this.column = column;
	}

	public Element(double score, int row, int column) {
		this(score, false, false, false, row, column);
	}

	public Element() {
		this(0, false, false, false, 0, 0);
	}

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

	public boolean isFirst() {
		return (this.column == 0 && this.row == 0);
	}

}
