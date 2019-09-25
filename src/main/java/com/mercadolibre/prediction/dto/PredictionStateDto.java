package com.mercadolibre.prediction.dto;

import java.io.Serializable;

public class PredictionStateDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long normalDays;
	
	private Long droughtDays;
	
	private Long sunnyDays;
	
	private Long rainyDays;

	public Long getNormalDays() {
		return normalDays;
	}

	public void setNormalDays(Long normalDays) {
		this.normalDays = normalDays;
	}

	public Long getDroughtDays() {
		return droughtDays;
	}

	public void setDroughtDays(Long droughtDays) {
		this.droughtDays = droughtDays;
	}

	public Long getSunnyDays() {
		return sunnyDays;
	}

	public void setSunnyDays(Long sunnyDays) {
		this.sunnyDays = sunnyDays;
	}

	public Long getRainyDays() {
		return rainyDays;
	}

	public void setRainyDays(Long rainyDays) {
		this.rainyDays = rainyDays;
	}

	@Override
	public String toString() {
		return "PredictionStateDto [normalDays=" + normalDays + ", droughtDays=" + droughtDays + ", sunnyDays="
				+ sunnyDays + ", rainyDays=" + rainyDays + "]";
	}
	
	
}
