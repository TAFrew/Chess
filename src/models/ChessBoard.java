package models;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class ChessBoard {
	// has array of squares. squares each CAN contain a piece
	// square has coordinates too -> 0,0 through to 7,7

	private ArrayList<Square> _squares;

	public ChessBoard(){
		_squares = new ArrayList<>();
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				_squares.add(new Square(row,col));
			}
		}

		for(Square s : _squares){
			// fill in pawns
			if(s.getRow() == 1){
				s.setPiece(new Pawn(true, Color.WHITE));
			}
			if(s.getRow() == 6){
				s.setPiece(new Pawn(true, Color.BLACK));
			}
			
			// fill in castles
			if((s.getRow() == 0) && (s.getColumn() == 0 || s.getColumn() == 7)){
				s.setPiece(new Castle(true, Color.WHITE));
			}
			if((s.getRow() == 7) && (s.getColumn() == 0 || s.getColumn() == 7)){
				s.setPiece(new Castle(true, Color.BLACK));
			}
			
			// fill in knights
			if((s.getRow() == 0) && (s.getColumn() == 1 || s.getColumn() == 6)){
				s.setPiece(new Knight(true, Color.WHITE));
			}
			if((s.getRow() == 7) && (s.getColumn() == 1 || s.getColumn() == 6)){
				s.setPiece(new Knight(true, Color.BLACK));
			}
			
			// fill in bishops
			if((s.getRow() == 0) && (s.getColumn() == 2 || s.getColumn() == 5)){
				s.setPiece(new Bishop(true, Color.WHITE));
			}
			if((s.getRow() == 7) && (s.getColumn() == 2 || s.getColumn() == 5)){
				s.setPiece(new Bishop(true, Color.BLACK));
			}
			
			// fill in queens
			if((s.getRow() == 0) && (s.getColumn() == 3)){
				s.setPiece(new Queen(true, Color.WHITE));
			}
			if((s.getRow() == 7) && (s.getColumn() == 3)){
				s.setPiece(new Queen(true, Color.BLACK));
			}
			
			// fill in kings
			if((s.getRow() == 0) && (s.getColumn() == 4)){
				s.setPiece(new King(true, Color.WHITE));
			}
			if((s.getRow() == 7) && (s.getColumn() == 4)){
				s.setPiece(new King(true, Color.BLACK));
			}
		}

	}
}
