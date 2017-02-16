package gui;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import models.Bishop;
import models.Castle;
import models.ChessBoard;
import models.King;
import models.Knight;
import models.Pawn;
import models.Queen;
import models.Square;

public class ChessBoardGUI {
	private ChessBoard _board;
	private Pane _app = new Pane();
	private Pane _gamePane = new Pane();

	private ArrayList<Node> _squares = new ArrayList<>();

	public Pane getPane(){
		return _app;
	}

	public ChessBoardGUI(ChessBoard board){
		initialize(board);
	}

	public void update(){
		for(int i = 0; i < _board.getBoard().size(); i++){
			Square s = _board.getBoard().get(i);
			if(!(s.getPiece() == null)){
				Rectangle r = (Rectangle)_squares.get(i);
				if(s.getPiece() instanceof Pawn){
					Image image = new Image("file:src/Images/Pawn.png");
					r.setFill(new ImagePattern(image));
				}
				else if(s.getPiece() instanceof Castle){
					Image image = new Image("file:src/Images/Castle.png");
					r.setFill(new ImagePattern(image));
				}
				else if(s.getPiece() instanceof Knight){
					Image image = new Image("file:src/Images/Knight.png");
					r.setFill(new ImagePattern(image));
				}
				else if(s.getPiece() instanceof Bishop){
					Image image = new Image("file:src/Images/Bishop.png");
					r.setFill(new ImagePattern(image));
				}
				else if(s.getPiece() instanceof Queen){
					Image image = new Image("file:src/Images/Queen.png");
					r.setFill(new ImagePattern(image));
				}
				else if(s.getPiece() instanceof King){
					Image image = new Image("file:src/Images/King.png");
					r.setFill(new ImagePattern(image));
				}
			}
		}
	}

	public void showPossibleMoves(ArrayList<Square> possibleSquares){
		for(Square s : possibleSquares){
			int row = s.getRow();
			int col = s.getColumn();
			Rectangle r = (Rectangle)_squares.get((s.getRow() * 8) + s.getColumn());
			// perhaps here create blue piece thing to go in rect and then when unselected, go back to normal
			r.setFill(Color.LIGHTCORAL);
		}
	}
	
	private void initialize(ChessBoard board){
		_board = board;


		Rectangle rect = new Rectangle(800,800, Color.GREY);
		_gamePane.getChildren().add(rect);

		for(int y = 8; y >= 0; y--){
			for(int x = 0; x < 8; x++){
				_squares.add(createEntity(x*90 + 10, y*90 - 90, 80, 80, Color.WHITE));
			}
		}

		_app.getChildren().add(_gamePane);
		
		update();
	}

	private Node createEntity(int x, int y, int w, int h, Color colour) {
		Rectangle entity = new Rectangle(w, h);
		entity.setTranslateX(x);
		entity.setTranslateY(y);
		entity.setFill(colour);

		_gamePane.getChildren().add(entity);
		return entity;
	}

}
