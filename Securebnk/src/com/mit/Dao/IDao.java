/**
 * 
 */
package com.mit.Dao;

import java.util.Collection;
import java.util.List;

/**
 * @author khader
 *
 */
public interface IDao {

	Collection<?> loadList(String query);

	void deleteFromDb(String query);

	void saveInDb(Object object);

	void updateInDb(Object object);

	Object loadUniqueObject(String query);

	void delete(Object object);

	
	
	
	
	

	
}
