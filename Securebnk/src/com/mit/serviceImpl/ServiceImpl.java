/**
 * 
 */
package com.mit.serviceImpl;

import java.io.Serializable;
import java.util.Collection;

import com.mit.Dao.IDao;
import com.mit.DaoImpl.DaoImpl;
import com.mit.service.IService;
import com.secure.Modal.CustomerTable;

/**
 * @author khader
 *
 */
public class ServiceImpl implements IService,Serializable{
	private static final long serialVersionUID = 1L;
	private IDao dao = new DaoImpl();
	public IDao getDao() {
		return dao;
	}


	/**
	 * @param dao the dao to set
	 */
	public void setDao(IDao dao) {
		this.dao = dao;
	}


	public Collection<?> loadList(String query) {
		
		return dao.loadList(query) ;
	}


	@Override
	public void saveInDb(CustomerTable customerTable) {
		dao.saveInDb(customerTable);
	}


	


}
