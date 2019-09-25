package com.mercadolibre.prediction;

import static com.mercadolibre.prediction.utils.PredictionUtils.calcArea;

import static com.mercadolibre.prediction.utils.PredictionUtils.calcPerimeter;
import static java.lang.StrictMath.abs;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mercadolibre.prediction.builder.BetasoidePlanetBuilder;
import com.mercadolibre.prediction.builder.FerengisPlanetBuilder;
import com.mercadolibre.prediction.builder.Galaxy;
import com.mercadolibre.prediction.builder.PlanetBuilder;
import com.mercadolibre.prediction.builder.VulcanoPlanetBuilder;
import com.mercadolibre.prediction.repository.IPredictionDao;
import com.mercadolibre.prediction.repository.IPredictionDaoCustom;
import com.mercadolibre.prediction.model.Planet;
import com.mercadolibre.prediction.model.Point;
import com.mercadolibre.prediction.model.Sun;
import com.mercadolibre.prediction.model.entities.Prediction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherAppApplicationTests {

	@Autowired
    IPredictionDao predictionDao;
	
	@Autowired
    IPredictionDaoCustom predictionDaoCustom;
		
	@Test
	public void findyPredictionByDayTest() {
	
		Prediction predictionReturn = predictionDao.findByDay(49);
		
		int idExpected = 49;
		
		assertEquals(idExpected, predictionReturn.getDay());
	}
	
	@Test
	public void findyMaxDayTest() {
			
		Prediction predictionReturn = predictionDaoCustom.findMaxByState("LLUVIA");
		
		assertNotNull(predictionReturn);

	}
	
	@Test
	public void createPlanets() {
		
		Galaxy galaxy = new Galaxy();
    
    	PlanetBuilder vulcanoBuilder = new VulcanoPlanetBuilder();
    	
    	galaxy.setPlanetBuilder(vulcanoBuilder);
    	galaxy.createPlanet();
    	Planet planetVulcano = galaxy.getPlanet();
    	
    	double speed = 5.0; 
    	
    	assertThat(planetVulcano.getSpeed()).isEqualTo(speed);
	}
	
	@Test
	public void calcPerimeterTest() {
	
		List<Planet> planets = auxCreatePlanet();
		
		double perimeter = calcPerimeter(planets);
		
		double perimeterValue = 3000;
		
		assertThat(perimeter).isEqualTo(perimeterValue);
		
	}
	
	@Test
	public void isTriangle() {

		List<Planet> planets = auxCreatePlanet();
		
		double area = calcArea(planets);
		
		boolean test = compareDouble(area, 0.0);
		
		assertThat(test).isEqualTo(false);

	}
	
	@Test
	public void compareTriangle() {
		
		Sun sun = new Sun();
		List<Planet> planets = auxCreatePlanet();
	    	
		for (Planet planet : planets) {
    		planetMove(planet);
		}
		
    	double trianglePlanet = calcArea(planets);
	    	
    	Point pointA = planets.get(0).getPoint();
    	Point pointB = planets.get(1).getPoint();
    	Point pointC = planets.get(2).getPoint();

    	double triangleSun = 0;
	    	
    	triangleSun += calcArea(pointA, pointB, sun.getPosition());
    	triangleSun += calcArea(pointA, sun.getPosition(), pointC);
    	triangleSun += calcArea(sun.getPosition(), pointB, pointC);        
    	
    	boolean test = compareDouble(triangleSun, trianglePlanet);
    	
    	assertThat(test).isEqualTo(false);

    }
	
	private void planetMove(Planet planet) {
	    
    	double movement = planet.getPosition() +  planet.getSpeed();
        planet.setPosition(movement);
        
    }
    
	private static boolean compareDouble(Double one, Double two) {
        return abs(one - two) < 0.000;
    }
	
	private List<Planet> auxCreatePlanet() {
		
		Galaxy galaxy = new Galaxy();
    	
		List<Planet> planets = new ArrayList<Planet>();
    	
    	PlanetBuilder vulcanoBuilder = new VulcanoPlanetBuilder();
    	PlanetBuilder ferengiBuilder = new FerengisPlanetBuilder();
    	PlanetBuilder betasoideBuilder = new BetasoidePlanetBuilder();
    	
    	galaxy.setPlanetBuilder(vulcanoBuilder);
    	galaxy.createPlanet();
    	Planet planetVulcano = galaxy.getPlanet();
    	planets.add(planetVulcano);
    	
    	galaxy.setPlanetBuilder(ferengiBuilder);
    	galaxy.createPlanet();
    	Planet planetFerengi = galaxy.getPlanet();
    	planets.add(planetFerengi);
    	
    	galaxy.setPlanetBuilder(betasoideBuilder);
    	galaxy.createPlanet();
    	Planet planetBetasoide = galaxy.getPlanet();
    	planets.add(planetBetasoide);
    	
    	return planets;
	}

}
