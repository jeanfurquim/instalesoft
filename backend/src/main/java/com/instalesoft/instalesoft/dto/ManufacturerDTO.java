package com.instalesoft.instalesoft.dto;

import java.io.Serializable;

import com.instalesoft.instalesoft.entities.Manufacturer;

public class ManufacturerDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String urlImage;

	public ManufacturerDTO() {
	}

	public ManufacturerDTO(Long id, String name, String urlImage) {
		this.id = id;
		this.name = name;
		this.urlImage = urlImage;
	}

	public ManufacturerDTO(Manufacturer entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.urlImage = entity.getUrlImage();
		

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

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	
	

}
