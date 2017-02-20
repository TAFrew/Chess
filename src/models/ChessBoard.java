package models;

import java.util.ArrayList;

import gui.PieceSelectionGUI;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChessBoard {
	// has array of squares. squares each CAN contain a piece
	// square has coordinates too -> 0,0 through to 7,7

	private ArrayList<Square> _squares;
	private ChessPiece _transformedPiece;
	private ChessPiece _pieceToTransform;

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
		
		if(selectedPiece instanceof Pawn){
			if(newSquare.getRow() == 0 || newSquare.getRow() == 7){
				// player can transform pawn
				_pieceToTransform = selectedPiece;
				selectNewPiece(selectedPiece);
				ChessPiece cp = _transformedPiece;
				newSquare.setPiece(cp);
			}
		}
	}

	private void selectNewPiece(ChessPiece selectedPiece) {
		// initialize GUI
		PieceSelectionGUI gui = new PieceSelectionGUI();
		Stage newStage = new Stage();
		Scene scene = new Scene(gui.getPane());
		scene.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// get coordinates of mouse click event
				handleClick(event);
				newStage.close();
			}
		});
		newStage.setTitle("Choose a piece");
		newStage.setScene(scene);
		newStage.showAndWait();
	}

	protected void handleClick(Event event) {
		MouseEvent me = (MouseEvent) event;
		double horizontal = me.getSceneX();
		double vertical = me.getSceneY();
		
		if(horizontal > 40 && horizontal < 140 && vertical > 100 & vertical < 200){
			_transformedPiece = new Queen(_pieceToTransform.getCurrentLocation(), _pieceToTransform.getColor());
		}
		else if(horizontal > 180 && horizontal < 280 && vertical > 100 & vertical < 200){
			_transformedPiece = new Castle(_pieceToTransform.getCurrentLocation(), _pieceToTransform.getColor());
		}
		else if(horizontal > 320 && horizontal < 420 && vertical > 100 & vertical < 200){
			_transformedPiece = new Bishop(_pieceToTransform.getCurrentLocation(), _pieceToTransform.getColor());
		}
		else if(horizontal > 460 && horizontal < 560 && vertical > 100 & vertical < 200){
			_transformedPiece = new Knight(_pieceToTransform.getCurrentLocation(), _pieceToTransform.getColor());
		}
	}

	public boolean isInCheck(Color player){
		King king = null;

		// get the king piece
		for(Square s : _squares){
			if(!(s.getPiece() == null)){
				if(s.getPiece() instanceof King){
					if(s.getPiece().getColor().equals(player)){
						king = (King)s.getPiece();
					}
				}
			}
		}

		// for all pieces of the other colour
		for(Square s : _squares){
			if(!(s.getPiece() == null)){
				if(!(s.getPiece().getColor().equals(player))){
					// if their possible moves contains the square the king is in return true
					if(s.getPiece().getPossibleSquares(this).contains(king._square)){
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isInCheckMate(Color player){
		if(isInCheck(player)){
			// TODO this does not move pieces back afterwards
			// if cant move,
			// and cant block 
			// and cant take piece:
			// return true
			for(Square square : _squares){
				if(!(square.getPiece() == null)){
					if(square.getPiece().getColor().equals(player)){
						for(Square s : square.getPiece().getPossibleSquares(this)){
							movePiece(square.getPiece(), s);
							if((isInCheck(player))){
								return false;
							}
							movePiece(square.getPiece(), square);
						}
					}
				}
			}
			return true;
		}
		else return false;
	}
}
