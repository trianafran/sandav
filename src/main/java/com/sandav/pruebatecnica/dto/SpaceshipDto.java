package com.sandav.pruebatecnica.dto;

import lombok.Data;

@Data
public class SpaceshipDto {
	private Long id;
	private String name;
	private Long speed; // Max speed. Unit: km/h
}
