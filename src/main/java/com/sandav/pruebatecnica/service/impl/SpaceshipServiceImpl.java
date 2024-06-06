package com.sandav.pruebatecnica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.sandav.pruebatecnica.repository.SpaceshipRepository;
import com.sandav.pruebatecnica.service.SpaceshipService;
import com.sandav.pruebatecnica.valueObject.Spaceship;

public class SpaceshipServiceImpl implements SpaceshipService {
	
	@Autowired
	public SpaceshipRepository spaceshipRepository;

	@Override
	public List<Spaceship> findAll(Integer page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		spaceshipRepository.findAll(paging);
		return null;
	}

	@Override
	public List<Spaceship> findByNameContains(String name) {
		spaceshipRepository.findByNameContains(name);
		return null;
	}

	@Override
	public Spaceship create(Spaceship spaceship) {
		spaceshipRepository.save(null);
		return null;
	}

	@Override
	public Spaceship update(Spaceship spaceship) {
		spaceshipRepository.save(null);
		return null;
	}

	@Override
	public void delete(Long id) {
		spaceshipRepository.deleteById(id);
	}

	@Override
	public Spaceship findById(Long id) {
		spaceshipRepository.findById(id);
		return null;
	}
}
