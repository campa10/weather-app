package com.mercadolibre.prediction.model;

public class Point {
	
	private double x;
	
	private double y;
	
	public Point() {
		super();
	}
	
	public Point(double x, double y) {
		
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void setX(double xPos) {
		this.x = xPos;
	}

	public void setY(double yPos) {
		this.y = yPos;
	}
	
}
