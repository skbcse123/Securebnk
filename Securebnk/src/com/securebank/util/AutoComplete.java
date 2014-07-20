/**
 * 
 */
package com.securebank.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.model.SelectItem;

/**
 * @author Khader
 *
 */
public class AutoComplete {

	public static  List<SelectItem> wrapItemList(String filter, List<SelectItem> toWrap) {
		
//		System.out.println("autocomplete inside wrapitem");
		
	    if ((toWrap == null) || (toWrap.size() == 0)) {
	        return new ArrayList<SelectItem>(0);
	    }
	
	   
	    if ((filter != null) && (filter.trim().length() == 0)) {
	        return new ArrayList<SelectItem>(0);
	    }
	   
	   
	    if (filter != null) {
	        filter = filter.toLowerCase();
	    }
	   
	 
	    List<SelectItem> toReturn = new ArrayList<SelectItem>(toWrap.size());
	    for (SelectItem currentWrap : toWrap) {
	    	if (filter != null) {
	    		
	            if (currentWrap.getLabel().toString().toLowerCase().startsWith(filter)) {
	            	// toReturn.add(new SelectItem(currentWrap.getValue().toString()+" ----- "+currentWrap.getLabel().toString()));
	            	 toReturn.add(new SelectItem(currentWrap.getLabel().toString().trim()));
	            	 
//	            System.out.println("autocomplete inside arraylist");	
	            
	            }
	        }
	       
	        else {
	            toReturn.add(new SelectItem(currentWrap));
	        }
	    }
	    
//	    System.out.println("end of autocomplete block");
	    return toReturn;
	}	


public static String returnId(String selectedString, int tokenPosition)
{
	System.out.println("tokenPosition="+tokenPosition); 
	String toReturn =null;
	if(selectedString.contains("---"))
	{
	StringTokenizer tokenizer = new StringTokenizer(selectedString.trim(),"---");
	
	
	switch(tokenPosition)
	{
	case 1 :         toReturn= tokenizer.nextToken().trim();
					 break;
	case 2 :         tokenizer.nextToken().trim(); tokenizer.nextToken().trim();
					 toReturn= tokenizer.nextToken().trim(); 
					 break;
	}
	
	}
	System.out.println("toReturn="+toReturn);
		return toReturn;	
}

}
