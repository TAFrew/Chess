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

	public ArrayList<Square> getBoard(){
		return _squares;
	}

	public boolean isClearPathBetween(Square square1, Square square2) {
		int smallerColumn = square1.getColumn();
		int largerColumn = square2.getColumn();
		if(smallerColumn > largerColumn){
			smallerColumn = square2.getColumn();
			largerColumn = square1.getColumn();
		}
		int smallerRow = square1.getRow();
		int largerRow = square2.getRow();
		if(smallerRow > largerRow){
			smallerRow = square2.getRow();
			largerRow = square1.getRow();
		}

		// if in same row
		if(square1.getRow() == square2.getRow()){
			for(int i = smallerColumn + 1; i < largerColumn; i++){
				if(!(getSquare(square1.getRow(),i).getPiece() == null)){
					return false;
				}
			}
			return true;
		}

		// if in same column
		if(square1.getColumn() == square2.getColumn()){
			for(int i = smallerRow + 1; i < largerRow; i++){
				if(!(getSquare(i,square1.getColumn()).getPiece() == null)){
					return false;
				}
			}
			return true;
		}

		// if in diagonal
		if(Math.abs(square1.getRow() - square2.getRow()) == Math.abs(square1.getColumn() - square2.getColumn())){
			// need some way of deciding which square to start with (probably lower row) but then whether to have for loop
			// going up or down for column
			if((smallerRow == square1.getRow() && smallerColumn == square1.getColumn())||(smallerRow == square2.getRow() && smallerColumn == square2.getColumn())){
				for(int i = 1; i < largerRow - smallerRow; i++){
					if(!(getSquare(smallerRow + i,smallerColumn + i).getPiece() == null)){
						return false;
					}
				}
				return true;
			}
			else{
				for(int i = 1; i < largerRow - smallerRow; i++){
					if(!(getSquare(smallerRow + i,largerColumn - i) == null)){
						if(!(getSquare(smallerRow + i,largerColumn - i).getPiece() == null)){
							return false;
						}	
					}
					else return false;
				}
				return true;
			}
		}

		// else false
		return false;
	}

	private Square getSquare(int row, int col) {
		for(Square s : _squares){
			if(s.getRow() == row && s.getColumn() == col){
				return s;
			}
		}
		return null;
	}

	public void movePiece(ChessPiece selectedPiece, Square newSquare) {
		Square currentSquare = selectedPiece._square;
		currentSquare.setPiece(null);
		newSquare.setPiece(selectedPiece);
		selectedPiece._square = newSquare;
		
		selectedPiece.move();
	}
}
