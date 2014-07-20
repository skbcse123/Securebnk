package com.mit.MBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icesoft.faces.component.selectinputtext.SelectInputText;
import com.icesoft.faces.component.selectinputtext.TextChangeEvent;
import com.mit.service.IService;
import com.mit.serviceImpl.ServiceImpl;
import com.secure.Modal.BseTable;
import com.secure.Modal.CustomerTable;
import com.secure.Modal.NseTable;
import com.secure.Modal.WatchlistBse;
import com.secure.Modal.WatchlistNse;
import com.securebank.exception.SecureBankException;
import com.securebank.util.AutoComplete;
import com.securebank.util.ValidationClass;

@ViewScoped
@ManagedBean(name = "watchListMBean")
public class WatchListMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean stop;
	private static final Logger SECUREBANK_LOGGER = Logger
			.getLogger(WatchListMBean.class);
	private HttpSession httpSession = (HttpSession) FacesContext
			.getCurrentInstance().getExternalContext().getSession(false);
	private List<WatchlistBse> customerBseList;
	private List<WatchlistNse> customerNseList;
	
	private List<BseTable> bseList;
	private List<NseTable> nseList;
	
	private WatchlistBse watchlistBse;
	private WatchlistNse watchlistNse;
	private List<CustomerTable> customerInfoList;
	private boolean editFlag;
	private CustomerTable customerTable;
	
	
		private ValidationClass validationClass;
	private NumberFormat formatter = new DecimalFormat("#0.00");

	private boolean bseDialogFlag;

	private boolean nseDialogFlag;

	private String selectedStock;
	private boolean stockObjectCreated;

	private List<WatchlistBse> selectedBseList;
	private List<WatchlistNse> selectedNseList;

	private IService services = new ServiceImpl();
	/**
	 * @return the stop
	 */
	public boolean isStop() {
		return stop;
	}

	/**
	 * @param stop
	 *            the stop to set
	 */
	public void setStop(boolean stop) {
		this.stop = stop;
	}

	/**
	 * @return the watchlistBse
	 */
	public WatchlistBse getWatchlistBse() {
		return watchlistBse;
	}

	/**
	 * @param watchlistBse
	 *            the watchlistBse to set
	 */
	public void setWatchlistBse(WatchlistBse watchlistBse) {
		this.watchlistBse = watchlistBse;
	}

	/**
	 * @return the watchlistNse
	 */
	public WatchlistNse getWatchlistNse() {
		return watchlistNse;
	}

	/**
	 * @param watchlistNse
	 *            the watchlistNse to set
	 */
	public void setWatchlistNse(WatchlistNse watchlistNse) {
		this.watchlistNse = watchlistNse;
	}

	
	public List<NseTable> getNseList() {
		return nseList;
	}

	/**
	 * @param nseList
	 *            the nseList to set
	 */
	public void setNseList(List<NseTable> nseList) {
		this.nseList = nseList;
	}

	/**
	 * @return the stockObjectCreated
	 */
	public boolean isStockObjectCreated() {
		return stockObjectCreated;
	}

	/**
	 * @param stockObjectCreated
	 *            the stockObjectCreated to set
	 */
	public void setStockObjectCreated(boolean stockObjectCreated) {
		this.stockObjectCreated = stockObjectCreated;
	}

	public List<WatchlistBse> getSelectedBseList() {
		return selectedBseList;
	}

	public void setSelectedBseList(List<WatchlistBse> selectedBseList) {
		this.selectedBseList = selectedBseList;
	}

	public List<WatchlistNse> getSelectedNseList() {
		return selectedNseList;
	}

	public void setSelectedNseList(List<WatchlistNse> selectedNseList) {
		this.selectedNseList = selectedNseList;
	}

	public List<BseTable> getBseList() {

		if (bseList != null) {
			String stockRates;
			StringTokenizer stringTokenizer;

			try (FileInputStream fout = new FileInputStream(
					"bseStock.properties")) {

				Properties prop = new Properties();
				prop.load(fout);

				for (int i = 0; i < bseList.size(); i++) {

					stockRates = prop.getProperty(bseList.get(i)
							.getCompanyQuote().trim());
					if (stockRates != null) {

						// System.out.println(customerBseList.get(i).getBseTable().getCompanyQuote()+"= "+stockRates);
						stringTokenizer = new StringTokenizer(stockRates);
						while (stringTokenizer.hasMoreTokens()) {
							if (stringTokenizer.countTokens() == 1)
								bseList.get(i).setPrevClose(
										Double.parseDouble(stringTokenizer
												.nextToken()));
							else
								stringTokenizer.nextToken();
						}
					}

				}

			} catch (IOException | NoSuchElementException e) {
				// System.out.println("error.....");
				SECUREBANK_LOGGER.debug("error in AddStockMBean.getBseList()");
			}
		}
		return bseList;
	}
	
	
	
	public void setBseList(List<BseTable> bseList) {
		this.bseList = bseList;
	}

	
	
	
	public boolean isBseDialogFlag() {
		return bseDialogFlag;
	}

	/**
	 * @param bseDialogFlag
	 *            the bseDialogFlag to set
	 */
	public void setBseDialogFlag(boolean bseDialogFlag) {
		this.bseDialogFlag = bseDialogFlag;
	}

	/**
	 * @return the nseDialogFlag
	 */
	public boolean isNseDialogFlag() {
		return nseDialogFlag;
	}

	public IService getServices() {
		return services;
	}

	public void setServices(IService services) {
		this.services = services;
	}

	/**
	 * @param nseDialogFlag
	 *            the nseDialogFlag to set
	 */
	public void setNseDialogFlag(boolean nseDialogFlag) {
		this.nseDialogFlag = nseDialogFlag;
	}

	/**
	 * @return the formatter
	 */
	public NumberFormat getFormatter() {
		return formatter;
	}

	/**
	 * @param formatter
	 *            the formatter to set
	 */
	public void setFormatter(NumberFormat formatter) {
		this.formatter = formatter;
	}

	/**
	 * @return the customerTable
	 */
	public CustomerTable getCustomerTable() {
		return customerTable;
	}

	/**
	 * @param customerTable
	 *            the customerTable to set
	 */
	public void setCustomerTable(CustomerTable customerTable) {
		this.customerTable = customerTable;
	}

	public List<CustomerTable> getCustomerInfoList() {
		return customerInfoList;
	}

	public void setCustomerInfoList(List<CustomerTable> customerInfoList) {
		this.customerInfoList = customerInfoList;
	}
	public void setCustomerBseList(List<WatchlistBse> customerBseList) {
		this.customerBseList = customerBseList;
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


	public void setCustomerNseList(List<WatchlistNse> customerNseList) {
		this.customerNseList = customerNseList;
	}

	public boolean isEditFlag() {
		return editFlag;
	}

	/**
	 * @param editFlag
	 *            the editFlag to set
	 */
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	/**
	 * @return the validationClass
	 */
	public ValidationClass getValidationClass() {
		return validationClass;
	}

	/**
	 * @param validationClass
	 *            the validationClass to set
	 */
	public void setValidationClass(ValidationClass validationClass) {
		this.validationClass = validationClass;
	}

	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		if (!(httpSession.getAttribute("CUSTOMERINFO") == null)) {

			customerInfoList = (List<CustomerTable>) httpSession
					.getAttribute("CUSTOMERINFO");

			
			try {
				//loadCustomerInfo();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("Login.jsf");

			} catch (IOException e) {
				SECUREBANK_LOGGER.debug(e.getMessage());
			}

		}
		
		loadInitialValues();
	}


	@SuppressWarnings("unchecked")
	private void loadInitialValues(){
		
		loadCustomerBseList();
		loadCustomerNseList();
	}
	
	public void executeThreads() {

		

		Thread first = new Thread(new Runnable() {
			public void run() {

				try {
					
					Thread.sleep(200);
					loadCustomerBseList();
					
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		});
		Thread second = new Thread(new Runnable() {
			public void run() {

				try {
					
					Thread.sleep(200);
					loadCustomerNseList();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		});

		try {
			
			first.start();
			
			second.start();
			
			first.join();
			second.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}


	@SuppressWarnings("unchecked")
	private void loadCustomerBseList() {
		try {
					
			this.bseList = (List<BseTable>) services
					.loadList(" from BseTable");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	private void loadCustomerNseList() {
		

		try {
			
			this.nseList= (List<NseTable>) services
					.loadList(" from NseTable");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public String getSelectedStock() {
		return selectedStock;
	}

	public void setSelectedStock(String selectedStock) {
		this.selectedStock = selectedStock;
	}

	public List<WatchlistBse> getCustomerBseList() {
		return customerBseList;
	}

	public List<WatchlistNse> getCustomerNseList() {
		return customerNseList;
	}

	
}