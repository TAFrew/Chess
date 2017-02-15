package models;


public class Square {
	private int _row;
	private int _column;
	private ChessPiece _piece;
	
	public Square(int x, int y){
		_row = x;
		_column = y;
	}

	public String toString(){
		return _row + "," + _column;
	}

	public int getRow(){
		return _row;
	}

	public int getColumn(){
		return _column;
	}
	
	public void setPiece(ChessPiece piece){
		_piece = piece;
	}
}
