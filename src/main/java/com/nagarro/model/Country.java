package com.nagarro.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="countries")
public class Country {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="sortname")
	private String shortName;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phonecode")
	private int phonecode;
	
	public Country() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhonecode() {
		return phonecode;
	}

	public void setPhonecode(int phonecode) {
		this.phonecode = phonecode;
	}

	public Country(int id, String shortName, String name, int phonecode) {
		super();
		this.id = id;
		this.shortName = shortName;
		this.name = name;
		this.phonecode = phonecode;
	}
	
	

}
