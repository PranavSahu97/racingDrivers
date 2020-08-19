package racingDrivers.driver;

public class DriverContext{
	DriverStateI currentState;
	int position;
	double miles;
	
	public DriverContext(){
		currentState=new Losing(); 
		position=-1;
		miles=0;
	}

	public double getMiles(){
		return miles;
	}

	public void setMiles(double miles){
		this.miles = miles;
	}
 	
 	public DriverStateI getCurrentState(){
		return currentState;
	}

	public void setCurrentState(DriverStateI currentState){
		this.currentState = currentState;
	}
 	
 	public int getPosition(){
		return position;
	}

	public void setPosition(int position){
		this.position = position;
	}
}