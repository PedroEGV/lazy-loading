package com.pedroegv.lazyloading.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class LazyLoading {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String customerId;
	@Column
	private Integer day;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Element> elements;
	@Column
	private Integer travels;

	public LazyLoading() {

	}

	public LazyLoading(Integer day, List<Element> elements) {
		super();
		this.day = day;
		this.elements = elements;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public Integer getTravels() {
		return travels;
	}

	public void setTravels(Integer travels) {
		this.travels = travels;
	}

}
