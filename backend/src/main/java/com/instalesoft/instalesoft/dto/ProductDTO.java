package com.instalesoft.instalesoft.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.instalesoft.instalesoft.entities.Category;
import com.instalesoft.instalesoft.entities.Product;

public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String model;
	private Integer warranty;
	private String description;
	private String complement;
	private String urlManual;
	private String urlVideo;
	private String urlWiringDiagram;
	private String urlImgProd;

	private List<CategoryDTO> categories = new ArrayList<>();

	public ProductDTO() {
	}

	public ProductDTO(Long id, String name, String model, Integer warranty, String description, String complement,
			String urlManual, String urlVideo, String urlWiringDiagram, String urlImgProd) {
		this.id = id;
		this.name = name;
		this.model = model;
		this.warranty = warranty;
		this.description = description;
		this.complement = complement;
		this.urlManual = urlManual;
		this.urlVideo = urlVideo;
		this.urlWiringDiagram = urlWiringDiagram;
		this.urlImgProd = urlImgProd;
	}

	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.model = entity.getModel();
		this.warranty = entity.getWarranty();
		this.description = entity.getDescription();
		this.complement = entity.getComplement();
		this.urlManual = entity.getUrlManual();
		this.urlVideo = entity.getUrlVideo();
		this.urlWiringDiagram = entity.getUrlWiringDiagram();
		this.urlImgProd = entity.getUrlImgProd();

	}

	
	public ProductDTO(Product entity, Set<Category> categories) {
		this(entity);
		categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getWarranty() {
		return warranty;
	}

	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getUrlManual() {
		return urlManual;
	}

	public void setUrlManual(String urlManual) {
		this.urlManual = urlManual;
	}

	public String getUrlVideo() {
		return urlVideo;
	}

	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}

	public String getUrlWiringDiagram() {
		return urlWiringDiagram;
	}

	public void setUrlWiringDiagram(String urlWiringDiagram) {
		this.urlWiringDiagram = urlWiringDiagram;
	}

	public String getUrlImgProd() {
		return urlImgProd;
	}

	public void setUrlImgProd(String urlImgProd) {
		this.urlImgProd = urlImgProd;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}

}
