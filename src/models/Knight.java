package models;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Knight extends ChessPiece {

	public Knight(Square square, Color color) {
		super(square, color);
	}

	@Override
	public ArrayList<Square> getPossibleSquares(ChessBoard board) {
		// 8 possible moves:
		// 1)r + 2, c + 1
		// 2)r + 2, c - 1
		// 3)r - 2, c + 1
		// 4)r - 2, c - 1
		// 5)c + 2, r + 1
		// 6)c + 2, r - 1
		// 7)c - 2, r + 1
		// 8)c - 2, r - 1
		// if square in a direction is free to go to (empty): add,
		// if it has opponents piece, add
		// if it is your piece, do not add
		ArrayList<Square> possibleSquares = new ArrayList<>();
		
		for(Square s : board.getBoard()){
			if((s.getRow() == _square.getRow() + 2 || s.getRow() == _square.getRow() - 2)&&((s.getColumn() == _square.getColumn() + 1 || s.getColumn() == _square.getColumn() - 1))){
				if(s.getPiece() == null){
					possibleSquares.add(s);
				}
				else if(!(s.getPiece()._color.equals(_color))){
					possibleSquares.add(s);
				}
			}
			if((s.getRow() == _square.getRow() + 1 || s.getRow() == _square.getRow() - 1)&&((s.getColumn() == _square.getColumn() + 2 || s.getColumn() == _square.getColumn() - 2))){
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
