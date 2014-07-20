/**
 * 
 */
package com.mit.service;

import java.util.Collection;

import com.secure.Modal.CustomerTable;

/**
 * @author khader
 *
 */
public interface IService {

	public Collection<?> loadList(String query) ;

	public void saveInDb(CustomerTable customerTable);

	
	
	
	
	

}
