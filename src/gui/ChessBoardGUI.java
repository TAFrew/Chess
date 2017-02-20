package gui;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
	private Text _t;

	private ArrayList<Node> _squares = new ArrayList<>();

	public Pane getPane(){
		return _app;
	}

	public ChessBoardGUI(ChessBoard board){
		initialize(board);
	}

	public void update(ArrayList<Square> possibleSquares){
		for(int i = 0; i < _board.getBoard().size(); i++){
			Square s = _board.getBoard().get(i);
			if(!(s.getPiece() == null)){
				Rectangle r = (Rectangle)_squares.get(i);
				String colour = "";
				String piece = "";
				if(s.getPiece().getColor().equals(Color.WHITE)){
					colour = "White";
				}
				else{
					colour = "Black";
				}
				if(s.getPiece() instanceof Pawn){
					piece = "Pawn";
				}
				else if(s.getPiece() instanceof Castle){
					piece = "Castle";
				}
				else if(s.getPiece() instanceof Knight){
					piece = "Knight";
				}
				else if(s.getPiece() instanceof Bishop){
					piece = "Bishop";
				}
				else if(s.getPiece() instanceof Queen){
					piece = "Queen";
				}
				else if(s.getPiece() instanceof King){
					piece = "King";
				}
				Image image = new Image("file:src/Images/" + colour + piece + ".png");
				r.setFill(new ImagePattern(image));
			}
			else{
				Rectangle r = (Rectangle)_squares.get(i);
				r.setFill(Color.WHITE);
			}
		}

		for(Node n : _squares){
			Rectangle r = (Rectangle)n;
			if(!(r.getStroke() == null)){
				if(r.getStroke().equals(Color.BLUE)){
					r.setStroke(Color.WHITE);
				}
			}
		}
		for(Square s : possibleSquares){
			Rectangle r = (Rectangle)_squares.get((s.getRow() * 8) + s.getColumn());
			r.setStroke(Color.BLUE);
			r.setStrokeWidth(5.0);
		}
	}

	private void initialize(ChessBoard board){
		_board = board;

		_t = new Text(350, 350, "Check!");
		_t.setScaleX(8.0);
		_t.setScaleY(5.0);
		_t.setFill(Color.INDIANRED);

		Rectangle rect = new Rectangle(800,800, Color.GREY);
		_gamePane.getChildren().add(rect);

		for(int y = 8; y >= 0; y--){
			for(int x = 0; x < 8; x++){
				Rectangle r = (Rectangle)createEntity(x*90 + 10, y*90 - 90, 80, 80, Color.WHITE);
				r.setStroke(Color.WHITE);
				r.setStrokeWidth(5.0);
				_squares.add(r);
			}
		}

		_app.getChildren().add(_gamePane);

		update(new ArrayList<>());
	}

	private Node createEntity(int x, int y, int w, int h, Color colour) {
		Rectangle entity = new Rectangle(w, h);
		entity.setTranslateX(x);
		entity.setTranslateY(y);
		entity.setFill(colour);

		_gamePane.getChildren().add(entity);
		return entity;
	}

	public void check() {
		_gamePane.getChildren().add(_t);
	}
	
	public void uncheck(){
		_gamePane.getChildren().remove(_t);
	}

}
