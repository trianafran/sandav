package com.sandav.pruebatecnica.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sandav.pruebatecnica.dto.SpaceshipDto;
import com.sandav.pruebatecnica.service.SpaceshipService;
import com.sandav.pruebatecnica.util.mappings.SpaceshipMapper;
import com.sandav.pruebatecnica.valueObject.Spaceship;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/spaceships")
@Tag(name = "Spaceship API")
public class SpaceshipController {
	@Autowired
	SpaceshipService spaceshipService;
	@Autowired
	SpaceshipMapper spaceshipMapper;

	@Operation(summary = "Get a pageabled list of spaceships")
	@GetMapping("/")
	List<SpaceshipDto> findAll(@RequestParam Integer page,@RequestParam Integer size) {
		return mapSpaceshipListToSpaceshipDtoList(spaceshipService.findAll(page, size));
	}
	
	@Operation(summary = "Get a list of spaceships containing the name value")
	@GetMapping("/name/{name}")
	List<SpaceshipDto> findByNameContains(@PathVariable String name) {
		return mapSpaceshipListToSpaceshipDtoList(spaceshipService.findByNameContains(name));
	}
	
	@Operation(summary = "Create a spaceship")
	@PostMapping("/")
	SpaceshipDto create(@RequestBody SpaceshipDto spaceshipDto) {
		return spaceshipMapper.spaceshipToSpaceshipDto(spaceshipService.create(spaceshipMapper.spaceshipDtoToSpaceship(spaceshipDto)));
	}
	
	@Operation(summary = "Upgrade a spaceship entirely")
	@PutMapping("/")
	SpaceshipDto update(@RequestBody SpaceshipDto spaceshipDto) {
		return spaceshipMapper.spaceshipToSpaceshipDto(spaceshipService.update(spaceshipMapper.spaceshipDtoToSpaceship(spaceshipDto)));
	}
	
	@Operation(summary = "Delete a spaceship by id")
	@DeleteMapping("/")
	void delete(Long id) {
		spaceshipService.delete(id);
	}
	
	@Operation(summary = "Get a spaceship by id")
	@GetMapping("/id/{id}")
	SpaceshipDto findById(@PathVariable Long id) {
		return spaceshipMapper.spaceshipToSpaceshipDto(spaceshipService.findById(id));
	}
	
	private List<SpaceshipDto> mapSpaceshipListToSpaceshipDtoList(List<Spaceship> spaceshipList){
		List<SpaceshipDto> spaceshipDtoList = new ArrayList<>();
		
		if (spaceshipList.isEmpty()) {
			return spaceshipDtoList;
		}
		
		for(Spaceship spaceship : spaceshipList) {
			SpaceshipDto spaceshipDto = spaceshipMapper.spaceshipToSpaceshipDto(spaceship);
			spaceshipDtoList.add(spaceshipDto);
		}
		return spaceshipDtoList;
	}
}
