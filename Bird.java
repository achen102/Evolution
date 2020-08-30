package Evolution;

import java.util.LinkedList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *This class models a Bird, and is contained within the Game class.
 */
public class Bird{
	private ImageView _birdshape;
	private double _velocity;
	private double []_inputs;
	private double [][] _syn0;
	private double [][] _syn1;
	private NeuralNetwork _neuralnetwork;
	
	/*
	 * This method constructs a bird, which is made out of an imageview, with an imported image. 
	 */
	public Bird() {
	    Image bird = new Image(this.getClass().getResourceAsStream("bird.png"));
		_birdshape = new ImageView();
		_birdshape.setImage(bird);
		_birdshape.setFitWidth(Constants.BIRD_SIZE*3);
		_birdshape.setPreserveRatio(true);
		_birdshape.setX(Constants.BIRD_X);
		_birdshape.setY(Constants.BIRD_Y);
		_birdshape.setOpacity(.3);
		_neuralnetwork= new NeuralNetwork();
		_syn0 = new double [5][3];
		_syn1 = new double [1][5];
		this.createWeight();
		
	}
	
	
	/*
	 * This method returns the ImageView node of the bird, and is used in the game class in order to access the bird's location on the pane.
	 */	
	public ImageView getBirdshape() {
		return _birdshape;
	}
	

	/*
	 * This method causes the bird to graphically fall due to gravity when the bird is above the bottom of the gamepane (so still in the gamepane).
	 */
	public void updateBird() {
		if (_birdshape.getY()<=Constants.GAME_HEIGHT) {
		_velocity= _velocity + Constants.GRAVITY * Constants.DURATION;
		 double position = _birdshape.getY();
		_birdshape.setY(position + _velocity * Constants.DURATION);
		
		}
	}
	
	/*
	 * This method returns the current velocity of the bird
	 */
	public double getVelocity() {
		return _velocity;
	}
	
	/*
	 * This method sets the velocity to a certain value, and is used in the decidetojump method in the Bird class and the KeyHandler handle
	 * method in the game class in order to set velocity to a rebound velocity & cause the bird to jump.
	 */
	public void setVelocity (double velocity) {
		_velocity = velocity;
		
	}
	
	/*
	 * This method returns the number of times the timeline was updated as a way to measure how long the bird was alive
	 */
	public double getFitness(double _timealive) {
		return _timealive;
	}
	
	/*
	 * This method uses Math.random as a way to create new weights for a certain fraction of random birds from the total population(the mutation rate constant).
	 */
	public void mutate(double _mutationrate) {
		if (Math.random()<_mutationrate) {
			this.createWeight();
			
			
		}
	}
	
	
	/*
	 * This method sets the initializes the three inputs, which are then added to a 1D array
	 */
	public void setinputs(LinkedList <Pipe> _pipelist, Pipe _initialpipe) {
		double pipexdistance=0;
		//taking into account the situation when there is only one pipe (no middle pipe)
		 if (_pipelist.size()==1) {
			 double pipe1x=_initialpipe.getbottomPipe().getX();
			 double birdx= _birdshape.getX();
			pipexdistance = (pipe1x-birdx)/Constants.GAME_HEIGHT;
		 }
		//for all other situations when there is more than one pipe
		 else {
		double pipe1x= _pipelist.get(1).getbottomPipe().getX();
		double birdx= _birdshape.getX();
		pipexdistance = (pipe1x-birdx)/Constants.GAME_HEIGHT;
		 }
		 
		//taking into account the situation when there is only one pipe (no middle pipe)
		
		 double pipeydistance=0;
		 if (_pipelist.size()==1) {
			 double pipe1y=_initialpipe.getbottomPipe().getY();
			 double birdy= _birdshape.getY();
			pipeydistance = (pipe1y-birdy)/Constants.GAME_HEIGHT;
		 }
		//for all other situations when there is more than one pipe
		 else {
		double pipe1y= _pipelist.get(1).getbottomPipe().getY();
		double birdy= _birdshape.getY();
		pipeydistance = (pipe1y-birdy)/Constants.GAME_HEIGHT;
		 }
		 
		double birdheight= (Constants.GAME_HEIGHT-_birdshape.getY())/Constants.GAME_HEIGHT;
		_inputs = new double [3];
		_inputs[0]= pipexdistance;
		_inputs [1]=pipeydistance;
		_inputs [2] =birdheight;
	}
	
	/*
	 * This method creates the two weight arrays which are used in the calculate method
	 */
	private void createWeight() {
		
		double max = 1;
		double min = -1;
		double range = max-min;
		//first weights array (3x5)
		
		for (int i=0; i<_syn0.length; i++) {
			for (int j=0; j<_syn0[0].length; j++) {
		_syn0[i][j]= (double)(Math.random()* range)+min;
			}
		}
		//second weights array (1x5)
		
		for (int k=0; k<_syn1[0].length; k++) {
			
		_syn1[0][k]= (double)(Math.random()* range)+min;
		
		}
	}
	
	/*
	 * This method creates the two weight arrays which are used in the calculate method
	 */
	public void setWeight(double[][] newsyn0, double [][]newsyn1) {
		//sets weight of syn0
		for (int i=0; i<_syn0.length; i++) {
			for (int j=0; j<_syn0[0].length; j++) {
				_syn0[i][j]= newsyn0[i][j];
			}
		}
		//sets weight of syn1
		for (int i=0; i<_syn1[0].length; i++) {
			
			_syn1[0][i]=newsyn1[0][i];
			}
		
			
	}
	
	/*
	 * This method returns the syn0 array, and is called in the setupgame method in the game class.
	 */
	public double[][] getsyn0Weight() {
	
		return _syn0;
	}
	
	/*
	 * This method returns the syn0 array, and is called in the setupgame method in the game class.
	 */
	public double[][]getsyn1Weight() {
		
		return _syn1;
	}

	
	/*
	 * This method calls upon the neural network's method calculate, which returns an output and tells the bird to jump if the output is less than or equal to .5
	 */
	public void decideToJump(){
	
		if (_neuralnetwork.calculate(_inputs,_syn0,_syn1)<=.5) {
			this.setVelocity(Constants.REBOUND_VELOCITY);
		}
	}
}
