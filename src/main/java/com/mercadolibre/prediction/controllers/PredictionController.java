package com.mercadolibre.prediction.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.prediction.dto.PredictionDto;
import com.mercadolibre.prediction.service.IPredictionService;
import com.mercadolibre.prediction.utils.Constants;
import com.mercadolibre.prediction.utils.WeatherState;

@RestController
public class PredictionController {
	
	private static Logger log = LoggerFactory.getLogger(PredictionController.class);
		
	@Autowired
	IPredictionService predictionService;
	
	@GetMapping(value = "/prediction/{day}")
	public ResponseEntity<?> prediction(@PathVariable("day") int day) {
		
		log.info("PREDICT WEATHER STARTS");
		log.info("Input variable: " + day);
		Map<String,Object> response = new HashMap<>();
		PredictionDto predictionDto = null;
		
		try {
			predictionDto = predictionService.findByDay(day);
			if(predictionDto == null) {
				response.put("mensaje", Constants.PREDICTION_NOT_FOUND);
				log.info("PREDICT WEATHER ENDS");
		        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
			}	
		} catch (Exception e) {
			response.put("mensaje", Constants.PREDICTION_SERVER_ERROR);
			log.info("PREDICT WEATHER ENDS");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		log.info("PREDICT WEATHER ENDS");
		return new ResponseEntity<PredictionDto>(predictionDto, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/predictionMax")
	public ResponseEntity<?> maxRainnyDay() {
		
		log.info("MAX RAINNY STARTS");
		
		Map<String,Object> response = new HashMap<>();
		PredictionDto predictionDto = null;
		
		try {
			predictionDto = predictionService.findMaxRainnyDay(WeatherState.LLUVIA);
			if(predictionDto == null) {
				response.put("mensaje", Constants.PREDICTION_NOT_FOUND);
				log.info("PREDICT WEATHER ENDS");
		        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
			}	
		} catch (Exception e) {
			response.put("mensaje", Constants.PREDICTION_SERVER_ERROR);
			log.info("MAX RAINNY ENDS");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		log.info("MAX RAINNY ENDS");
		return new ResponseEntity<PredictionDto>(predictionDto, HttpStatus.OK);
	}
		
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> nullPointerHandler(){
		Map<String, Object> response = new HashMap<>();
		response.put("mensaje", Constants.PREDICTION_NOT_FOUND);
		log.info("PREDICT WEATHER ENDS");
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> numberFormatHandler(){
    	Map<String, Object> response = new HashMap<>();
    	response.put("mensaje", Constants.PREDICTION_BAD_REQUEST);
		log.info("numberFormatHandler");
		log.info("PREDICT WEATHER ENDS");
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
    }

}
