package com.sandav.pruebatecnica.valueObject;

import lombok.Data;

@Data
public class Spaceship {
	private Long id;
	private String name;
	private Long speed; // Max speed. Unit: km/h
}
