package models;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Bishop extends ChessPiece {

	public Bishop(Square square, Color color) {
		super(square, color);
	}

	@Override
	public ArrayList<Square> getPossibleSquares(ChessBoard board) {
		// for i = 1 -> 7 : if s.r + i < 8 && s.c + i < 8 and opposite for -i . actually will have to probably do more checks to see if square exists.. or not actually never mind we shall see
		// if square in a direction is free to go to (empty) go onto next square,
		// if it has opponents piece, add s but stop there
		// if it is your piece, do not add s and stop there
		ArrayList<Square> possibleSquares = new ArrayList<>();
		
		for(Square s : board.getBoard()){
			// check if in diagonal
			if(Math.abs(s.getRow() - _square.getRow()) == Math.abs(s.getColumn() - _square.getColumn())){
				if(board.isClearPathBetween(s, _square)){
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
