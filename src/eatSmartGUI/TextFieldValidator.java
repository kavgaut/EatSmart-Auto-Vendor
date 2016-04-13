package eatSmartGUI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFieldValidator{
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String NUMBER_PATTERN = "[0-9]*";
	private static final String DECIMAL_PATTERN = "\\d*\\.?\\d*";
	private static final String TEXT_PATTERN = "([a-zA-Z]*(\\s)*[\\.\\,]*)*$";
	
	public boolean validateNumberField(final String value){
		pattern = Pattern.compile(NUMBER_PATTERN);
		matcher = pattern.matcher(value);
		return matcher.matches();
	}
	
	public boolean validateDecimalField(final String value){
		pattern = Pattern.compile(DECIMAL_PATTERN);
		matcher = pattern.matcher(value);
		return matcher.matches();
	}
	
	public boolean validateTextField(final String txtVal){
		pattern = Pattern.compile(TEXT_PATTERN);
		matcher = pattern.matcher(txtVal);
		return matcher.matches();
	}
}