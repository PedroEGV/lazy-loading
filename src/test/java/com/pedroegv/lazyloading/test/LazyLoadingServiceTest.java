package com.pedroegv.lazyloading.test;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.pedroegv.lazyloading.domain.WorkDay;
import com.pedroegv.lazyloading.service.LazyLoadingService;

public class LazyLoadingServiceTest {

	private static final File TEST_RESOURCES_DIR = new File(
			"src/test/resources/");

	@Test
	public void loadDataTest() {
		List<WorkDay> workDays = new LazyLoadingService().loadData(new File(
				TEST_RESOURCES_DIR, "lazy_loading_example_input.txt"));
		Assert.assertEquals(5, workDays.size());
		Assert.assertEquals(4, workDays.get(0).getWeights().length);
		Assert.assertEquals(3, workDays.get(1).getWeights().length);
		Assert.assertEquals(11, workDays.get(2).getWeights().length);
		Assert.assertEquals(6, workDays.get(3).getWeights().length);
		Assert.assertEquals(10, workDays.get(4).getWeights().length);
	}

	@Test
	public void maxTravelsTest() {
		LazyLoadingService lazyLoadingService = new LazyLoadingService();
		Assert.assertEquals(
				2,
				lazyLoadingService.maxTravels(new WorkDay(1, new Double[] {
						30.0, 30.0, 1.0, 1.0 }), 50.0));
		Assert.assertEquals(
				1,
				lazyLoadingService.maxTravels(new WorkDay(2, new Double[] {
						20.0, 20.0, 20.0 }), 50.0));
		Assert.assertEquals(2, lazyLoadingService.maxTravels(new WorkDay(3,
				new Double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0,
						10.0, 11.0 }), 50.0));
		Assert.assertEquals(
				3,
				lazyLoadingService.maxTravels(new WorkDay(4, new Double[] {
						9.0, 19.0, 29.0, 39.0, 49.0, 59.0 }), 50.0));
		Assert.assertEquals(
				8,
				lazyLoadingService.maxTravels(new WorkDay(5, new Double[] {
						32.0, 56.0, 76.0, 8.0, 44.0, 60.0, 47.0, 85.0, 71.0,
						91.0 }), 50.0));
	}
}
