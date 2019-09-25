package com.mercadolibre.prediction.builder;

import com.mercadolibre.prediction.model.Planet;

public class Galaxy {
	
	private PlanetBuilder planetBuilder;
	
	public void setPlanetBuilder(PlanetBuilder planetBuilder) {
		this.planetBuilder = planetBuilder;
	}
	
	public Planet getPlanet() {
		return planetBuilder.getPlanet();
	}
	
	public void createPlanet() {
		planetBuilder.createPlanet();
		planetBuilder.buildDistance();
		planetBuilder.buildPosition();
		planetBuilder.buildSpeed();
	}

}
