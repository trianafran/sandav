package com.sandav.pruebatecnica.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sandav.pruebatecnica.domain.SpaceshipEntity;

public interface SpaceshipRepository extends CrudRepository<SpaceshipEntity, Long> {
	List<SpaceshipEntity> findAll(Pageable pageable);
	List<SpaceshipEntity> findByNameContains(String name);
}
