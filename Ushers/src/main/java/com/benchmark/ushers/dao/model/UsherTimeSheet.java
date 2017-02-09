package com.benchmark.ushers.dao.model;

import java.util.Date;

import org.springframework.data.domain.Persistable;


public class UsherTimeSheet implements Persistable<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id ;
	private Integer projectLocationId ;
	private String projectCode ;
	private String usherCode ;
	private float deduction ;
	private Integer debit = 0;
	private Integer daySalary = 0;
	private Date date ;
	private Date date1 ;
	private Date date2 ;
	private Date date3 ;
	private Date date4 ;
	private Date date5 ;
	private boolean uniformDelivered;
	
	private String projectLocationName = "";
	private String projectName = "";
	private String usherName = "";

	public UsherTimeSheet() {
	}
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}

	@Override
	public boolean isNew() {
		return id == null;
	}
	
	@Override
	public boolean equals(Object obj){
		return ((UsherTimeSheet)obj).getDate() == this.getDate();
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", date=" + getDate() + "]";
	}
	public Integer getProjectLocationId() {
		return projectLocationId;
	}
	public void setProjectLocationId(Integer projectLocationId) {
		this.projectLocationId = projectLocationId;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getUsherCode() {
		return usherCode;
	}
	public void setUsherCode(String usherCode) {
		this.usherCode = usherCode;
	}
	public float getDeduction() {
		return deduction;
	}
	public void setDeduction(float deduction) {
		this.deduction = deduction;
	}
	public Integer getDebit() {
		return debit;
	}
	public void setDebit(Integer debit) {
		this.debit = debit;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getProjectLocationName() {
		return projectLocationName;
	}
	public void setProjectLocationName(String projectLocationName) {
		this.projectLocationName = projectLocationName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getUsherName() {
		return usherName;
	}
	public void setUsherName(String usherName) {
		this.usherName = usherName;
	}
	public boolean isUniformDelivered() {
		return uniformDelivered;
	}
	public void setUniformDelivered(boolean uniformDelivered) {
		this.uniformDelivered = uniformDelivered;
	}
	public Integer getDaySalary() {
		return daySalary;
	}
	public void setDaySalary(Integer daySalary) {
		this.daySalary = daySalary;
	}
	/**
	 * @return the date1
	 */
	public Date getDate1() {
		return date1;
	}
	/**
	 * @param date1 the date1 to set
	 */
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	/**
	 * @return the date2
	 */
	public Date getDate2() {
		return date2;
	}
	/**
	 * @param date2 the date2 to set
	 */
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	/**
	 * @return the date3
	 */
	public Date getDate3() {
		return date3;
	}
	/**
	 * @param date3 the date3 to set
	 */
	public void setDate3(Date date3) {
		this.date3 = date3;
	}
	/**
	 * @return the date4
	 */
	public Date getDate4() {
		return date4;
	}
	/**
	 * @param date4 the date4 to set
	 */
	public void setDate4(Date date4) {
		this.date4 = date4;
	}
	/**
	 * @return the date5
	 */
	public Date getDate5() {
		return date5;
	}
	/**
	 * @param date5 the date5 to set
	 */
	public void setDate5(Date date5) {
		this.date5 = date5;
	}
}
