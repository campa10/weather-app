package com.mercadolibre.prediction.service;

import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.prediction.dto.PredictionDto;
import com.mercadolibre.prediction.dto.PredictionStateDto;
import com.mercadolibre.prediction.model.entities.Prediction;
import com.mercadolibre.prediction.utils.WeatherState;
import com.mercadolibre.prediction.repository.IPredictionDao;
import com.mercadolibre.prediction.repository.IPredictionDaoCustom;

@Service
public class PredictionServiceImpl implements IPredictionService{
	
	@Autowired
    IPredictionDao predictionDao;
	
	@Autowired
    IPredictionDaoCustom predictionDaoCustom;
	
	public PredictionDto findByDay(int day) throws SQLException {
    	
    	Prediction prediction = predictionDao.findByDay(day);
    	
    	PredictionDto predictionDto = null;
    	
    	if(prediction != null) {
    		predictionDto = new PredictionDto(prediction.getState(), prediction.getDay(), prediction.getPerimeter());
    	}
    	
        return predictionDto;
    }

	@Override
	public Long countByState(WeatherState state) {
		return predictionDao.countByState(state.toString());
	}

	@Override
	public PredictionDto findMaxRainnyDay(WeatherState state) {
		
		PredictionDto predictionDto = null;
		
		Prediction prediction = predictionDaoCustom.findMaxByState(state.toString());
		
    	if(prediction != null) {
    		predictionDto = new PredictionDto(prediction.getState(), prediction.getDay(), prediction.getPerimeter());
    	}
    	
        return predictionDto;

	}

	@Override
	public PredictionStateDto getPredictionState() {
		
		PredictionStateDto predictionStateDto = new PredictionStateDto();
		
		predictionStateDto.setNormalDays(this.countByState(WeatherState.NORMAL));
		predictionStateDto.setDroughtDays(this.countByState(WeatherState.SEQUIA));
		predictionStateDto.setSunnyDays(this.countByState(WeatherState.CONDICIONES_OPTIMAS));
		predictionStateDto.setRainyDays(this.countByState(WeatherState.LLUVIA));
				
		return predictionStateDto;
	}

}
