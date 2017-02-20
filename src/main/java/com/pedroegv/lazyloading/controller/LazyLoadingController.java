package com.pedroegv.lazyloading.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pedroegv.lazyloading.domain.LazyLoading;
import com.pedroegv.lazyloading.domain.LazyLoadingDTO;
import com.pedroegv.lazyloading.repository.LazyLoadingRepository;
import com.pedroegv.lazyloading.service.LazyLoadingService;

@Controller
public class LazyLoadingController {

	@Autowired
	private LazyLoadingService lazyLoadingService;
	
	@Autowired
	private LazyLoadingRepository lazyLoadingRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index.html";
	}

	@RequestMapping(value = "/fileContent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<LazyLoading> getFileContent(
			@RequestParam MultipartFile inputFile) {
		File file = new File(inputFile.getOriginalFilename());
		List<LazyLoading> workDays = new ArrayList<LazyLoading>();
		try {
			String content = new String(inputFile.getBytes(),
					StandardCharsets.UTF_8);
			file.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(content);
			bw.close();
			workDays = lazyLoadingService.loadData(file);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (file.exists()) {
				file.delete();
			}
		}
		return workDays;
	}

	@RequestMapping(value = "/processData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Iterable<LazyLoading> processData(
			@RequestBody LazyLoadingDTO dto) {
		Double minWeight = dto.getMinWeight();
		List<LazyLoading> workDays = dto.getWorkDays();
		for (LazyLoading lazyLoading : workDays) {
			lazyLoadingService.maxTravels(lazyLoading, minWeight);
		}
		return lazyLoadingRepository.findAll();
	}
}
