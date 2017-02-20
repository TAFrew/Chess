package gui;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class PieceSelectionGUI {
	private Pane _app = new Pane();
	private Pane _gamePane = new Pane();

	public Pane getPane(){
		return _app;
	}

	public PieceSelectionGUI(){
		initialize();
	}


	private void initialize(){
		Rectangle rect = new Rectangle(600,300, Color.GREY);
		_gamePane.getChildren().add(rect);

		createEntity(40, 100, 100, 100, "Queen");
		createEntity(180, 100, 100, 100, "Castle");
		createEntity(320, 100, 100, 100, "Bishop");
		createEntity(460, 100, 100, 100, "Knight");
		
		_app.getChildren().add(_gamePane);

	}

	private Node createEntity(int x, int y, int w, int h, String piece) {
		Rectangle entity = new Rectangle(w, h);
		entity.setTranslateX(x);
		entity.setTranslateY(y);
		Image image = new Image("file:src/Images/White" + piece + ".png");
		entity.setFill(new ImagePattern(image));

		_gamePane.getChildren().add(entity);
		return entity;
	}

}
