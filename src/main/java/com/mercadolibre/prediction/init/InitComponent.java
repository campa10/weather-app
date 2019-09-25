package com.mercadolibre.prediction.init;

import static org.slf4j.LoggerFactory.getLogger;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercadolibre.prediction.builder.BetasoidePlanetBuilder;
import com.mercadolibre.prediction.builder.FerengisPlanetBuilder;
import com.mercadolibre.prediction.builder.Galaxy;
import com.mercadolibre.prediction.builder.PlanetBuilder;
import com.mercadolibre.prediction.builder.VulcanoPlanetBuilder;
import com.mercadolibre.prediction.repository.IPredictionDao;
import com.mercadolibre.prediction.model.Planet;
import com.mercadolibre.prediction.model.Sun;
import com.mercadolibre.prediction.model.entities.Prediction;
import com.mercadolibre.prediction.strategy.IPrediction;
import com.mercadolibre.prediction.strategy.PredictionManager;


@Component
public class InitComponent implements InitializingBean {
	
	private static final Logger log = getLogger(InitComponent.class);
	
    @Autowired
    private IPredictionDao predictionDao;

    private Sun sun;
    
    private List<Planet> planets;
    
    private int day;
   
    @Override
	public void afterPropertiesSet() throws Exception {
    	
    	log.info("start creating enviroment...");
    	
    	this.sun = new Sun();
    	
    	Galaxy galaxy = new Galaxy();
    	
    	this.planets = new ArrayList<Planet>();
    	
    	PlanetBuilder vulcanoBuilder = new VulcanoPlanetBuilder();
    	PlanetBuilder ferengiBuilder = new FerengisPlanetBuilder();
    	PlanetBuilder betasoideBuilder = new BetasoidePlanetBuilder();
    	
    	galaxy.setPlanetBuilder(vulcanoBuilder);
    	galaxy.createPlanet();
    	Planet planetVulcano = galaxy.getPlanet();
    	this.planets.add(planetVulcano);
    	
    	galaxy.setPlanetBuilder(ferengiBuilder);
    	galaxy.createPlanet();
    	Planet planetFerengi = galaxy.getPlanet();
    	this.planets.add(planetFerengi);
    	
    	galaxy.setPlanetBuilder(betasoideBuilder);
    	galaxy.createPlanet();
    	Planet planetBetasoide = galaxy.getPlanet();
    	this.planets.add(planetBetasoide);
        
    	int endDays = this.calculateDays();
    	
        for(day = 1; day < endDays; day++) {
        	
			Prediction prediction = this.getDailyValue();
			if (prediction != null) {
				log.info("### PREDICTION:" + prediction.toString());
				this.predictionDao.save(prediction);
			}
		}

        log.info("end creating enviroment...");
		
	}
    
    public Prediction getDailyValue() {
    	
    	log.info("getDailyValue...");
    	
    	List<IPrediction> predictions = PredictionManager.LoadStategies(planets, sun);
    	
    	String state = null;
    	
    	double perimeter = 0.0;
    	
    	for (IPrediction iPrediction : predictions) {
    		log.info("iPrediction: " + iPrediction.toString());
    		
    		state = iPrediction.getPrediction();
    		
    		log.info("planet a: " + planets.get(0).toString());
    		log.info("planet b: " + planets.get(1).toString());
    		log.info("planet c: " + planets.get(2).toString());
    		
			if(state != null) {
				log.info("day: " + day);
				log.info("state: " + state);
				log.info("perimeter: " + iPrediction.getPerimeter());
				perimeter = iPrediction.getPerimeter();
				break;
			}
		}

    	for (Planet planet : planets) {
    		planetMove(planet);
		}
    	
		return new Prediction(state, day, perimeter);		
    	
    }
    
    private void planetMove(Planet planet) {
    
    	double movement = planet.getPosition() +  planet.getSpeed();
        planet.setPosition(movement);
        
    }
    
    private Integer calculateDays(){
		
    	Date startDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, 10);
		Date finalDate = calendar.getTime();
		
		return (int) ((finalDate.getTime()-startDate.getTime())/86400000);
	}

}
