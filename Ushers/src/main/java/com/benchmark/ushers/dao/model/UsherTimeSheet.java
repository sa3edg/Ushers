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
	private Integer debit ;
	private Date date ;
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
}
