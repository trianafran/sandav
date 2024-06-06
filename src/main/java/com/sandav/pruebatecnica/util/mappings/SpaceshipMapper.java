package com.sandav.pruebatecnica.util.mappings;

import org.mapstruct.Mapper;

import com.sandav.pruebatecnica.domain.SpaceshipEntity;
import com.sandav.pruebatecnica.valueObject.Spaceship;

@Mapper(componentModel = "spring")
public interface SpaceshipMapper {
	Spaceship spaceshipEntityToSpaceship(SpaceshipEntity spaceshipEntiity);
	SpaceshipEntity spaceshipToSpaceshipentity(Spaceship spaceship);
}
