package com.instalesoft.instalesoft.dto;

import java.io.Serializable;

import com.instalesoft.instalesoft.entities.Car;

public class CarDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String year;
	private String manufacturerId;



	public CarDTO() {
	}

	public CarDTO(Long id, String name, String year, String manufacturerId) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.manufacturerId = manufacturerId;

	}

	public CarDTO(Car entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.year = entity.getYear();
		this.manufacturerId = entity.getManufacturer().getName();
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}



	

}
