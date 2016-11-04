package com.talanlabs.microservices.web;

import com.talanlabs.microservices.domain.City;
import com.talanlabs.microservices.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;


@Controller
public class SampleController {

	@Autowired
	private CityService cityService;

	@GetMapping("/hello-db")
	@ResponseBody
	@Transactional(readOnly = true)
	public String helloWorld() {
		final City city = cityService.getCity("Bath", "UK");
		return city== null ? "unknown" : city.getName();
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String helloWorld(Principal principal) {
		return principal == null ? "Hello anonymous" : "Hello " + principal.getName();
	}

}