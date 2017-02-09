package com.benchmark.ushers.dao.model;

import org.springframework.data.domain.Persistable;

public class UsherRate implements Persistable<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id ;
	private String usherCode = "";
	private String productId = "";
	private String projectCode = "";
	private String projectTypeId = "";
	private String clientFeedback = "";
	private String supervisorFeedback = "";
	private String usherCoordinatorFeedback = "";
	private String rate = "";

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

	public String getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(String projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	/**
	 * @return the usherCode
	 */
	public String getUsherCode() {
		return usherCode;
	}

	/**
	 * @param usherCode the usherCode to set
	 */
	public void setUsherCode(String usherCode) {
		this.usherCode = usherCode;
	}

	/**
	 * @return the clientFeedback
	 */
	public String getClientFeedback() {
		return clientFeedback;
	}

	/**
	 * @param clientFeedback the clientFeedback to set
	 */
	public void setClientFeedback(String clientFeedback) {
		this.clientFeedback = clientFeedback;
	}

	/**
	 * @return the supervisorFeedback
	 */
	public String getSupervisorFeedback() {
		return supervisorFeedback;
	}

	/**
	 * @param supervisorFeedback the supervisorFeedback to set
	 */
	public void setSupervisorFeedback(String supervisorFeedback) {
		this.supervisorFeedback = supervisorFeedback;
	}

	/**
	 * @return the usherCoordinatorFeedback
	 */
	public String getUsherCoordinatorFeedback() {
		return usherCoordinatorFeedback;
	}

	/**
	 * @param usherCoordinatorFeedback the usherCoordinatorFeedback to set
	 */
	public void setUsherCoordinatorFeedback(String usherCoordinatorFeedback) {
		this.usherCoordinatorFeedback = usherCoordinatorFeedback;
	}

	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

	/**
	 * @return the id
	 */
	@Override
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public boolean isNew() {
		return id == null;
	}

	@Override
	public String toString() {
		return "UsherRate [id=" + id + ", usherCode=" + usherCode
				+ ", productId=" + productId + ", projectCode=" + projectCode
				+ ", projectTypeId=" + projectTypeId + ", clientFeedback="
				+ clientFeedback + ", supervisorFeedback=" + supervisorFeedback
				+ ", usherCoordinatorFeedback=" + usherCoordinatorFeedback
				+ ", rate=" + rate + "]";
	}
	
}
