package com.mercadolibre.prediction.strategy;

public interface IPrediction {
	
	boolean calcPrediction();
	
	String getPrediction();

	double getPerimeter();
}
