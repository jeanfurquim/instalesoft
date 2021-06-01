package com.instalesoft.instalesoft.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String model;

	private Integer warranty;

	@Column(columnDefinition = "TEXT")
	private String description;
	@Column(columnDefinition = "TEXT")
	private String complement;
	@Column(columnDefinition = "TEXT")
	private String urlManual;
	@Column(columnDefinition = "TEXT")
	private String urlVideo;
	@Column(columnDefinition = "TEXT")
	private String urlWiringDiagram;
	@Column(columnDefinition = "TEXT")
	private String urlImgProd;

	@ManyToMany
	@JoinTable(name = "tb_product_category", 
	joinColumns = @JoinColumn(name = "product_id"), 
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	Set<Category> categories = new HashSet<>();

	public Product() {
	}

	public Product(Long id, String name, String model, Integer warranty, String description, String complement,
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

	public Set<Category> getCategories() {
		return categories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
