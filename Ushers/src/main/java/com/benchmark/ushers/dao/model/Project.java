package com.benchmark.ushers.dao.model;

import java.util.Date;

import org.springframework.data.domain.Persistable;

public class Project implements Persistable<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String clientId = "";
	private String productId = "";
	private String projectTypeId = "";
	private String projectName = "";
	private String projectCode = "";
	private Date projectDate ;
	private transient boolean persisted;
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return getProjectCode();
	}
	
	@Override
	public boolean isNew() {
		return !persisted;
	}

	public Project withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(String projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public Date getProjectDate() {
		return projectDate;
	}

	public void setProjectDate(Date projectDate) {
		this.projectDate = projectDate;
	}
}
