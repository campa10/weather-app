package com.mercadolibre.prediction.builder;

public class FerengisPlanetBuilder extends PlanetBuilder{
	
	@Override
	public void buildSpeed() {
		planet.setSpeed(-1);
	}

	@Override
	public void buildPosition() {
		planet.setPosition(0);	
	}

	@Override
	public void buildDistance() {
		planet.setDistance(500);
	}

}
