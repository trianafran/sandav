package com.sandav.pruebatecnica.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sandav.pruebatecnica.domain.SpaceshipEntity;

@Repository
public interface SpaceshipRepository extends CrudRepository<SpaceshipEntity, Long> {
	Page<SpaceshipEntity> findAll(Pageable pageable);
	List<SpaceshipEntity> findByNameContainsIgnoreCase(String name);
}
