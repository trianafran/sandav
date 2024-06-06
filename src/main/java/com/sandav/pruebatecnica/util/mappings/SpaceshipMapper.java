package com.sandav.pruebatecnica.util.mappings;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sandav.pruebatecnica.domain.SpaceshipEntity;
import com.sandav.pruebatecnica.dto.SpaceshipCreateDto;
import com.sandav.pruebatecnica.dto.SpaceshipDto;
import com.sandav.pruebatecnica.valueObject.Spaceship;

@Mapper(componentModel = "spring")
public interface SpaceshipMapper {
	Spaceship spaceshipEntityToSpaceship(SpaceshipEntity spaceshipEntity);
	SpaceshipEntity spaceshipToSpaceshipEntity(Spaceship spaceship);
	
	Spaceship spaceshipDtoToSpaceship(SpaceshipDto spaceshipDto);
	SpaceshipDto spaceshipToSpaceshipDto(Spaceship spaceship);
	
	@Mapping(target = "id", ignore = true)
	Spaceship spaceshipCreateDtoToSpaceship(SpaceshipCreateDto spaceshipCreateDto);
}
