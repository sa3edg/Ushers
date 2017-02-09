package com.benchmark.ushers.dao.model;

import java.util.Date;
import java.util.List;

public class UsherTimeSheetReport extends UsherTimeSheet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> dates;
	private int daysCount;
	private float totalDays;
	private int dayMoneyAmount;
	private float moneyAmount;
	private float totalMoneyAmount;
	private String uniform = "";
	private String usherIdentificationId = "";
	private String usherSigniture = "";
	
	public List<String> getDates() {
		return dates;
	}
	public void setDates(List<String> dates) {
		this.dates = dates;
	}
	public int getDaysCount() {
		return daysCount;
	}
	public void setDaysCount(int daysCount) {
		this.daysCount = daysCount;
	}
	public float getTotalDays() {
		return totalDays;
	}
	public void setTotalDays(float totalDays) {
		this.totalDays = totalDays;
	}
	public int getDayMoneyAmount() {
		return dayMoneyAmount;
	}
	public void setDayMoneyAmount(int dayMoneyAmount) {
		this.dayMoneyAmount = dayMoneyAmount;
	}
	public float getMoneyAmount() {
		return moneyAmount;
	}
	public void setMoneyAmount(float moneyAmount) {
		this.moneyAmount = moneyAmount;
	}
	public float getTotalMoneyAmount() {
		return totalMoneyAmount;
	}
	public void setTotalMoneyAmount(float totalMoneyAmount) {
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
