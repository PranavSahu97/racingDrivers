package racingDrivers.driver; 


public class Holding_On implements DriverStateI{
	public DriverStateI getState(int position, int n){
		if(position <(0.3 * n)){
			return new Leading();
		}
		else if(position >= Math.round(0.7*n)){
			return new Losing();
		}
		else{
			return this;
		}		
	}
	
	public String toString() {
		return "CALCULATIVE"
	}
	
} 