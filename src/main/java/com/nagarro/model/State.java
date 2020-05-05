package com.nagarro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="states")
public class State {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="country_id")
	private int countryId;
	
	@Column(name="name")
	private String name;

	public State() {
		
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State(int id, int countryId, String name) {
		super();
		this.id = id;
		this.countryId = countryId;
		this.name = name;
	}
	
	

}
