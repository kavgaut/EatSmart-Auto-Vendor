package eatSmartGUI;

public class AdminRole implements RemoteUserRole{
	State state;
	
	public AdminRole(){
		this.state = null;
	}
	
	public void setState(State newState){
		this.state = newState;
	}
	
	public State getState(){
		return this.state;
	}
}