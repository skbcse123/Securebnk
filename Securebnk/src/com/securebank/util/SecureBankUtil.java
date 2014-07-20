package com.securebank.util;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * This class contains common utility methods for HumanCapital project
 * @author Administrator
 *
 */

public class SecureBankUtil {
	
	
	/**
	 * @return
	 */
	public static String getContext() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return	request.getServletContext().getContextPath();
		
		
	}

//	public static void redirect(String redirectTo) throws IOException {
//
//		
//			FacesContext.getCurrentInstance().getExternalContext()
//					.redirect(getContext()+redirectTo);
//		
//	}

	


}