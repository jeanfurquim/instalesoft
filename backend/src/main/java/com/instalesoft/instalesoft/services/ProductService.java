package com.instalesoft.instalesoft.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.instalesoft.instalesoft.dto.CategoryDTO;
import com.instalesoft.instalesoft.dto.ProductDTO;
import com.instalesoft.instalesoft.entities.Category;
import com.instalesoft.instalesoft.entities.Product;
import com.instalesoft.instalesoft.repositories.CategoryRepository;
import com.instalesoft.instalesoft.repositories.ProductRepository;
import com.instalesoft.instalesoft.services.exceptions.DatabaseException;
import com.instalesoft.instalesoft.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> list = repository.findAll(pageRequest);
		return list.map(x -> new ProductDTO(x));
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ProductDTO(entity, entity.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyToDtoEntity(dto, entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			@SuppressWarnings("deprecation")
			Product entity = repository.getOne(id);
			copyToDtoEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");

		}
	}

	private void copyToDtoEntity(ProductDTO dto, Product entity) {

		entity.setName(dto.getName());
		entity.setModel(dto.getModel());
		entity.setWarranty(dto.getWarranty());
		entity.setDescription(dto.getDescription());
		entity.setComplement(dto.getComplement());
		entity.setUrlManual(dto.getUrlManual());
		entity.setUrlVideo(dto.getUrlVideo());
		entity.setUrlWiringDiagram(dto.getUrlWiringDiagram());
		entity.setUrlImgProd(dto.getUrlImgProd());

		entity.getCategories().clear();
		for (CategoryDTO catDto : dto.getCategories()) {
			@SuppressWarnings("deprecation")
			Category category = categoryRepository.getOne(catDto.getId());
			entity.getCategories().add(category);
		}
	}

}
