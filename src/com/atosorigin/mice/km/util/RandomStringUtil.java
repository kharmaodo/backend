package com.atosorigin.mice.km.util;

import java.util.Random;

import com.atosorigin.mice.km.common.Constants;

public class RandomStringUtil {
	public static String generateString(int length) {
		Random random = new Random();
	    char[] result = new char[length];
	    for (int i = 0; i < length; i++) {
	        result[i] = Constants.RANDOM_STR.charAt(random.nextInt(Constants.RANDOM_STR.length()));
	    }
	    return new String(result);
	}


}
