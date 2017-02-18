package com.pedroegv.lazyloading.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pedroegv.lazyloading.service.LazyLoadingService;

@Controller
public class LazyLoadingController {

	@Autowired
	private LazyLoadingService lazyLoading;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index.html";
	}

	@RequestMapping(value = "/processData", method = RequestMethod.POST)
	public String processData(@RequestParam("minWeight") Double minWeight,
			@RequestParam("inputFile") MultipartFile inputFile) {
		System.out.println("minWeight: " + minWeight);
		System.out.println("inputFile: " + inputFile.getOriginalFilename());
		return "index.html";
	}
}
