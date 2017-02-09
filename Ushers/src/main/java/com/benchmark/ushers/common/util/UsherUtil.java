package com.benchmark.ushers.common.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.benchmark.ushers.dao.model.Usher;
import com.benchmark.ushers.dao.model.UsherTimeSheet;

public class UsherUtil {
	private static String ACTIVATION_USHER_PREFIX = "AC";
	private static String INSTORE_USHER_PREFIX = "IN";
	private static String PROJECT_PREFIX = "PRO";
	
	public static String getNextUsherCode(String usherType, String lastUsherCode){
		if(StringUtils.isEmpty(lastUsherCode)){
			return Usher.ACTIVATION.equals(usherType) ? ACTIVATION_USHER_PREFIX + "00001" : INSTORE_USHER_PREFIX + "00001";
		}
		int lastCode = Integer.parseInt(lastUsherCode.substring(2)) + 1;
		return Usher.ACTIVATION.equals(usherType) ? ACTIVATION_USHER_PREFIX + String.format("%05d", lastCode) : INSTORE_USHER_PREFIX + String.format("%05d", lastCode); 
	}
	public static String getNextProjectCode(String lastProjectCode){
		if(StringUtils.isEmpty(lastProjectCode)){
			return PROJECT_PREFIX + "0001";
		}
		int lastCode = Integer.parseInt(lastProjectCode.substring(3)) + 1;
		return  PROJECT_PREFIX + String.format("%04d", lastCode); 
	}
	
	public static List<UsherTimeSheet> getUsherTimeSheets(UsherTimeSheet usherTimeSheet){
		List<UsherTimeSheet> ushetTimeSheets = new ArrayList<UsherTimeSheet>();
		if(usherTimeSheet.getDate() != null){
			ushetTimeSheets.add(usherTimeSheet);
		}
		if(usherTimeSheet.getDate1() != null){
			ushetTimeSheets.add(copyTimeSheet(usherTimeSheet, usherTimeSheet.getDate1()));
		}
		if(usherTimeSheet.getDate2() != null){
			ushetTimeSheets.add(copyTimeSheet(usherTimeSheet, usherTimeSheet.getDate2()));
		}
		if(usherTimeSheet.getDate3() != null){
			ushetTimeSheets.add(copyTimeSheet(usherTimeSheet, usherTimeSheet.getDate3()));
		}
		if(usherTimeSheet.getDate4() != null){
			ushetTimeSheets.add(copyTimeSheet(usherTimeSheet, usherTimeSheet.getDate4()));
		}
		if(usherTimeSheet.getDate5() != null){
			ushetTimeSheets.add(copyTimeSheet(usherTimeSheet, usherTimeSheet.getDate5()));
		}
		return ushetTimeSheets;
	}
	
	private static UsherTimeSheet copyTimeSheet(UsherTimeSheet usherTimeSheet, Date date){
		UsherTimeSheet temp = new UsherTimeSheet();
		temp.setUsherName(usherTimeSheet.getUsherName());
		temp.setUsherCode(usherTimeSheet.getUsherCode());
		temp.setUniformDelivered(usherTimeSheet.isUniformDelivered());
		temp.setProjectName(usherTimeSheet.getProjectName());
		temp.setProjectLocationName(usherTimeSheet.getProjectLocationName());
		temp.setProjectLocationId(usherTimeSheet.getProjectLocationId());
		temp.setProjectCode(usherTimeSheet.getProjectCode());
		temp.setId(usherTimeSheet.getId());
		temp.setDeduction(usherTimeSheet.getDeduction());
		temp.setDebit(usherTimeSheet.getDebit());
		temp.setDaySalary(usherTimeSheet.getDaySalary());
		temp.setDate(date);
		return temp;
	}

}
