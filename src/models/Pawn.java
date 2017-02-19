package models;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Pawn extends ChessPiece{

	public Pawn(Square square, Color color) {
		super(square, color);
	}

	@Override
	public ArrayList<Square> getPossibleSquares(ChessBoard board) {
		// TODO en passant
		// check color, if black can go row + 1.
		// if white, can go row - 1.
		ArrayList<Square> possibleSquares = new ArrayList<>();
		int direction = 0;
		if(_color.equals(Color.WHITE)){
			direction = 1;
		}
		else if(_color.equals(Color.BLACK)){
			direction = -1;
		}

		// check if hasnt moved. have moved boolen or some shit in pawn class.
		// if has, only r + 1, else r + 2 and r + 1
		for(Square s : board.getBoard()){
			if(s.getColumn() == _square.getColumn() && s.getRow() == _square.getRow() + direction){
				if(s.getPiece() == null){
					possibleSquares.add(s);
				}
			}
			if(!(_hasMoved)){
				if(s.getColumn() == _square.getColumn() && s.getRow() == _square.getRow() + (2 * direction)){
					if(s.getPiece() == null && board.isClearPathBetween(s, _square)){
						possibleSquares.add(s);
					}
				}
			}
			
			// check if it can take piece: row +/- 1 and col +- 1
			if((s.getColumn() == _square.getColumn() + 1 || s.getColumn() == _square.getColumn() - 1) && s.getRow() == _square.getRow() + direction){
				if(!(s.getPiece() == null)){
					if(!(s.getPiece()._color.equals(_color))){
						possibleSquares.add(s);
					}
				}
			}
		}

		return possibleSquares;
	}
}
