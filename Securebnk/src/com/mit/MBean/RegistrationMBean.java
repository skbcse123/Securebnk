package com.mit.MBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import com.mit.service.IService;
import com.mit.serviceImpl.ServiceImpl;
import com.secure.Modal.CustomerTable;
import com.securebank.util.SecureBankUtil;
import com.securebank.util.ValidationClass;

@ViewScoped
@ManagedBean(name = "registrationMBean", eager = true)
public class RegistrationMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	
	
	private CustomerTable customerTable;
	private IService service = new ServiceImpl();
	private static final Logger SECUREBANK_LOGGER = Logger
			.getLogger(RegistrationMBean.class);
	private ValidationClass validationClass;
	private boolean customerAlReadyExist;
	private String customerIdError;

	// Setter and Getter

	public boolean isCustomerAlReadyExist() {
		return customerAlReadyExist;
	}

	public void setCustomerAlReadyExist(boolean customerAlReadyExist) {
		this.customerAlReadyExist = customerAlReadyExist;
	}

	
	public CustomerTable getCustomerTable() {
		return customerTable;
	}

	public void setCustomerTable(CustomerTable customerTable) {
		this.customerTable = customerTable;
	}

	

	public ValidationClass getValidationClass() {
		return validationClass;
	}

	public void setValidationClass(ValidationClass validationClass) {
		this.validationClass = validationClass;
	}

	public static Logger getSecurebankLogger() {
		return SECUREBANK_LOGGER;
	}

	public String getCustomerIdError() {
		return customerIdError;
	}

	public void setCustomerIdError(String customerIdError) {
		this.customerIdError = customerIdError;
	}

	// Methods

	@PostConstruct
	public void init() {
		customerTable = new CustomerTable();

	}

	
	public String addCustomerDetails()
	{		try {
			if (checkCustomerExistence()) {
				service.saveInDb(customerTable);
				return "Login?faces-redirect=true";
			}

		} catch (Exception e) {

			SECUREBANK_LOGGER.debug(e.getMessage());
		}

		return null;
	}

	private void redirect(String redirectTo) throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect(SecureBankUtil.getContext() + redirectTo);

	}

	@SuppressWarnings("unchecked")
	private int loadCustomerList() {
		List<CustomerTable> customerTableList = null;
		try {

			customerTableList = (List<CustomerTable>) service
					.loadList(" from CustomerTable ct where ct.userName like '"
							+ customerTable.getUserName() + "'");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			SECUREBANK_LOGGER.debug(e.getMessage());
		}
		return customerTableList.size();
	}

	public boolean checkCustomerExistence() {

		if (!(null == customerTable.getUserName() || "".equals(customerTable
				.getUserName()))) {

			setCustomerIdError("");

			if (loadCustomerList() != 0) {

				customerAlReadyExist = true;
				setCustomerIdError(customerTable.getUserName()
						+ " already exist..");

				this.customerTable.setUserName("");
				return false;

			} else {

				return true;
			}
		} else {
			customerAlReadyExist = true;
			setCustomerIdError("Valid Customer Name is Required");
			return false;
		}

	}

}
