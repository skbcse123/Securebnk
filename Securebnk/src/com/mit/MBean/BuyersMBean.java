/**
 * 
 */
package com.mit.MBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.mit.service.IService;
import com.mit.serviceImpl.ServiceImpl;
import com.secure.Modal.BseTable;
import com.secure.Modal.NseTable;

/**
 * @author Mit
 * 
 */

@ViewScoped
@ManagedBean(name = "buyersMBean", eager = true)
public class BuyersMBean implements Serializable {
	
	private IService services= new ServiceImpl();
	private List<NseTable> nseBuyersList;
	private List<BseTable> bseBuyersList;
	private boolean nseFlag = true;
	private boolean flag;

	private boolean mcxFlag;
	private boolean stop;

	
	public IService getServices() {
		return services;
	}

	public List<NseTable> getNseBuyersList() {
		return nseBuyersList;
	}

	public List<BseTable> getBseBuyersList() {
		return bseBuyersList;
	}

	public void setServices(IService services) {
		this.services = services;
	}

	public void setNseBuyersList(List<NseTable> nseBuyersList) {
		this.nseBuyersList = nseBuyersList;
	}

	public void setBseBuyersList(List<BseTable> bseBuyersList) {
		this.bseBuyersList = bseBuyersList;
	}

	
	// setter and getter

	/**
	 * @return the flag
	 */
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

	
	@PostConstruct
	private void initialise() {
		// System.out.println("in Buyers");
		loadInitialValues();
		loadBse();
		loadNse();

	}

	@SuppressWarnings("unchecked")
	private void loadInitialValues() {
			initializeFlag(true);

	}

	public void initializeFlag(boolean flagVal) {
		flag = flagVal;

	}

	@SuppressWarnings("unchecked")
	public void loadNse() {
	
			try {
			
				nseBuyersList = (List<NseTable>) services.loadList(" from NseTable");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	@SuppressWarnings("unchecked")
	public void loadBse() {
		try {
			
			bseBuyersList = (List<BseTable>) services.loadList(" from BseTable");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
