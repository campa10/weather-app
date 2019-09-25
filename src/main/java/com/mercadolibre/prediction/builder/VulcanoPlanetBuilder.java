package com.mercadolibre.prediction.builder;

public class VulcanoPlanetBuilder extends PlanetBuilder {

	@Override
	public void buildSpeed() {
		planet.setSpeed(5);
	}

	@Override
	public void buildPosition() {
		planet.setPosition(0);	
	}

	@Override
	public void buildDistance() {
		planet.setDistance(1000);
	}

}
