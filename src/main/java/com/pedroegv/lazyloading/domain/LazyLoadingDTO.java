package com.pedroegv.lazyloading.domain;

import java.util.List;

public class LazyLoadingDTO {

	private Double minWeight;
	private List<LazyLoading> workDays;

	public LazyLoadingDTO() {

	}

	public LazyLoadingDTO(Double minWeight, List<LazyLoading> workDays) {
		super();
		this.minWeight = minWeight;
		this.workDays = workDays;
	}

	public Double getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(Double minWeight) {
		this.minWeight = minWeight;
	}

	public List<LazyLoading> getWorkDays() {
		return workDays;
	}

	public void setWorkDays(List<LazyLoading> workDays) {
		this.workDays = workDays;
	}

}
