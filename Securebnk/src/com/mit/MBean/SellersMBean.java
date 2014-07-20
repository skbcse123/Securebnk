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
@ManagedBean(name = "sellersMBean", eager = true)
public class SellersMBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<NseTable> nseSellersList;
	private List<BseTable> bseSellersList;
	private boolean nseFlag = true;
	private boolean flag;
	private boolean mcxFlag;
	private boolean stop;
	private IService services = new ServiceImpl();

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
	 * @return the nseSellersList
	 */
	public List<NseTable> getNseSellersList() {
		return nseSellersList;
	}

	/**
	 * @param nseSellersList
	 *            the nseSellersList to set
	 */
	public void setNseSellersList(List<NseTable> nseSellersList) {
		this.nseSellersList = nseSellersList;
	}

	/**
	 * @return the bseSellersList
	 */
	public List<BseTable> getBseSellersList() {
		return bseSellersList;
	}

	/**
	 * @param bseSellersList
	 *            the bseSellersList to set
	 */
	public void setBseSellersList(List<BseTable> bseSellersList) {
		this.bseSellersList = bseSellersList;
	}

	
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
	public void initialise() {
		loadNse();
		loadBse();
	}

	

	public void initializeFlag(boolean flagVal) {
		flag = flagVal;

	}

	@SuppressWarnings("unchecked")
	public void loadNse() {
		
			try {nseSellersList = (List<NseTable>) services.loadList(" from NseTable");
				} catch (Exception e)
				{e.printStackTrace();
				}
		
	}

	@SuppressWarnings("unchecked")
	public void loadBse() {
		try {
				bseSellersList = (List<BseTable>) services
					.loadList(" from BseTable");
		} catch (Exception e) 
		{	e.printStackTrace();
		}
		}
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
