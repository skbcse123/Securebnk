package com.securebank.util;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class DateValidator implements Validator{

	public void validate(FacesContext context, UIComponent component, Object value )
			throws ValidatorException {
		
		if (value  == null || component.getAttributes().get("durationfrom") == null) return;
		Date toDate   = (Date) value; 
        Date fromDate = (Date) component.getAttributes().get("durationfrom");

        if (fromDate.after(toDate)) {
            FacesMessage message = new FacesMessage("Invalid dates submitted.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
		
	}

}
