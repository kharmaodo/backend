package com.atosorigin.mice.km.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.atosorigin.mice.km.common.Constants;

public class ZipUtil {
	
	public void zip(String origPrefix, String[] fileNames, String destPrefix, String zipName) {
		try {
	        BufferedInputStream origin = null;
	        FileOutputStream dest = new FileOutputStream(destPrefix + zipName);
	        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
	        byte data[] = new byte[Constants.BUFFER];
	        List<File> files = new ArrayList<File>();
	        for(int i=0; i<fileNames.length; i++) {
	        	File f = new File(origPrefix + fileNames[i]);
	        	files.add(f);
	        }
	        
	        for (File f : files) {
	            FileInputStream fi = new FileInputStream(f);
	            origin = new BufferedInputStream(fi, Constants.BUFFER);
	            ZipEntry entry = new ZipEntry(f.getName());
	            out.putNextEntry(entry);
	            int count;
	            while ((count = origin.read(data, 0, Constants.BUFFER)) != -1) {
	                out.write(data, 0, count);
	            }
	            origin.close();
	        }
	        out.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
