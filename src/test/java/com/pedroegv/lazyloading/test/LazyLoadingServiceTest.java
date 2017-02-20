package com.pedroegv.lazyloading.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pedroegv.lazyloading.domain.Element;
import com.pedroegv.lazyloading.domain.LazyLoading;
import com.pedroegv.lazyloading.service.LazyLoadingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LazyLoadingServiceTest {

	private static final File TEST_RESOURCES_DIR = new File(
			"src/test/resources/");

	@Autowired
	private LazyLoadingService lazyLoadingService;

	@Test
	public void loadDataTest() {
		List<LazyLoading> workDays = lazyLoadingService.loadData(new File(
				TEST_RESOURCES_DIR, "lazy_loading_example_input.txt"));
		Assert.assertEquals(5, workDays.size());
		Assert.assertEquals(4, workDays.get(0).getElements().size());
		Assert.assertEquals(3, workDays.get(1).getElements().size());
		Assert.assertEquals(11, workDays.get(2).getElements().size());
		Assert.assertEquals(6, workDays.get(3).getElements().size());
		Assert.assertEquals(10, workDays.get(4).getElements().size());
	}

	@Test
	public void maxTravelsTest0() {
		List<Element> elements = new ArrayList<Element>();
		elements.add(new Element(30.0));
		elements.add(new Element(30.0));
		elements.add(new Element(1.0));
		elements.add(new Element(1.0));
		LazyLoading lazyLoading = new LazyLoading(1, elements);
		Assert.assertEquals(2, lazyLoadingService.maxTravels(lazyLoading, 50.0)
				.getTravels().intValue());
	}

	@Test
	public void maxTravelsTest1() {
		List<Element> elements = new ArrayList<Element>();
		elements.add(new Element(20.0));
		elements.add(new Element(20.0));
		elements.add(new Element(20.0));
		LazyLoading lazyLoading = new LazyLoading(2, elements);
		Assert.assertEquals(1, lazyLoadingService.maxTravels(lazyLoading, 50.0)
				.getTravels().intValue());
	}

	@Test
	public void maxTravelsTest2() {
		List<Element> elements = new ArrayList<Element>();
		elements.add(new Element(1.0));
		elements.add(new Element(2.0));
		elements.add(new Element(3.0));
		elements.add(new Element(4.0));
		elements.add(new Element(5.0));
		elements.add(new Element(6.0));
		elements.add(new Element(7.0));
		elements.add(new Element(8.0));
		elements.add(new Element(9.0));
		elements.add(new Element(10.0));
		elements.add(new Element(11.0));
		LazyLoading lazyLoading = new LazyLoading(3, elements);
		Assert.assertEquals(2, lazyLoadingService.maxTravels(lazyLoading, 50.0)
				.getTravels().intValue());
	}

	@Test
	public void maxTravelsTest3() {
		List<Element> elements = new ArrayList<Element>();
		elements.add(new Element(9.0));
		elements.add(new Element(19.0));
		elements.add(new Element(29.0));
		elements.add(new Element(39.0));
		elements.add(new Element(49.0));
		elements.add(new Element(59.0));
		LazyLoading lazyLoading = new LazyLoading(4, elements);
		Assert.assertEquals(3, lazyLoadingService.maxTravels(lazyLoading, 50.0)
				.getTravels().intValue());
	}

	@Test
	public void maxTravelsTest4() {
		List<Element> elements = new ArrayList<Element>();
		elements.add(new Element(32.0));
		elements.add(new Element(56.0));
		elements.add(new Element(76.0));
		elements.add(new Element(8.0));
		elements.add(new Element(44.0));
		elements.add(new Element(60.0));
		elements.add(new Element(47.0));
		elements.add(new Element(85.0));
		elements.add(new Element(71.0));
		elements.add(new Element(91.0));
		LazyLoading lazyLoading = new LazyLoading(5, elements);
		Assert.assertEquals(8, lazyLoadingService.maxTravels(lazyLoading, 50.0)
				.getTravels().intValue());
	}
}
