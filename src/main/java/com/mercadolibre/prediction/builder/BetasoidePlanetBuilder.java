package com.mercadolibre.prediction.builder;

public class BetasoidePlanetBuilder extends PlanetBuilder {

	@Override
	public void buildSpeed() {
		planet.setSpeed(-3);
	}

	@Override
	public void buildPosition() {
		planet.setPosition(0);	
	}

	@Override
	public void buildDistance() {
		planet.setDistance(2000);
	}
}
