package com.benchmark.ushers.dao.model;

public class UsherReport extends Usher {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fileType = "";
	
	private String productId = "";
	private String projectCode = "";
	private String projectName = "";
	private String projectTypeId = "";
	

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(String projectTypeId) {
		this.projectTypeId = projectTypeId;
	}
	
	
}
