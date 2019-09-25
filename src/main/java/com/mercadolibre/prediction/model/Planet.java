package com.mercadolibre.prediction.model;

import static java.lang.Math.cos;

import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class Planet {
	
	private double speed;	
	 
	private double position;
	 
	private double distance;
	
	private Point point;

	public Planet () {
		  Double xPos = cos(toRadians(this.position)) * this.distance;
		  Double yPos = sin(toRadians(this.position)) * this.distance;
	      this.setPoint(new Point(xPos, yPos));
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getPosition() {
		return position;
	}

	public void setPosition(double position) {
		this.position = position;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
    public Point getPoint() {
    	Double xPos = cos(toRadians(this.position)) * this.distance;
        Double yPos = sin(toRadians(this.position)) * this.distance;
    	this.point.setX(xPos);
    	this.point.setY(yPos);
        return this.point;
    }
    
	public void setPoint(Point point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Planet [speed=" + speed + ", position=" + position + ", distance=" + distance + "]";
	}
    
}
