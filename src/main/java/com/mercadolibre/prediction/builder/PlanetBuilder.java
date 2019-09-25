package com.mercadolibre.prediction.builder;

import com.mercadolibre.prediction.model.Planet;

public abstract class PlanetBuilder {
	
	protected Planet planet;
	
	public Planet getPlanet() {
		return planet;
	}
	
	public void createPlanet() {
		planet = new Planet();
	}
		
	public abstract void buildSpeed();
	
	public abstract void buildPosition();
	
	public abstract void buildDistance();

}
