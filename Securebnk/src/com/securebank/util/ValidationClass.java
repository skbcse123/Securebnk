package com.securebank.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
public class ValidationClass implements Validator{
	private boolean validateFlag;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
			"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
			"(\\.[A-Za-z]{2,})$";   
	
	private static final String INTEGER_PATTERN = "-?[0-9]+";
	private Pattern pattern; 
	private Matcher matcher;
	/**
	 * @return the pattern
	 */
	public Pattern getPattern() {
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	/**
	 * @return the matcher
	 */
	public Matcher getMatcher() {
		return matcher;
	}

	/**
	 * @param matcher the matcher to set
	 */
	public void setMatcher(Matcher matcher) {
		this.matcher = matcher;
	}

	/**
	 * @return the emailPattern
	 */
	public static String getEmailPattern() {
		return EMAIL_PATTERN;
	}

	/**
	 * @return the validateFlag
	 */
	public boolean isValidateFlag() {
		return validateFlag;
	}

	/**
	 * @param validateFlag the validateFlag to set
	 */
	public void setValidateFlag(boolean validateFlag) {
		this.validateFlag = validateFlag;
	}

	
	public void validateEmail(FacesContext context, UIComponent arg1, Object value)
			throws ValidatorException {
		
		
		String mailId = (String) value;
		
		
		if(!mailId.equals("")||!mailId.equals(null))
		{
		if(!(mailId.contains("@") && mailId.contains("."))){    
			FacesMessage msg = 
				new FacesMessage("E-mail validation failed.", 
						"Invalid E-mail format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}
		

	
		}   
	
	}
	
	public void validateIntegerValue(FacesContext context, UIComponent arg1, Object value)
			throws ValidatorException {
		System.out.println("in validateIntegerValue()");
		String integerValue = value.toString();
		System.out.println(integerValue);
		
		if(!integerValue.equals("")||!integerValue.equals(null))
		{
			
			pattern = Pattern.compile(INTEGER_PATTERN);
			
			matcher = pattern.matcher(integerValue);
	
			if (! (matcher.matches())) { 
				System.out.println("inside....");
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setDetail("Enter a Valid Integer Value.");
			msg.setSummary("Enter a Valid Integer Value.");
			throw new ValidatorException(msg);

				}
		}
	}
	
	
	public void validateForEmpty(FacesContext context, UIComponent arg1, Object value)
			throws ValidatorException {
		String val = (String) value;
		System.out.println("in validateForEmpty():"+value);
		if((val.equals("") || val == null )) {
			System.out.println("inside:");
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setDetail("Cannot be Empty");
			msg.setSummary("Cannot be Empty");
			throw new ValidatorException(msg);
			 
	}
		
		
	}

	/**
	 * @return the integerPattern
	 */
	public static String getIntegerPattern() {
		return INTEGER_PATTERN;
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
