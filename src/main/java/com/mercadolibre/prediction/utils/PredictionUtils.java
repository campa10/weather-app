package com.mercadolibre.prediction.utils;

import java.util.ArrayList;
import java.util.List;
import static java.lang.StrictMath.abs;
import com.mercadolibre.prediction.model.Planet;
import com.mercadolibre.prediction.model.Point;
import com.mercadolibre.prediction.model.Sun;

public class PredictionUtils {
	
	public static double calcPerimeter(List<Planet> planets) {
		
		List<Point> planetPoints = new ArrayList<Point>();
		
		for (Planet planet : planets) {
			planetPoints.add(planet.getPoint());
		}
		
		double perimeter = 0;
		
		perimeter +=  Math.sqrt( Math.pow( (planetPoints.get(0).getX() - planetPoints.get(1).getX()), 2 ) + Math.pow( (planetPoints.get(0).getY() - planetPoints.get(1).getY()), 2 ) );
		perimeter +=  Math.sqrt( Math.pow( (planetPoints.get(1).getX() - planetPoints.get(2).getX()), 2 ) + Math.pow( (planetPoints.get(1).getY() - planetPoints.get(2).getY()), 2 ) );
		perimeter +=  Math.sqrt( Math.pow( (planetPoints.get(0).getX() - planetPoints.get(2).getX()), 2 ) + Math.pow( (planetPoints.get(0).getY() - planetPoints.get(2).getY()), 2 ) );
			
		return perimeter;
        
	}
	
	public static boolean isTriangle(List<Planet> planets) {
		
		double area = calcArea(planets);
		
		return compareDouble(area, 0.0);
		
	}
	
	public static boolean isTriangle(List<Planet> planets, Sun sun) {
		
		double area = calcArea(planets, sun);
		
		return compareDouble(area, 0.0);
		
	}
	

    public static double calcArea(List<Planet> planets) {
    	
    	Point pointA = planets.get(0).getPoint();
    	Point pointB = planets.get(1).getPoint();
    	Point pointC = planets.get(2).getPoint();
		
        return abs((pointA.getX() - pointC.getX()) * (pointB.getY() - pointA.getY())
                -  (pointA.getX() - pointB.getX()) * (pointC.getY() - pointA.getY())) / 2;
        
        
    }
    
    public static double calcArea(List<Planet> planets, Sun sun) {
    	
    	Point pointA = planets.get(0).getPoint();
    	Point pointB = planets.get(1).getPoint();
    	Point pointC = sun.getPosition();
		
        return abs((pointA.getX() - pointC.getX()) * (pointB.getY() - pointA.getY())
                -  (pointA.getX() - pointB.getX()) * (pointC.getY() - pointA.getY())) / 2;
        
        
    }
    
    public static Double calcArea(Point pointA, Point pointB, Point pointC) {
    	 
        return abs((pointA.getX() - pointC.getX()) * (pointB.getY() - pointA.getY())
                -  (pointA.getX() - pointB.getX()) * (pointC.getY() - pointA.getY())) / 2;
 
    }

 
    public static boolean compareDouble(Double one, Double two) {
        final Double d1 = one != null ? one : 0.0;
        final Double d2 = two != null ? two : 0.0;

        return abs(d1 - d2) < 0.0001;
    }
    
    public static boolean compareTriangle(List<Planet> planets, Sun sun) {
    	
    	double trianglePlanet = calcArea(planets);
    	
    	Point pointA = planets.get(0).getPoint();
    	Point pointB = planets.get(1).getPoint();
    	Point pointC = planets.get(2).getPoint();

    	double triangleSun = 0;
    	
    	triangleSun += calcArea(pointA, pointB, sun.getPosition());
    	triangleSun += calcArea(pointA, sun.getPosition(), pointC);
    	triangleSun += calcArea(sun.getPosition(), pointB, pointC);        
        
    	return compareDouble(triangleSun, trianglePlanet);

    }
    
    
}
