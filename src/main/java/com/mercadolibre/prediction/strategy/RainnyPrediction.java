package com.mercadolibre.prediction.strategy;

import java.util.List;

import org.slf4j.Logger;

import com.mercadolibre.prediction.model.Planet;
import com.mercadolibre.prediction.model.Sun;
import com.mercadolibre.prediction.utils.WeatherState;

import static com.mercadolibre.prediction.utils.PredictionUtils.isTriangle;
import static org.slf4j.LoggerFactory.getLogger;
import static com.mercadolibre.prediction.utils.PredictionUtils.compareTriangle;
import static com.mercadolibre.prediction.utils.PredictionUtils.calcPerimeter;

public class RainnyPrediction implements IPrediction{

	private static final Logger log = getLogger(RainnyPrediction.class);
	
	protected List<Planet> planets;

	protected Sun sun;
	
	private double perimeter;
	
	public RainnyPrediction(List<Planet> planets, Sun sun) {
		this.planets = planets;
		this.sun = sun;
		this.perimeter = 0.0;
	}

	@Override
	public boolean calcPrediction() {
		
		if (isTriangle(this.planets)) {
            return true;
		} else {
			return compareTriangle(this.planets , this.sun);
		}
		
	}

	@Override
	public String getPrediction() {
		
		if(calcPrediction()) {
			log.info("creating LLUVIA..");
			log.info("creating LLUVIA: " + calcPerimeter(planets));
			this.perimeter = calcPerimeter(planets);
			log.info("creating LLUVIA: " + this.perimeter);
			return WeatherState.LLUVIA.toString();
		} else {
			return null;
		}

	}

	@Override
	public double getPerimeter() {
		return this.perimeter;
	}
	
}
