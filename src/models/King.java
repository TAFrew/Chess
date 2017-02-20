package models;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.scene.paint.Color;

public class King extends ChessPiece {

	public King(Square square, Color color) {
		super(square, color);
	}

	@Override
	public ArrayList<Square> getPossibleSquares(ChessBoard board) {
		ArrayList<Square> possibleSquares = new ArrayList<>();

		// for all squares around it: if valid square
		// if square in a direction is free to go to (empty): add,
		// if it has opponents piece, add
		// if it is your piece, do not add
		for(Square s : board.getBoard()){
			// up or down or diagonal
			if(((s.getRow() == _square.getRow() + 1)||(s.getRow() == _square.getRow() - 1)) && ((s.getColumn() == _square.getColumn() + 1)||(s.getColumn() == _square.getColumn())||(s.getColumn() == _square.getColumn() - 1))){
				if(s.getPiece() == null){
					possibleSquares.add(s);
				}
				else if(!(s.getPiece()._color.equals(_color))){
					possibleSquares.add(s);
				}
			}
			// left or right
			if(((s.getColumn() == _square.getColumn() + 1) || (s.getColumn() == _square.getColumn() - 1)) && (s.getRow() == _square.getRow())){
				if(s.getPiece() == null){
					possibleSquares.add(s);
				}
				else if(!(s.getPiece()._color.equals(_color))){
					possibleSquares.add(s);
				}
			}
		}
		
		ArrayList<Square> opponentsMoves = new ArrayList<>();
		// remove any squares that would put you in check
		for(Square s : board.getBoard()){
			if(!(s.getPiece() == null)){
				if(!(s.getPiece().getColor().equals(_color))){
					if(!(s.getPiece() instanceof King)){
						opponentsMoves.addAll(s.getPiece().getPossibleSquares(board));
					}
				}
			}
		}
		
		possibleSquares.removeAll(opponentsMoves);
		
		return possibleSquares;
	}
}
