package Evolution;
public class NeuralNetwork {
		
		private double [] _hiddenlayer;
	public NeuralNetwork() {
		
	
		
	}

	
	public double calculate(double[] _inputs, double[][]_syn0, double[][] _syn1) {
		_hiddenlayer= new double [5];
		
		double outputsum=0;
		//calculate value of nodes in hidden layer 1
		
		/*_hiddenlayer[0]=_inputs[0]*_syn0[0][0]+_inputs[1]*_syn0[0][1]+_inputs[2]*_syn0[0][2];
		_hiddenlayer[1]=_inputs[0]*_syn0[1][0]+_inputs[1]*_syn0[1][1]+_inputs[2]*_syn0[1][2];
		_hiddenlayer[2]=_inputs[0]*_syn0[2][0]+_inputs[1]*_syn0[2][1]+_inputs[2]*_syn0[2][2];
		_hiddenlayer[3]=_inputs[0]*_syn0[3][0]+_inputs[1]*_syn0[3][1]+_inputs[2]*_syn0[3][2];
		_hiddenlayer[4]=_inputs[0]*_syn0[4][0]+_inputs[1]*_syn0[4][1]+_inputs[2]*_syn0[4][2];*/
		for (int i=0; i<_hiddenlayer.length; i++) {
				
			for (int j =0; j<_syn0[0].length; j++) {
				_hiddenlayer[i]=_hiddenlayer[i]+(_inputs[j]*_syn0[i][j]);
				
			}
			
			
			double outputvalue = _hiddenlayer[i]* _syn1[0][i];
			outputsum =outputsum + outputvalue;
		}

		
		//puts output sum through sigmoid function & returns output
		return 1/(1+Math.exp(-outputsum));

		
		}


			
	}
	
