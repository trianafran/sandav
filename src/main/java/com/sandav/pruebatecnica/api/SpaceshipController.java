package com.sandav.pruebatecnica.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sandav.pruebatecnica.dto.SpaceshipCreateDto;
import com.sandav.pruebatecnica.dto.SpaceshipDto;
import com.sandav.pruebatecnica.service.SpaceshipService;
import com.sandav.pruebatecnica.util.mappings.SpaceshipMapper;
import com.sandav.pruebatecnica.valueObject.Spaceship;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/spaceships")
@Tag(name = "Spaceship API")
public class SpaceshipController {
	@Autowired
	SpaceshipService spaceshipService;
	@Autowired
	SpaceshipMapper spaceshipMapper;

	@Operation(summary = "Find a pageabled list of spaceships")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Found a spaceships paginated list", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = SpaceshipDto.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid data supplied", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Spaceships not found", 
			    content = @Content),
			  @ApiResponse(responseCode = "500", description = "Internal server error", 
			    content = @Content)})
	@GetMapping("/")
	ResponseEntity<List<SpaceshipDto>> findAll(@Parameter(description = "Page index", example = "0") @RequestParam(required = true) Integer page,@Parameter(description = "Amount of elements by page", example = "20") @RequestParam(required = true) Integer size) {
		List<SpaceshipDto> spaceshipListDto = mapSpaceshipListToSpaceshipDtoList(spaceshipService.findAll(page, size));;
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		if(spaceshipListDto.isEmpty()) {
			httpStatus = HttpStatus.NOT_FOUND;
		}  else {
			httpStatus = HttpStatus.OK;
		}
		return new ResponseEntity<List<SpaceshipDto>>(spaceshipListDto, httpStatus);
	}
	
	@Operation(summary = "Find a list of spaceships containing the name value")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Found a list of spaceships containing the name value", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = SpaceshipDto.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid data supplied", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Spaceship not found", 
			    content = @Content),
			  @ApiResponse(responseCode = "500", description = "Internal server error", 
			    content = @Content)})
	@GetMapping("/name/{name}")
	ResponseEntity<List<SpaceshipDto>> findByNameContains(@Parameter(description = "Name value for search", example="wing") @PathVariable(required = true) String name) {
		List<SpaceshipDto> spaceshipListDto = mapSpaceshipListToSpaceshipDtoList(spaceshipService.findByNameContains(name));
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		if(spaceshipListDto.isEmpty()) {
			httpStatus = HttpStatus.NOT_FOUND;
		}  else {
			httpStatus = HttpStatus.OK;
		}
		return new ResponseEntity<List<SpaceshipDto>>(spaceshipListDto, httpStatus);
	}
	
	@Operation(summary = "Create a spaceship")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Create a spaceship", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = SpaceshipDto.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid data supplied", 
			    content = @Content),
			  @ApiResponse(responseCode = "500", description = "Internal server error", 
			    content = @Content)})
	@PostMapping("/")	
	ResponseEntity<SpaceshipDto> create(@Parameter(description = "Spaceship JSON Object to create", example="{\"name\":\"Spaceship test created\", \"speed\":1234567890}") @RequestBody(required = true) SpaceshipCreateDto spaceshipCreateDto) {
		SpaceshipDto spaceshipDto= spaceshipMapper.spaceshipToSpaceshipDto(spaceshipService.create(spaceshipMapper.spaceshipCreateDtoToSpaceship(spaceshipCreateDto)));
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		if(spaceshipDto != null && spaceshipDto.getId() != null) {
			httpStatus = HttpStatus.OK;
		}
		return new ResponseEntity<SpaceshipDto>(spaceshipDto, httpStatus);
	}
	
	@Operation(summary = "Upgrade a spaceship entirely")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Update a spaceship", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = SpaceshipDto.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid data supplied", 
			    content = @Content),
			  @ApiResponse(responseCode = "500", description = "Internal server error", 
			    content = @Content)})
	@PutMapping("/")
	ResponseEntity<SpaceshipDto> update(@Parameter(description = "Spaceship JSON Object to update", example="{\"id\" : 1, \"name\":\"Spaceship test updated\", \"speed\": 0987654321}") @RequestBody(required = true) SpaceshipDto spaceshipUpdateDto) {
		SpaceshipDto spaceshipDto = spaceshipMapper.spaceshipToSpaceshipDto(spaceshipService.update(spaceshipMapper.spaceshipDtoToSpaceship(spaceshipUpdateDto))); 
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		if(spaceshipDto != null && spaceshipDto.getId() != null) {
			httpStatus = HttpStatus.OK;
		}
		return new ResponseEntity<SpaceshipDto>(spaceshipDto, httpStatus);
	}
	
	@Operation(summary = "Delete a spaceship by id")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Delete a spaceships by ID", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = SpaceshipDto.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid data supplied", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Spaceship not found", 
			    content = @Content),
			  @ApiResponse(responseCode = "500", description = "Internal server error", 
			    content = @Content)})
	@DeleteMapping("/id/{id}")
	ResponseEntity<Object> delete(@Parameter(description = "Spaceship ID to delete", example="1") @PathVariable(required = true) Long id) {
		spaceshipService.delete(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@Operation(summary = "Find a spaceship by id")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Found a spaceships by ID", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = SpaceshipDto.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid data supplied", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Spaceship not found", 
			    content = @Content),
			  @ApiResponse(responseCode = "500", description = "Internal server error", 
			    content = @Content)})
	@GetMapping("/id/{id}")
	ResponseEntity<SpaceshipDto> findById(@Parameter(description = "Spaceship ID", example="1") @PathVariable(required = true) Long id) {
		SpaceshipDto spaceshipDto = spaceshipMapper.spaceshipToSpaceshipDto(spaceshipService.findById(id));
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		if(spaceshipDto != null && spaceshipDto.getId() != null) {
			httpStatus = HttpStatus.OK;
		}
		return new ResponseEntity<>(spaceshipDto, httpStatus);
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
