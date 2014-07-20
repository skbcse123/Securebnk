package com.mit.MBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.mit.service.IService;
import com.mit.serviceImpl.ServiceImpl;
import com.secure.Modal.CustomerTable;
@ViewScoped
@ManagedBean(name = "loginMBean", eager=true)
public class LoginMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */


	
	private IService services  = new ServiceImpl();
	List<CustomerTable> customerTableList;
	private String userName;
	private String password;
	private String userPassError;
	private static final Logger SECUREBANK_LOGGER = Logger
			.getLogger(LoginMBean.class);
	private HttpSession httpSession = (HttpSession) FacesContext
			.getCurrentInstance().getExternalContext().getSession(true);
	private boolean loginButtonFlag;


	
	public List<CustomerTable> getCustomerTableList() {
		return customerTableList;
	}

	/**
	 * @param customerTableList
	 *            the customerTableList to set
	 */
	public void setCustomerTableList(List<CustomerTable> customerTableList) {
		this.customerTableList = customerTableList;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static Logger getSecurebankLogger() {
		return SECUREBANK_LOGGER;
	}

	public HttpSession getHttpSession() {
		return httpSession;
	}

	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	// Methods

	@PostConstruct
	public void init() {
		// System.out.println("in init Login");

		if (!(httpSession.getAttribute("CUSTOMERLOGINID") == null)) {
			try {
				redirect("watchList.jsf");
			} catch (IOException e) {
				SECUREBANK_LOGGER.debug("error in init()" + e.getMessage());
			}
		} else
			setLoginButtonFlag(true);
     
		

	}

	@SuppressWarnings("unchecked")
	public String verifyLogin() {
		
		
		
		
		String returnString = null;
		try {
			userPassError = "";
			

			customerTableList = (List<CustomerTable>) services
					.loadList(" from CustomerTable ct where ct.userName like '"
							+ userName + "' And ct.password like '" + password
							+ "'");
			
			if (customerTableList.size() > 0) {

				httpSession.setAttribute("CUSTOMERINFO", customerTableList);
				FacesContext fcscontext =  FacesContext.getCurrentInstance();
				Cookie usernamecookie  =  new Cookie("CUSTOMERINFO",customerTableList.get(0).getUserName());
				usernamecookie.setMaxAge(3000);
				usernamecookie.setSecure(true);
				
				Cookie userpasscookie  =  new Cookie("passwordCookie",customerTableList.get(0).getPassword());
				userpasscookie.setMaxAge(3000);
				
				
				
				((HttpServletResponse) fcscontext.getExternalContext()
					       .getResponse()).addCookie(userpasscookie);
				
				

				
				returnString = "WatchList?faces-redirect=true";
			} else {
				userPassError = "UserName or Password is incorrect";
				userName = "";
				password = "";
			}

		} catch (Exception e) {
			SECUREBANK_LOGGER.info(e.getMessage());
		}

		return returnString;
	}

	private void redirect(String redirectTo) throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect(redirectTo);

	}

	/**
	 * @return the userPassError
	 */
	public String getUserPassError() {
		return userPassError;
	}

	/**
	 * @param userPassError
	 *            the userPassError to set
	 */
	public void setUserPassError(String userPassError) {
		this.userPassError = userPassError;
	}

	/**
	 * @return the loginButtonFlag
	 */
	public boolean isLoginButtonFlag() {
		return loginButtonFlag;
	}

	/**
	 * @param loginButtonFlag
	 *            the loginButtonFlag to set
	 */
	public void setLoginButtonFlag(boolean loginButtonFlag) {
		this.loginButtonFlag = loginButtonFlag;
	}

	public IService getServices() {
		return services;
	}

	public void setServices(IService services) {
		this.services = services;
	}
	
	public String logOut() 
	{   httpSession.removeAttribute("CUSTOMERINFO");
		httpSession.invalidate();
		return "Login?faces-redirect=true";
	}
	
	public void RedirectRegistration()
	{
		
		 try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("registration.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
	}

}