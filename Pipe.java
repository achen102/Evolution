package Evolution;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *This class models a Pipe, and is contained within the Game class.
 */
public class Pipe {
	private ImageView _toppipe;
	private ImageView _bottompipe;
	
	/*
	 * This method constructs a pipe which is made out of two imageview nodes (one of the top pipe and one of the bottom pipe).
	 */
	public Pipe() {
		Image toppipe = new Image (this.getClass().getResourceAsStream("toppipe.png"));
		_toppipe= new ImageView();
		_toppipe.setImage(toppipe);
		_toppipe.setFitHeight(Constants.PIPE_HEIGHT);
		_toppipe.setFitWidth(Constants.PIPE_WIDTH);
		_toppipe.setY(Constants.TOPPIPE_Y);
		
		Image bottompipe= new Image (this.getClass().getResourceAsStream("bottompipe.png"));
		_bottompipe= new ImageView();
		_bottompipe.setImage(bottompipe);
		_bottompipe.setFitHeight(Constants.PIPE_HEIGHT);
		_bottompipe.setFitWidth(Constants.PIPE_WIDTH);
		_bottompipe.setY(Constants.BOTTOMPIPE_Y);
	}
	
	/*
	 * This method returns the rectangle that makes up the top pipe, and is called in the game class to get the location of the top pipe
	 */
	public ImageView gettopPipe() {
		return _toppipe;
	}
	
	/*
	 * This method returns the rectangle that makes up the bottom pipe, and is called in the game class to get the location of the bottom pipe
	 */
	public ImageView getbottomPipe() {
		return _bottompipe;
	}
	
	/*
	 * This method sets the Y location of the top pipe and the bottom pipe to a certain y, and is called in the game class.
	 */
	
	public void setYLoc(double y) {
		_toppipe.setY(y);
		_bottompipe.setY(y);
	}
	
	/*
	 * This method sets the Y location of the top pipe and the bottom pipe to a certain y, and is called in the game class.
	 */
	public void setXLoc(double x) {
		_toppipe.setX(x);
		_bottompipe.setY(x);
	}
	
	
	
}