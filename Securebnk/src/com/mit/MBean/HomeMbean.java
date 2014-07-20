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


@ManagedBean(name="homeMbean")
@ViewScoped
public class HomeMbean implements Serializable {

	private List<NseTable> nseStockList;
	private List<BseTable> bseStockList;
	
	
	
	private IService services= new ServiceImpl();
	public List<NseTable> getNseStockList() {
		return nseStockList;
	}

	public List<BseTable> getBseStockList() {
		return bseStockList;
	}

	public void setNseStockList(List<NseTable> nseStockList) {
		this.nseStockList = nseStockList;
	}

	public void setBseStockList(List<BseTable> bseStockList) {
		this.bseStockList = bseStockList;
	}

	
	public HomeMbean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init()
	{
		loadNseStockList();
		loadBseStockList();
	}
	
	@SuppressWarnings("unchecked")
	public void loadNseStockList() 
	{
		nseStockList = (List<NseTable>) this.services.loadList(" from NseTable");
		
	}
	public void loadBseStockList() 
	{	this.bseStockList = (List<BseTable>) this.services.loadList(" from BseTable");
		
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
