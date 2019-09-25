package com.mercadolibre.prediction.strategy;

import java.util.List;

import org.slf4j.Logger;

import com.mercadolibre.prediction.model.Planet;
import com.mercadolibre.prediction.model.Sun;
import com.mercadolibre.prediction.utils.WeatherState;
import static com.mercadolibre.prediction.utils.PredictionUtils.isTriangle;
import static org.slf4j.LoggerFactory.getLogger;

public class DroughtPrediction implements IPrediction{
	
	private static final Logger log = getLogger(DroughtPrediction.class);
		
	protected List<Planet> planets;

	protected Sun sun;
	
	protected double perimeter;
	
	public DroughtPrediction(List<Planet> planets, Sun sun) {
		this.planets = planets;
		this.sun = sun;
		this.perimeter = 0.0;
	}
	

	@Override
	public boolean calcPrediction() {
				
		if(isTriangle(this.planets)){
			return isTriangle(this.planets , this.sun);
		} else {
			return false;
		}
		
	}

	@Override
	public String getPrediction() {
	
		if(calcPrediction()) {
			log.info("creating SEQUIA..");
			return WeatherState.SEQUIA.toString();
		} else {
			return null;
		}
		
	}
	
	@Override
	public double getPerimeter() {
		return this.perimeter;
	}

}
