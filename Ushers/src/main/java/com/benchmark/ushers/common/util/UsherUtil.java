package com.benchmark.ushers.common.util;

import org.apache.commons.lang.StringUtils;

import com.benchmark.ushers.dao.model.Usher;

public class UsherUtil {
	private static String ACTIVATION_USHER_PREFIX = "AC";
	private static String INSTORE_USHER_PREFIX = "IN";
	private static String PROJECT_PREFIX = "PRO";
	
	public static String getNextUsherCode(String usherType, String lastUsherCode){
		if(StringUtils.isEmpty(lastUsherCode)){
			return "";
		}
		int lastCode = Integer.parseInt(lastUsherCode.substring(2));
		return Usher.ACTIVATION.equals(usherType) ? ACTIVATION_USHER_PREFIX + String.format("%05d", lastCode++) : INSTORE_USHER_PREFIX + String.format("%05d", lastCode++); 
	}
	public static String getNextProjectCode(String lastProjectCode){
		if(StringUtils.isEmpty(lastProjectCode)){
			return "";
		}
		int lastCode = Integer.parseInt(lastProjectCode.substring(3));
		return  PROJECT_PREFIX + String.format("%06d", lastCode++); 
	}

}
