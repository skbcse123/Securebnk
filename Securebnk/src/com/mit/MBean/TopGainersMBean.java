/**
 * 
 */
package com.mit.MBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.context.ApplicationContext;
import com.mit.service.IService;
import com.mit.serviceImpl.ServiceImpl;
import com.secure.Modal.BseTable;
import com.secure.Modal.NseTable;

/**
 * @author Mit
 * 
 */

@ViewScoped
@ManagedBean(name = "topGainersMBean")
public class TopGainersMBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<NseTable> nseTopGainersList;
	private List<BseTable> bseTopGainersList;
	private boolean nseFlag = true;
	private boolean bseFlag;
	private boolean mcxFlag;
	private boolean flag;
	private boolean stop;
	
	private IService service  = new ServiceImpl();

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
	 * @return the nseTopGainersList
	 */
	public List<NseTable> getNseTopGainersList() {
		return nseTopGainersList;
	}

	/**
	 * @param nseTopGainersList
	 *            the nseTopGainersList to set
	 */
	public void setNseTopGainersList(List<NseTable> nseTopGainersList) {
		this.nseTopGainersList = nseTopGainersList;
	}

	/**
	 * @return the bseTopGainersList
	 */
	public List<BseTable> getBseTopGainersList() {
		return bseTopGainersList;
	}

	/**
	 * @param bseTopGainersList
	 *            the bseTopGainersList to set
	 */
	public void setBseTopGainersList(List<BseTable> bseTopGainersList) {
		this.bseTopGainersList = bseTopGainersList;
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
	 * @return the bseFlag
	 */
	public boolean isBseFlag() {
		return bseFlag;
	}

	/**
	 * @param bseFlag
	 *            the bseFlag to set
	 */
	public void setBseFlag(boolean bseFlag) {
		this.bseFlag = bseFlag;
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
	public void init() {
		loadInitialValues();
		loadNse();
		loadBse();

	}

	@SuppressWarnings("unchecked")
	private void loadInitialValues()
	{
	
	}

	

	public void loadNse() {
				try {
					nseTopGainersList = (List<NseTable>) service
						.loadList(" from NseTable");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public void loadBse() {
		// System.out.println("in loadBse");
		try {
			

			bseTopGainersList = (List<BseTable>) service
					.loadList(" from BseTable");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public IService getService() {
		return service;
	}

	public void setService(IService service) {
		this.service = service;
	}

}
