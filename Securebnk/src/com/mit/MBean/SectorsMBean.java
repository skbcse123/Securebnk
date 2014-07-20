/**
 * 
 */
package com.mit.MBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.mit.service.IService;
import com.mit.serviceImpl.ServiceImpl;
import com.secure.Modal.BseTable;
import com.secure.Modal.NseTable;

/**
 * @author Mit
 * 
 */

@ViewScoped
@ManagedBean(name = "sectorsMBean")
public class SectorsMBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger SECUREBANK_LOGGER = Logger
			.getLogger(SectorsMBean.class);
	private HttpSession httpSession;

	private List<NseTable> nseSectorsList;

	private List<BseTable> bseSectorsList;

	private boolean flag;

	private boolean mcxFlag;

	private long selectedSymbol;

	private boolean nseFlag = true;

	private boolean stop;
	private IService services= new ServiceImpl();

	// setter and getter

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
	 * @return the nseFlag
	 */
	public boolean isNseFlag() {
		return nseFlag;
	}

	/**
	 * @param nseFlag
	 *            the nseFlag to set
	 */
	public void setNseFlag(boolean nseFlag) {
		this.nseFlag = nseFlag;
	}

	public boolean isFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * @return the mcxFlag
	 */
	public boolean isMcxFlag() {
		return mcxFlag;
	}

	/**
	 * @param mcxFlag
	 *            the mcxFlag to set
	 */
	public void setMcxFlag(boolean mcxFlag) {
		this.mcxFlag = mcxFlag;
	}

	
	public List<NseTable> getNseSectorsList() {
		return nseSectorsList;
	}

	/**
	 * @param sectorsList
	 *            the sectorsList to set
	 */
	public void setNseSectorsList(List<NseTable> sectorsList) {
		this.nseSectorsList = sectorsList;
	}

	/**
	 * @return the bseSectorsList
	 */
	public List<BseTable> getBseSectorsList() {
		return bseSectorsList;
	}

	/**
	 * @param bseSectorsList
	 *            the bseSectorsList to set
	 */
	public void setBseSectorsList(List<BseTable> bseSectorsList) {
		this.bseSectorsList = bseSectorsList;
	}

	@PostConstruct
	private void init() {
		// System.out.println("in SectorMBean");
		loadInitialValues();

	}

	@SuppressWarnings("unchecked")
	private void loadInitialValues() {

		httpSession = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);

		initializeFlag(true);
		loadNse();
		loadBse();

	}

	public void initializeFlag(boolean flagVal) {
		flag = flagVal;

	}

	public void loadNse() {
		
			try {
						nseSectorsList = (List<NseTable>) services
						.loadList(" from NseTable");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	public void loadBse() {
		try {
					bseSectorsList = (List<BseTable>) services
					.loadList(" from BseTable");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	stop = true;
	}

	public String showMcx() {

		mcxFlag = true;
		return null;
	}

	public String selectedBseStock() {

		// System.out.println("selectedSymbol"+selectedSymbol);

		httpSession.setAttribute("SELECTED_STOCK_BSE", selectedSymbol);
		try {
			redirect("/stockDetails.jsf");

		} catch (IOException e) {

			SECUREBANK_LOGGER.debug("error in SectorMBean.selectedBseStock()");
		}
		return null;
	}

	public String selectedNseStock() {
		// System.out.println("selectedSymbol"+selectedSymbol);
		httpSession.setAttribute("SELECTED_STOCK_NSE", selectedSymbol);
		try {
			redirect("/stockDetails.jsf");

		} catch (IOException e) {

			SECUREBANK_LOGGER.debug("error in SectorMBean.selectedNseStock()");
		}

		return null;
	}

	public void redirect(String redirectTo) throws IOException {

	/*	FacesContext.getCurrentInstance().getExternalContext()
				.redirect(SecureBankUtil.getContext() + redirectTo);*/

	}

	/**
	 * @return the selectedSymbol
	 */
	public long getSelectedSymbol() {
		return selectedSymbol;
	}

	/**
	 * @param selectedSymbol
	 *            the selectedSymbol to set
	 */
	public void setSelectedSymbol(long selectedSymbol) {
		this.selectedSymbol = selectedSymbol;
	}

	/**
	 * @return the services
	 */
	public IService getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(IService services) {
		this.services = services;
	}

}
