package com.mercadolibre.prediction.repository;

import com.mercadolibre.prediction.model.entities.Prediction;

public interface IPredictionDaoCustom {
	
	Prediction findMaxByState(String state);

}
