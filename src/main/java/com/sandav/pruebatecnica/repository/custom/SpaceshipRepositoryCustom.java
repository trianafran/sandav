package com.sandav.pruebatecnica.repository.custom;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sandav.pruebatecnica.valueObject.Spaceship;

public interface SpaceshipRepositoryCustom {
	List<Spaceship> findAll(Pageable pageable);
	List<Spaceship>findByNameContains(String name);
	Spaceship create(Spaceship spaceship);
	Spaceship update(Spaceship spaceship);
	Spaceship findById(Long id);
	void deleteById(Long id);
	
}
