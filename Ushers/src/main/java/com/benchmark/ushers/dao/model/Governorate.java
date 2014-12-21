package com.benchmark.ushers.dao.model;

import org.springframework.data.domain.Persistable;


public class Governorate implements Persistable<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id ;
	
	private String name = "";

	public Governorate() {
	}
	
	public Governorate(Integer id,String name) {
		this.id = id;
		this.name = name;
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
		return "Customer [id=" + id + ", name=" + getName() + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
