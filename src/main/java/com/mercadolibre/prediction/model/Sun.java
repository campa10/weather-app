package com.mercadolibre.prediction.model;

import org.springframework.stereotype.Component;

@Component
public class Sun {
	
	private Point position;

	public Sun() {
		 this.position = new Point(0.0, 0.0);
	}
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point sunPosition) {
		this.position = sunPosition;
	}
	
}
