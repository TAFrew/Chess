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
				Pawn p = new Pawn(s, Color.WHITE);
				s.setPiece(p);
			}
			if(s.getRow() == 6){
				Pawn p = new Pawn(s, Color.BLACK);
				s.setPiece(p);
			}

			// fill in castles
			if((s.getRow() == 0) && (s.getColumn() == 0 || s.getColumn() == 7)){
				Castle c = new Castle(s, Color.WHITE);
				s.setPiece(c);
			}
			if((s.getRow() == 7) && (s.getColumn() == 0 || s.getColumn() == 7)){
				Castle c = new Castle(s, Color.BLACK);
				s.setPiece(c);
			}

			// fill in knights
			if((s.getRow() == 0) && (s.getColumn() == 1 || s.getColumn() == 6)){
				Knight k = new Knight(s, Color.WHITE);
				s.setPiece(k);
			}
			if((s.getRow() == 7) && (s.getColumn() == 1 || s.getColumn() == 6)){
				Knight k = new Knight(s, Color.BLACK);
				s.setPiece(k);
			}

			// fill in bishops
			if((s.getRow() == 0) && (s.getColumn() == 2 || s.getColumn() == 5)){
				Bishop b = new Bishop(s, Color.WHITE);
				s.setPiece(b);
			}
			if((s.getRow() == 7) && (s.getColumn() == 2 || s.getColumn() == 5)){
				Bishop b = new Bishop(s, Color.BLACK);
				s.setPiece(b);
			}

			// fill in queens
			if((s.getRow() == 0) && (s.getColumn() == 3)){
				Queen q = new Queen(s, Color.WHITE);
				s.setPiece(q);
			}
			if((s.getRow() == 7) && (s.getColumn() == 3)){
				Queen q = new Queen(s, Color.BLACK);
				s.setPiece(q);
			}

			// fill in kings
			if((s.getRow() == 0) && (s.getColumn() == 4)){
				King k = new King(s, Color.WHITE);
				s.setPiece(k);
			}
			if((s.getRow() == 7) && (s.getColumn() == 4)){
				King k = new King(s, Color.BLACK);
				s.setPiece(k);
			}
		}

	}
}
