package racingDrivers.driver;

public class Leading implements DriverStateI{
	public DriverStateI getState(int position, int n){
		if(Math.round(0.3 * n)<=position && position<(0.7 * n) ){
			return new Holding_On();
		}
		else if(position >= Math.round(0.7*n)){
			return new Losing();
		}
		else{
			return this;
		}
	}

	public String toString() {
		return "CONFIDENT";
	}
} 