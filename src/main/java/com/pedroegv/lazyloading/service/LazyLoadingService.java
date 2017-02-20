package com.pedroegv.lazyloading.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedroegv.lazyloading.domain.Element;
import com.pedroegv.lazyloading.domain.LazyLoading;
import com.pedroegv.lazyloading.repository.LazyLoadingRepository;

@Service
public class LazyLoadingService {

	@Autowired
	private LazyLoadingRepository lazyLoadingRepository;

	public LazyLoading maxTravels(LazyLoading lazyLoading, Double minWeight) {
		LinkedList<Element> sortedWeights = new LinkedList<Element>(
				lazyLoading.getElements());
		Collections.sort(sortedWeights, new Comparator<Element>() {
			@Override
			public int compare(Element e1, Element e2) {
				return e1.getWeight() - e2.getWeight() < 0 ? -1 : 1;
			}
		});
		int travels = 0;
		while (!sortedWeights.isEmpty()) {
			Double topElement = sortedWeights.getLast().getWeight();
			sortedWeights.removeLast();
			int inTheBag = 1;
			while (inTheBag * topElement < minWeight
					&& !sortedWeights.isEmpty()) {
				inTheBag++;
				sortedWeights.removeFirst();
			}
			if (inTheBag * topElement >= minWeight) {
				travels++;
			}
		}
		lazyLoading.setTravels(travels);
		lazyLoadingRepository.save(lazyLoading);
		return lazyLoading;
	}

	public List<LazyLoading> loadData(File file) {
		List<LazyLoading> workDays = new ArrayList<LazyLoading>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			int days = scanner.nextInt();
			for (int day = 0; day < days; day++) {
				LazyLoading lazyLoading = new LazyLoading();
				lazyLoading.setDay(day + 1);
				int weights = scanner.nextInt();
				List<Element> elements = new ArrayList<Element>();
				for (int i = 0; i < weights; i++) {
					Element element = new Element();
					element.setWeight(scanner.nextDouble());
					elements.add(element);
				}
				lazyLoading.setElements(elements);
				workDays.add(lazyLoading);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		return workDays;
	}
}
