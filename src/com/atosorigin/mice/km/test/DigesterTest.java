package com.atosorigin.mice.km.test;

import com.atosorigin.mice.km.xml.GoogleMapDigester;
import com.atosorigin.mice.km.xml.bean.Location;

public class DigesterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GoogleMapDigester g = new GoogleMapDigester();
		Location l = g.getLocation("台北市松山區民生東路三段100號");
	}

}
