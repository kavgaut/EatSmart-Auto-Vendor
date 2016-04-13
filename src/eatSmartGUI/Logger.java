package eatSmartGUI;
import java.sql.Date;

public class Logger{
	private String loggedUserId;
	private Date loggedInDate;
	
	private static Logger logInstance = new Logger();
	
	public static Logger getInstance(){
		return logInstance;
	}
	private Logger(){
		
	}
	
	public void logUserInfo(String UserId){
		LoggerDaoImpl ldi = new LoggerDaoImpl();
		ldi.insertUserlogInfo(UserId);
	}
	
	public void setLoggedUserId(String userId){
		this.loggedUserId = userId;
	}
	
	public String getLoggedUserId(){
		return loggedUserId;
	}
	
	public void setLoggedInDate(Date loggedInDate){
		this.loggedInDate = loggedInDate;
	}
	
	public Date getLoggedInDate(){
		return loggedInDate;
	}
}