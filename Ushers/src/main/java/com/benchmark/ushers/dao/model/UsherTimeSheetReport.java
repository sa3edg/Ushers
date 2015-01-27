package com.benchmark.ushers.dao.model;

import java.util.Date;
import java.util.List;

public class UsherTimeSheetReport extends UsherTimeSheet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Date> dates;
	private int daysCount;
	private int totalDays;
	private int dayMoneyAmount;
	private int moneyAmount;
	private int totalMoneyAmount;
	private String uniform = "";
	private String usherIdentificationId = "";
	private String usherSigniture = "";
	
	public List<Date> getDates() {
		return dates;
	}
	public void setDates(List<Date> dates) {
		this.dates = dates;
	}
	public int getDaysCount() {
		return daysCount;
	}
	public void setDaysCount(int daysCount) {
		this.daysCount = daysCount;
	}
	public int getTotalDays() {
		return totalDays;
	}
	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}
	public int getDayMoneyAmount() {
		return dayMoneyAmount;
	}
	public void setDayMoneyAmount(int dayMoneyAmount) {
		this.dayMoneyAmount = dayMoneyAmount;
	}
	public int getMoneyAmount() {
		return moneyAmount;
	}
	public void setMoneyAmount(int moneyAmount) {
		this.moneyAmount = moneyAmount;
	}
	public int getTotalMoneyAmount() {
		return totalMoneyAmount;
	}
	public void setTotalMoneyAmount(int totalMoneyAmount) {
		this.totalMoneyAmount = totalMoneyAmount;
	}
	public String getUniform() {
		return uniform;
	}
	public void setUniform(String uniform) {
		this.uniform = uniform;
	}
	public String getUsherIdentificationId() {
		return usherIdentificationId;
	}
	public void setUsherIdentificationId(String usherIdentificationId) {
		this.usherIdentificationId = usherIdentificationId;
	}
	public String getUsherSigniture() {
		return usherSigniture;
	}
	public void setUsherSigniture(String usherSigniture) {
		this.usherSigniture = usherSigniture;
	}
}
