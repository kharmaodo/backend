package com.atosorigin.mice.km.test;

public class StripHtmlTag {
	
	public static void main(String[] args) {
		String tester= "<p>TEST<a href=\"\">aaaaaaaaaaaa</a>bbbbbbbbbb</p>";
		String aa = tester.replaceAll("\\<.*?>","");
		System.out.println(aa);
	}
}
