package models;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public abstract class ChessPiece {
	
	protected Square _square;
	protected ArrayList<Square> _possibleMoves;
	protected Color _color;
	
	public ChessPiece(Square square, Color color) {
		_square = square;
		_color = color;
	}
	
	public abstract ArrayList<Square> getPossibleSquares(ChessBoard board);
	
	// chess piece needs to have squares it can move to from current one
}
