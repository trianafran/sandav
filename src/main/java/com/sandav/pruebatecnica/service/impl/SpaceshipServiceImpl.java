package com.sandav.pruebatecnica.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sandav.pruebatecnica.repository.custom.SpaceshipRepositoryCustom;
import com.sandav.pruebatecnica.service.SpaceshipService;
import com.sandav.pruebatecnica.valueObject.Spaceship;

@Service
public class SpaceshipServiceImpl implements SpaceshipService {
	
	@Autowired
	public SpaceshipRepositoryCustom spaceshipRepositoryCustom;

	@Override
	public List<Spaceship> findAll(Integer page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		return spaceshipRepositoryCustom.findAll(paging);
	}

	@Override
	public List<Spaceship> findByNameContains(String name) {
		return spaceshipRepositoryCustom.findByNameContains(name);
	}

	@Override
	public Spaceship create(Spaceship spaceship) {
		if(spaceship.getId() != null)
			throw new IllegalArgumentException("Id must be null");
		return spaceshipRepositoryCustom.create(spaceship);
	}

	@Override
	public Spaceship update(Spaceship spaceship) {
		if(spaceship.getId() == null || spaceship.getId() < 1)
			throw new IllegalArgumentException("Id must not be less than one");
		return spaceshipRepositoryCustom.update(spaceship);
	}

	@Override
	public void delete(Long id) {
		if(id == null || id < 1)
			throw new IllegalArgumentException("Id must not be less than one.");
		spaceshipRepositoryCustom.findById(id);
		spaceshipRepositoryCustom.deleteById(id);
	}

	@Override
	public Spaceship findById(Long id) {
		if(id == null || id < 1)
			throw new IllegalArgumentException("Id must not be less than one");
		return spaceshipRepositoryCustom.findById(id);
	}
}
