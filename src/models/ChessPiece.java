package models;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public abstract class ChessPiece {
	
	protected Square _square;
	protected ArrayList<Square> _possibleMoves;
	protected Color _color;
	protected boolean _hasMoved;
	
	public ChessPiece(Square square, Color color) {
		_square = square;
		_color = color;
		_hasMoved = false;
	}
	
	public abstract ArrayList<Square> getPossibleSquares(ChessBoard board);
	
	public void move(){
		_hasMoved = true;
	}
	
	public Color getColor(){
		return _color;
	}
	
	// chess piece needs to have squares it can move to from current one
}
