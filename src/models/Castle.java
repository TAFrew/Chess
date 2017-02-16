package models;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Castle extends ChessPiece {

	public Castle(Square square, Color color) {
		super(square,color);
	}

	@Override
	public ArrayList<Square> getPossibleSquares(ChessBoard board) {
		int row = _square.getRow();
		int column = _square.getColumn();
		ArrayList<Square> possibleSquares = new ArrayList<>();
		
		// for all squares in same row, or in same column:
		// if square in a direction is free to go to (empty) go onto next square,
		// if it has opponents piece, add s but stop there
		// if it is your piece, do not add s and stop there
		for(Square s : board.getBoard()){
			if(s.getRow() == row || s.getColumn() == column){
				if(board.isClearPathBetween(_square,s)){
					if(s.getPiece() == null){
						possibleSquares.add(s);
					}
					else if(!(s.getPiece()._color.equals(_color))){
						possibleSquares.add(s);
					}
				}
			}
		}
		
		return possibleSquares;
	}

}
