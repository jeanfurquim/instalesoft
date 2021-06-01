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

import com.instalesoft.instalesoft.dto.CarDTO;
import com.instalesoft.instalesoft.dto.ProductDTO;
import com.instalesoft.instalesoft.entities.Car;
import com.instalesoft.instalesoft.entities.Product;
import com.instalesoft.instalesoft.repositories.CarRepository;
import com.instalesoft.instalesoft.repositories.CategoryRepository;
import com.instalesoft.instalesoft.repositories.ProductRepository;
import com.instalesoft.instalesoft.services.exceptions.DatabaseException;
import com.instalesoft.instalesoft.services.exceptions.ResourceNotFoundException;

@Service
public class CarService {

	@Autowired
	private CarRepository repository;

	@Autowired
	private ProductRepository productRepository;


	

	@Transactional(readOnly = true)
	public Page<CarDTO> findAllPaged(PageRequest pageRequest) {
		Page<Car> list = repository.findAll(pageRequest);
		return list.map(x -> new CarDTO(x));
	}

	@Transactional(readOnly = true)
	public CarDTO findById(Long id) {
		Optional<Car> obj = repository.findById(id);
		Car entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new CarDTO(entity, entity.getProducts());
	}

	@Transactional
	public CarDTO insert(CarDTO dto) {
		Car entity = new Car();
		copyToDtoEntity(dto, entity);
		entity = repository.save(entity);
		return new CarDTO(entity);
	}

	@Transactional
	public CarDTO update(Long id, CarDTO dto) {
		try {
			@SuppressWarnings("deprecation")
			Car entity = repository.getOne(id);
			copyToDtoEntity(dto, entity);
			entity = repository.save(entity);
			return new CarDTO(entity);
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

	private void copyToDtoEntity(CarDTO dto, Car entity) {

		entity.setName(dto.getName());
		entity.setYear(dto.getYear());

		entity.getProducts().clear();
		for (ProductDTO catDto : dto.getProducts()) {
			@SuppressWarnings("deprecation")
			Product product = productRepository.getOne(catDto.getId());
			entity.getProducts().add(product);

		}

	}
}
