package models;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class King extends ChessPiece {

	public King(Square square, Color color) {
		super(square, color);
	}

	@Override
	public ArrayList<Square> getPossibleSquares(ChessBoard board) {
		// TODO Auto-generated method stub
		ArrayList<Square> possibleSquares = new ArrayList<>();
		
		// for all squares around it: if valid square
		// if square in a direction is free to go to (empty): add,
		// if it has opponents piece, add
		// if it is your piece, do not add
		for(Square s : board.getBoard()){
			// up or down or diagonal
			if((s.getColumn() == _square.getColumn() || (s.getColumn() == _square.getColumn() + 1|| (s.getColumn() == _square.getColumn() - 1) && (s.getRow() == _square.getRow() + 1 || s.getRow() == _square.getRow() - 1)))){
				if(s.getPiece() == null){
					possibleSquares.add(s);
				}
				else if(!(s.getPiece()._color.equals(_color))){
					possibleSquares.add(s);
				}
			}
			// left or right
			if((s.getColumn() == _square.getColumn() + 1 || s.getColumn() == _square.getColumn() - 1) && (s.getRow() == _square.getRow())){
				if(s.getPiece() == null){
					possibleSquares.add(s);
				}
				else if(!(s.getPiece()._color.equals(_color))){
					possibleSquares.add(s);
				}
			}
		}
		return possibleSquares;
	}

}
