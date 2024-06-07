package com.sandav.pruebatecnica.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.sandav.pruebatecnica.valueObject.Spaceship;


public interface SpaceshipService {
	public List<Spaceship>findAll(Integer page, Integer size);
	public List<Spaceship>findByNameContains(String name);
	public Spaceship create(Spaceship spaceship);
	public Spaceship update(Spaceship spaceship);
	public void delete(Long id);
	public Spaceship findById(Long id);
}
