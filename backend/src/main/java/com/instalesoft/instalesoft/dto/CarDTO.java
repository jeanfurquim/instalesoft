package com.instalesoft.instalesoft.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.instalesoft.instalesoft.entities.Car;
import com.instalesoft.instalesoft.entities.Manufacturer;

public class CarDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String year;

	private List<ManufacturerDTO> manufactures = new ArrayList<>();

	public CarDTO() {
	}

	public CarDTO(Long id, String name, String year) {
		this.id = id;
		this.name = name;
		this.year = year;

	}

	public CarDTO(Car entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.year = entity.getYear();

	}

	public CarDTO(Car entity, Set<Manufacturer> manufactures) {
		this(entity);
		manufactures.forEach(cat -> this.manufactures.add(new ManufacturerDTO(cat)));
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

	public List<ManufacturerDTO> getManufactures() {
		return manufactures;
	}

	public void setManufacturer(List<ManufacturerDTO> manufactures) {
		this.manufactures = manufactures;
	}

}
