package com.benchmark.ushers.dao.model;

import org.springframework.data.domain.Persistable;

public class Project implements Persistable<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id ;
	private String client = "";
	private String product = "";
	private String projectType = "";
	private String projectName = "";
	private String projectCode = "";
	private String startDate = "";
	private String endDate = "";
	private int year;
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
		// TODO Auto-generated method stub
		return false;
	}
	
	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
