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

import com.instalesoft.instalesoft.dto.ManufacturerDTO;
import com.instalesoft.instalesoft.entities.Manufacturer;
import com.instalesoft.instalesoft.repositories.ManufacturerRepository;
import com.instalesoft.instalesoft.services.exceptions.DatabaseException;
import com.instalesoft.instalesoft.services.exceptions.ResourceNotFoundException;

@Service
public class ManufacturerService {

	@Autowired
	private ManufacturerRepository repository;

	@Transactional(readOnly = true)
	public Page<ManufacturerDTO> findAllPaged(PageRequest pageRequest) {
		Page<Manufacturer> list = repository.findAll(pageRequest);
		return list.map(x -> new ManufacturerDTO(x));
	}

	@Transactional(readOnly = true)
	public ManufacturerDTO findById(Long id) {
		Optional<Manufacturer> obj = repository.findById(id);
		Manufacturer entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ManufacturerDTO(entity);
	}

	@Transactional
	public ManufacturerDTO insert(ManufacturerDTO dto) {
		Manufacturer entity = new Manufacturer();
		entity.setName(dto.getName());
		entity.setUrlImage(dto.getUrlImage());
		entity = repository.save(entity);
		return new ManufacturerDTO(entity);
	}

	@Transactional
	public ManufacturerDTO update(Long id, ManufacturerDTO dto) {
		try {
			Manufacturer entity = repository.getById(id);
			entity.setName(dto.getName());
			entity.setUrlImage(dto.getUrlImage());
			entity = repository.save(entity);
			return new ManufacturerDTO(entity);
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
}
