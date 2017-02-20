package com.pedroegv.lazyloading.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Element {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private LazyLoading lazyLoadingId;
	@Column
	private Double weight;

	public Element() {

	}

	public Element(Double weight) {
		super();
		this.weight = weight;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getWeight() {
		return weight;
	}

	public LazyLoading getLazyLoadingId() {
		return lazyLoadingId;
	}

	public void setLazyLoadingId(LazyLoading lazyLoadingId) {
		this.lazyLoadingId = lazyLoadingId;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

}
