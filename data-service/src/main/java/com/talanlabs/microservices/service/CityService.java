package com.talanlabs.microservices.service;


import com.talanlabs.microservices.domain.City;
import com.talanlabs.microservices.domain.HotelSummary;
import com.talanlabs.microservices.repository.CitySearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

	Page<City> findCities(CitySearchCriteria criteria, Pageable pageable);

	City getCity(String name, String country);

	Page<HotelSummary> getHotels(City city, Pageable pageable);

}