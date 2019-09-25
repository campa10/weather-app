package com.mercadolibre.prediction.service;

import java.sql.SQLException;

import com.mercadolibre.prediction.dto.PredictionDto;
import com.mercadolibre.prediction.dto.PredictionStateDto;
import com.mercadolibre.prediction.utils.WeatherState;

public interface IPredictionService {

	PredictionDto findByDay(int day) throws SQLException;

	Long countByState(WeatherState state);

	PredictionDto findMaxRainnyDay(WeatherState state);

	PredictionStateDto getPredictionState();

}
