package com.mercadolibre.prediction.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mercadolibre.prediction.service.IPredictionService;
import com.mercadolibre.prediction.dto.PredictionDto;
import com.mercadolibre.prediction.dto.PredictionStateDto;
import com.mercadolibre.prediction.utils.WeatherState;

import org.springframework.ui.Model;

@Controller
public class WeatherAppController {
	
	private static Logger log = LoggerFactory.getLogger(WeatherAppController.class);
		    
	@Autowired
	IPredictionService predictionService;
	
	@GetMapping("/")
    public String main(Model model) {
		
		log.info("WeatherAppController STARTS");
		
		try {
			
			PredictionDto maxRainyDay = predictionService.findMaxRainnyDay(WeatherState.LLUVIA);
			PredictionStateDto predictionStateDto = predictionService.getPredictionState();

			model.addAttribute("maxRainyDay", maxRainyDay);
			model.addAttribute("predictionState", predictionStateDto);
			
		} catch (Exception e) {

			PredictionDto maxRainyDay = new PredictionDto();
			PredictionStateDto predictionStateDto = new PredictionStateDto();

			model.addAttribute("maxRainyDay", maxRainyDay);
			model.addAttribute("predictionState", predictionStateDto);
		}

        return "home";
    }
	
}