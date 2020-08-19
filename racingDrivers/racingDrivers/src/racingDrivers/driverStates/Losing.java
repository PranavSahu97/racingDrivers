package racingDrivers.driver;

public class Losing implements DriverStateI{
	public DriverStateI getState(int position, int n){
		if(position <(0.3 * n)){
			return new Leading();
		}
		else if(Math.round(0.3*n)<=position && position<Math.round(0.7*n)){
			return new Holding_On();
		}
		else{
			return this;
		}
	}

	public String toString() {
		return "RECKLESS";
	}
} 