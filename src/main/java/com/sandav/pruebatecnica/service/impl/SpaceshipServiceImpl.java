package com.sandav.pruebatecnica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		return spaceshipRepositoryCustom.create(spaceship);
	}

	@Override
	public Spaceship update(Spaceship spaceship) {
		return spaceshipRepositoryCustom.update(spaceship);
	}

	@Override
	public void delete(Long id) {
		spaceshipRepositoryCustom.deleteById(id);
	}

	@Override
	public Spaceship findById(Long id) {
		return spaceshipRepositoryCustom.findById(id);
	}
}
