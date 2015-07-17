package com.wisebirds.sap.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wisebirds.sap.Application;
import com.wisebirds.sap.domain.stats.AdInsights;

@Controller
public class InsightController {
	@RequestMapping(value = "/insight", method = RequestMethod.GET)
	public ModelAndView insight() throws URISyntaxException {
		try {
			Path wiki_path = Paths.get(RestInsightController.class.getClassLoader().getResource("dummy/ad_insights.json").toURI());
			Charset charset = Charset.forName("UTF-8");
			StringBuilder sb = new StringBuilder();
			Files.readAllLines(wiki_path, charset).forEach(sb::append);
			AdInsights adInsights = Application.GSON.fromJson(sb.toString(), AdInsights.class);
			return new ModelAndView("ad/insight/data_table", "adInsights", adInsights.getData());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
