package com.mercadolibre.prediction.strategy;

import java.util.LinkedList;

import java.util.List;

import com.mercadolibre.prediction.model.Planet;
import com.mercadolibre.prediction.model.Sun;

public class PredictionManager {
	
	public static List<IPrediction> LoadStategies(List<Planet> planets, Sun sun) {
		
		List<IPrediction> strategyList = new LinkedList<>();
		
		DroughtPrediction droughtPrediction = new DroughtPrediction(planets, sun);
		RainnyPrediction rainnyPrediction = new RainnyPrediction(planets, sun);
		SunnyPrediction sunnyPrediction = new SunnyPrediction(planets, sun);
		
		strategyList.add(droughtPrediction);
		strategyList.add(rainnyPrediction);
		strategyList.add(sunnyPrediction);
		
		return strategyList;
	}
	
}
