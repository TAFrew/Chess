package models;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Queen extends ChessPiece {

	public Queen(Square square, Color color) {
		super(square, color);
	}

	@Override
	public ArrayList<Square> getPossibleSquares(ChessBoard board) {
		int row = _square.getRow();
		int column = _square.getColumn();
		ArrayList<Square> possibleSquares = new ArrayList<>();
		
		for(Square s : board.getBoard()){
			// castle
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
			// bishop
			else if(Math.abs(s.getRow() - _square.getRow()) == Math.abs(s.getColumn() - _square.getColumn())){
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
