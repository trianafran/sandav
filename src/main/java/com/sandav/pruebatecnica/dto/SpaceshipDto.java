package com.sandav.pruebatecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SpaceshipDto {
	@NotNull
	@Size(min = 1)
	@Schema(example = "1")
	private Long id;
	@Size(min = 0, max = 255)
	private String name;
	private Long speed; // Max speed. Unit: km/h
}
