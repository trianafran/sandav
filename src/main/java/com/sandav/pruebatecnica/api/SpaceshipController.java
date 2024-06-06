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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sandav.pruebatecnica.dto.SpaceshipDto;
import com.sandav.pruebatecnica.service.SpaceshipService;
import com.sandav.pruebatecnica.util.mappings.SpaceshipMapper;
import com.sandav.pruebatecnica.valueObject.Spaceship;

@RestController("/api/v1")
public class SpaceshipController {
	@Autowired
	SpaceshipService spaceshipService;
	@Autowired
	SpaceshipMapper spaceshipMapper;

	@GetMapping("/spaceships")
	List<SpaceshipDto> findAll(@RequestParam Integer page,@RequestParam Integer size) {
		return mapSpaceshipListToSpaceshipDtoList(spaceshipService.findAll(page, size));
	}
	
	@GetMapping("/spaceships/name/{name}")
	List<SpaceshipDto> findByNameContains(@PathVariable String name) {
		return mapSpaceshipListToSpaceshipDtoList(spaceshipService.findByNameContains(name));
	}
	
	@PostMapping("/spaceships")
	SpaceshipDto create(@RequestBody SpaceshipDto spaceshipDto) {
		return spaceshipMapper.spaceshipToSpaceshipDto(spaceshipService.create(spaceshipMapper.spaceshipDtoToSpaceship(spaceshipDto)));
	}
	
	@PutMapping("/spaceships")
	SpaceshipDto update(@RequestBody SpaceshipDto spaceshipDto) {
		return spaceshipMapper.spaceshipToSpaceshipDto(spaceshipService.update(spaceshipMapper.spaceshipDtoToSpaceship(spaceshipDto)));
	}
	
	@DeleteMapping("/spaceships")
	void delete(Long id) {
		spaceshipService.delete(id);
	}
	
	@GetMapping("/spaceships/id/{id}")
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
