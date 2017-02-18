package com.pedroegv.lazyloading.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.pedroegv.lazyloading.domain.WorkDay;

@Service
public class LazyLoadingService {

	public int maxTravels(WorkDay workDay, Double minWeight) {
		Double[] weights = workDay.getWeights();
		LinkedList<Double> sortedWeights = new LinkedList<Double>(
				Arrays.asList(weights));
		Collections.sort(sortedWeights);
		int travels = 0;
		while (!sortedWeights.isEmpty()) {
			Double topElement = sortedWeights.getLast();
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
		return travels;
	}

	public void printResult(int day, int travels) {
		System.out.println("Case #" + day + ": " + travels);
	}

	public List<WorkDay> loadData(File file) {
		List<WorkDay> workDays = new ArrayList<WorkDay>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			int days = scanner.nextInt();
			for (int day = 0; day < days; day++) {
				int elements = scanner.nextInt();
				Double[] weights = new Double[elements];
				for (int element = 0; element < elements; element++) {
					weights[element] = scanner.nextDouble();
				}
				workDays.add(new WorkDay(day + 1, weights));
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
