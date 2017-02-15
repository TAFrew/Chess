package models;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public abstract class ChessPiece {
	
	private Square _square;
	private ArrayList<Square> _possibleMoves;
	private Color _color;
	
	public ChessPiece(Square square, Color color) {
		_square = square;
		_color = color;
	}
	
	public abstract ArrayList<Square> getPossibleSquares();
	
	// chess piece needs to have squares it can move to from current one
}
