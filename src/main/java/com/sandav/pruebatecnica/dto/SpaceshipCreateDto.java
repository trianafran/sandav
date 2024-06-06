package com.sandav.pruebatecnica.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SpaceshipCreateDto {
	@Size(min = 0, max = 255)
	private String name;
	private Long speed; // Max speed. Unit: km/h
}
