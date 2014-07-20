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





@ManagedBean(name="TopGainersMBean", eager=true)
@ViewScoped
public class topgainersBean implements Serializable {


	private static final long serialVersionUID = 1L;
	private List<NseTable> nseTopGainersList;
	private List<BseTable> bseTopGainersList;
	private IService services  = new ServiceImpl();
	
	public topgainersBean() 
	{
		LoadBse();
		LoadNse();
			
	}
	
	
	@PostConstruct
	public void initiazliztion()
	{
	
	}
	@SuppressWarnings("unchecked")
	public void LoadBse()
	 {
		 System.out.println(" I m in BSE");
		 try
		 {
			 bseTopGainersList =  (List<BseTable>) services.loadList(" from NseTable");	 
		 }
		 catch(Exception e)
		 { e.printStackTrace();
		 }
		  
		 
	 }

	 @SuppressWarnings("unchecked")
	public void LoadNse()
	 {
		 System.out.println(" I m in NSE");
		 try
		 { nseTopGainersList =  (List<NseTable>) services.loadList(" from NseTable");	 
		 }
		 catch(Exception e)
		 { e.printStackTrace();
		 }
		 
	 }
	

	public IService getServices() {
		return services;
	}


	public void setServices(IService services) {
		this.services = services;
	}


	public List<NseTable> getNseTopGainersList() {
		return nseTopGainersList;
	}


	public void setNseTopGainersList(List<NseTable> nseTopGainersList) {
		this.nseTopGainersList = nseTopGainersList;
	}


	public List<BseTable> getBseTopGainersList() {
		return bseTopGainersList;
	}


	public void setBseTopGainersList(List<BseTable> bseTopGainersList) {
		this.bseTopGainersList = bseTopGainersList;
	}
	
	
}