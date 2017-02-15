package models;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class ChessPiece {
	
	private boolean _inPlay;
	private Square _square;
	private ArrayList<Square> _possibleMoves;
	private Color _color;
	
	public ChessPiece(boolean inPlay, Color color) {
		_inPlay = inPlay;
		_color = color;
	}
	// chess piece needs to have squares it can move to from current one
}
