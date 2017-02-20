package com.pedroegv.lazyloading.domain;

import java.util.List;

public class LazyLoadingDTO {

	private Double minWeight;
	private List<WorkDay> workDays;

	public LazyLoadingDTO() {

	}

	public LazyLoadingDTO(Double minWeight, List<WorkDay> workDays) {
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

	public List<WorkDay> getWorkDays() {
		return workDays;
	}

	public void setWorkDays(List<WorkDay> workDays) {
		this.workDays = workDays;
	}

}
