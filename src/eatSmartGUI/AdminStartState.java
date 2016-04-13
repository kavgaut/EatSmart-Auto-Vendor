package eatSmartGUI;

public class AdminStartState implements State{
	
	public void doAction(RemoteUserRole remoteUser){
		remoteUser.setState(this);
	}
}