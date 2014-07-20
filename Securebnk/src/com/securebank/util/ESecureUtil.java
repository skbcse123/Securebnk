package com.securebank.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;

/**
 * This class contains common utility methods for HumanCapital project
 * @author Administrator
 *
 */

public class ESecureUtil {

	private static final Logger SCHOOL_LOGGER = Logger.getLogger(ESecureUtil.class);

	
	public static String getContext() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return	request.getServletContext().getContextPath();
		
		
	}
	
	public static String loadPropertyPath(String fileName) {

		String absolutePath;

		if (System.getProperty("os.name").equals("Linux"))
			absolutePath = System.getProperty("user.dir") + "/webapps/secure/"
					 + fileName;
		else
			absolutePath = System.getProperty("user.dir")
					+ "\\webapps\\secure\\" + fileName;

		return absolutePath;
	}
	
	
	public static String encodeImage(FileEntryEvent event) {
		 ByteArrayOutputStream baos= new ByteArrayOutputStream();
		try { 
		   BufferedImage originalImage= ImageIO.read(new File(getuploadedImage(event))); 
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			//icon=Base64.encodeBase64String(baos.toByteArray());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return Base64.encodeBase64String(baos.toByteArray());

	}		
	
	public static String getuploadedImage(FileEntryEvent event) {
		String icon = null;
		
		FileEntry fileEntry = (FileEntry) event.getSource();
		FileEntryResults results = fileEntry.getResults();

		for (FileEntryResults.FileInfo fileInfo : results.getFiles()) {
			icon = fileInfo.getFile().getAbsolutePath();
		
		}
		
		return icon;

	}		

	
	
	
}