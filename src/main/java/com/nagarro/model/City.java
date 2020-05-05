package com.nagarro.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cities")
public class City {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="state_id")
	private int stateId;
	
	public City() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int state_id) {
		this.stateId = state_id;
	}

	public City(int id, String name, int state_id) {
		super();
		this.id = id;
		this.name = name;
		this.stateId = state_id;
	}
	

}
