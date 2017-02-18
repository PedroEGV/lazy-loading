package com.pedroegv.lazyloading.domain;

public class WorkDay {

	private Integer day;
	private Double[] weights;

	public WorkDay(Integer day, Double[] weights) {
		super();
		this.day = day;
		this.weights = weights;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Double[] getWeights() {
		return weights;
	}

	public void setWeights(Double[] weights) {
		this.weights = weights;
	}

}
