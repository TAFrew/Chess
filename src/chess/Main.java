package chess;

import java.util.ArrayList;

import gui.ChessBoardGUI;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.ChessBoard;
import models.ChessPiece;
import models.Square;

public class Main extends Application{

	private ChessBoard _board;

	private ChessBoardGUI _GUI;
	private Stage _stage;
	private AnimationTimer _timer;

	private Color _currentPlayer = Color.WHITE;
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
				_GUI.update(_possibleSquares);
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
				//TODO if AI
			}
		};

		_timer.start();
	}

	/**
	 * This method handles a players turn. It gets the square/piece that they have clicked on, and shows their possible moves or
	 * makes the move
	 * @param event
	 */
	protected void handleMouseClick(Event event) {
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

		// if no piece is selected, select it and show possible moves
		if(_selectedPiece == null){
			if(!(s.getPiece() == null)){
				if(s.getPiece().getColor().equals(_currentPlayer)){
					_selectedPiece = s.getPiece();
					_possibleSquares = s.getPiece().getPossibleSquares(_board);
				}
			}
		}
		else{
			// if square is in possible moves, go there
			if(_possibleSquares.contains(s)){
				// if in check, only allow moves to take king out of check
				if(_board.isInCheck(_currentPlayer)){
					// do move, then check if still in check, if so undo move and tell them they are in check still?
					Square currentSquare = _selectedPiece.getCurrentLocation();
					_board.movePiece(_selectedPiece, s);
					if(_board.isInCheck(_currentPlayer)){
						_board.movePiece(_selectedPiece, currentSquare);
					}
					else{
						_selectedPiece = null;
						_possibleSquares = new ArrayList<>();
						swapPlayer();
						handleCheck();
					}
				}
				else{
					_board.movePiece(_selectedPiece, s);
					_selectedPiece = null;
					_possibleSquares = new ArrayList<>();
					swapPlayer();
					handleCheck();
				}
			}
			// select new piece
			else if(!(s.getPiece() == null)){
				if(s.getPiece().getColor().equals(_currentPlayer)){
					_selectedPiece = s.getPiece();
					_possibleSquares = s.getPiece().getPossibleSquares(_board);
				}
			}
		}
	}

	/**
	 * This method checks whether a player is in check and informs them of this
	 */
	private void handleCheck() {
		if(_board.isInCheck(_currentPlayer)){
			_GUI.check();
		}
		else{
			_GUI.uncheck();
		}
	}

	/**
	 * This method swaps the color of the current player
	 */
	private void swapPlayer() {
		if(_currentPlayer.equals(Color.WHITE)){
			_currentPlayer = Color.BLACK;
		}
		else if(_currentPlayer.equals(Color.BLACK)){
			_currentPlayer = Color.WHITE;
		}
	}

	/**
	 * This method is called when the application starts. It initializes some fields for the main class
	 */
	private void initialize() {
		_board = new ChessBoard();
		_GUI = new ChessBoardGUI(_board);
	}
}
