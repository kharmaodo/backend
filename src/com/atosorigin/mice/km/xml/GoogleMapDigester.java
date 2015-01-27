package com.atosorigin.mice.km.xml;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.digester.Digester;

import com.atosorigin.mice.km.xml.bean.Location;

public class GoogleMapDigester {
	
	public Location getLocation(String address) {
		Location location = new Location();
		Digester digester = new Digester();
	    digester.setValidating(false);
	    digester.addObjectCreate("GeocodeResponse/result/geometry/location", Location.class);
	    digester.addBeanPropertySetter("GeocodeResponse/result/geometry/location/lat", "lat");
	    digester.addBeanPropertySetter("GeocodeResponse/result/geometry/location/lng", "lng");
		try {
		  address = URLEncoder.encode(address, "UTF-8");
	      String feed = "http://maps.googleapis.com/maps/api/geocode/xml?address="+address+"&sensor=false";
	      
	      URL url = new URL(feed);
	      HttpURLConnection httpSource = (HttpURLConnection)url.openConnection();
	      
	      /*
	      StringBuilder sb = new StringBuilder();
		  BufferedReader reader = new BufferedReader(new InputStreamReader(httpSource.getInputStream(), "UTF-8"));
		  String lines;
		  while ((lines = reader.readLine()) != null) {
	            sb.append(lines);
	      }
		  reader.close();
		  System.out.println("sb.toString() == " + sb.toString());
	     */
	      
	      Reader reader = new InputStreamReader(httpSource.getInputStream(), "UTF-8");
	      location = (Location)digester.parse(reader);
		  //location = (Location)digester.parse(new StringReader(sb.toString()));
	      //System.out.println("Lat ======== " + location.getLat());
	      //System.out.println("Lng ======== " + location.getLng());
	      
	    } catch(Exception e) {
	      e.printStackTrace();
	    }
	    return location;
	}
}
