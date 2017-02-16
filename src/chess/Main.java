package chess;

import java.util.ArrayList;

import gui.ChessBoardGUI;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.ChessBoard;
import models.ChessPiece;
import models.Square;

public class Main extends Application{

	private ChessBoard _board;

	private ChessBoardGUI _GUI;
	private Stage _stage;
	private AnimationTimer _timer;

	private int _turn = 1;
	private ChessPiece _selectedPiece;
	ArrayList<Square> _possibleSquares = new ArrayList<>();
	boolean _showPossibleMoves = false;

	/**
	 * The main method launches the application
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * This method is called when the application starts and initializes the GUI, mouse listener and animation timer
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		initialize();

		Scene scene = new Scene(_GUI.getPane());
		scene.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				handleMouseClick(event);
				_GUI.update();
			}
		});

		// stage
		_stage = primaryStage;
		_stage.setTitle("Chess");
		_stage.setScene(scene);
		_stage.show();

		// animation timer
		_timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				// TODO
				if(_showPossibleMoves){
					_GUI.showPossibleMoves(_possibleSquares);
				}
			}
		};

		_timer.start();
	}

	/**
	 * This method handles a players turn. It gets the square that they have clicked on, and if they can go here it does so.
	 * @param event
	 */
	protected void handleMouseClick(Event event) {
		//TODO
		// get coordinates of mouse click event
		MouseEvent me = (MouseEvent) event;
		double horizontal = me.getSceneX();
		double vertical = me.getSceneY();

		// get square
		int count = 0;
		int finalCount = -1;
		for(int y = 7; y >= 0; y--){
			for(int x = 1; x < 9; x++){
				if(horizontal <= x * 90 && vertical >= y * 90){
					if(finalCount == -1){
						finalCount = count;
					}
				}
				count++;
			}
		}
		
		Square s = _board.getBoard().get(finalCount);
		
		// else : if square contians piece of players: show possible moves on GUI	
		if(_selectedPiece == null){
			if(!(s.getPiece() == null)){
				_possibleSquares = s.getPiece().getPossibleSquares(_board);
				_showPossibleMoves = true;
			}
		}

		// if() piece is already selected, check if they have clicked in list of possible squares, if so do the move, 
		// update the board model, and then update the GUI
		
	}

	/**
	 * This method is called when the application starts. It initializes some fields for the main class
	 */
	private void initialize() {
		_board = new ChessBoard();
		_GUI = new ChessBoardGUI(_board);
	}
}
