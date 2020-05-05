package com.nagarro.controller;

import java.util.List;

import com.nagarro.model.City;
import com.nagarro.model.Country;
import com.nagarro.model.State;
import com.nagarro.repository.CityRepository;
import com.nagarro.repository.CountryRepository;
import com.nagarro.repository.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/region")
public class RegionsController {

	@Autowired
	private CountryRepository country;

	@Autowired
	private StateRepository state;

	@Autowired
	private CityRepository city;

	@GetMapping("/getCountryList")
	public List<Country> getCountryList() {
		return country.findAll();
	}

	@GetMapping("/getStateList/{id}")
	public List<State> getStateList(@PathVariable("id") Integer id) {
		return state.findByCountryId(id);
	}

	@GetMapping("/getCityList/{id}")
	public List<City> getCityList(@PathVariable("id") Integer id) {
		return city.findByStateId(id);
	}

}
