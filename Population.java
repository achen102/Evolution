package Evolution;

import java.util.ArrayList;
import java.util.Stack;

import javafx.scene.layout.Pane;

public class Population{
	private ArrayList <Bird> _livebirds;
	private Stack <Bird> _deadbirds;
	public Population() {
		_deadbirds= new Stack<Bird>();
		}
	
	//this method initializes 50 birds as the population and adds them to the arraylist of live birds
	public void createBirds(Pane _gamepane) {
		
		_livebirds = new ArrayList<Bird>(50);
		
		for (int i = 0; i<50; i++) {
			
			Bird bird = new Bird();
			_livebirds.add(i,bird);
			
			_gamepane.getChildren().add(_livebirds.get(i).getBirdshape());
			
	}
	}
	
	//this method returns the arraylist of live birds
	public ArrayList<Bird> getLiveBirds() {
		return _livebirds;
	}
	
	//this method returns the stack of dead birds
	public Stack<Bird> getDeadBirds(){
		return _deadbirds;
	}
	
	
}