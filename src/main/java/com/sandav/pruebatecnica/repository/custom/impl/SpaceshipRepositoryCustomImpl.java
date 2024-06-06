package com.sandav.pruebatecnica.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.sandav.pruebatecnica.domain.SpaceshipEntity;
import com.sandav.pruebatecnica.repository.SpaceshipRepository;
import com.sandav.pruebatecnica.repository.custom.SpaceshipRepositoryCustom;
import com.sandav.pruebatecnica.util.mappings.SpaceshipMapper;
import com.sandav.pruebatecnica.valueObject.Spaceship;

public class SpaceshipRepositoryCustomImpl implements SpaceshipRepositoryCustom{
	
	@Autowired
	SpaceshipRepository spaceshipRepository;
	@Autowired
	SpaceshipMapper spaceshipMapper;

	@Override
	public List<Spaceship> findAll(Pageable pageable) {
		List<SpaceshipEntity> spaceshipEntityList = spaceshipRepository.findAll(pageable).toList();
		return mapSpaceshipEntityListToSpaceshipList(spaceshipEntityList);
	}

	@Override
	public List<Spaceship> findByNameContains(String name) {
		List<SpaceshipEntity> spaceshipEntityList = spaceshipRepository.findByNameContains(name);
		return mapSpaceshipEntityListToSpaceshipList(spaceshipEntityList);
	}

	@Override
	public Spaceship create(Spaceship spaceship) {
		SpaceshipEntity spaceshipEntity = spaceshipMapper.spaceshipToSpaceshipentity(spaceship);
		spaceshipRepository.save(spaceshipEntity);
		return spaceshipMapper.spaceshipEntityToSpaceship(spaceshipEntity);
	}

	@Override
	public Spaceship update(Spaceship spaceship) {
		SpaceshipEntity spaceshipEntity = spaceshipMapper.spaceshipToSpaceshipentity(spaceship);
		spaceshipRepository.save(spaceshipEntity);
		return spaceshipMapper.spaceshipEntityToSpaceship(spaceshipEntity);
	}

	@Override
	public Spaceship findById(Long id) {
		return spaceshipMapper.spaceshipEntityToSpaceship(spaceshipRepository.findById(id).get());
	}
	
	private List<Spaceship> mapSpaceshipEntityListToSpaceshipList(List<SpaceshipEntity> spaceshipEntityList){
		List<Spaceship> spaceshipList = new ArrayList<>();
		
		if (spaceshipEntityList.isEmpty()) {
			return spaceshipList;
		}
		
		for(SpaceshipEntity spaceshipEntity : spaceshipEntityList) {
			Spaceship spaceship = spaceshipMapper.spaceshipEntityToSpaceship(spaceshipEntity);
			spaceshipList.add(spaceship);
		}
		return spaceshipList;
	}
}
