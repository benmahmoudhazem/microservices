package com.talanlabs.microservices.repository;

import java.util.List;

import com.talanlabs.microservices.domain.City;
import com.talanlabs.microservices.domain.Hotel;
import com.talanlabs.microservices.domain.HotelSummary;
import com.talanlabs.microservices.domain.Rating;
import com.talanlabs.microservices.domain.RatingCount;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link HotelRepository}.
 *
 * @author Oliver Gierke
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelRepositoryIntegrationTest {

	@Autowired
	CityRepository cityRepository;
	@Autowired
	HotelRepository repository;

	@Test
	public void executesQueryMethodsCorrectly() {
		City city = this.cityRepository
				.findAll(new PageRequest(0, 1, Direction.ASC, "name")).getContent()
				.get(0);
		assertThat(city.getName()).isEqualTo("Atlanta");

		Page<HotelSummary> hotels = this.repository.findByCity(city,
				new PageRequest(0, 10, Direction.ASC, "name"));
		Hotel hotel = this.repository.findByCityAndName(city,
				hotels.getContent().get(0).getName());
		assertThat(hotel.getName()).isEqualTo("Doubletree");

		List<RatingCount> counts = this.repository.findRatingCounts(hotel);
		assertThat(counts).hasSize(1);
		assertThat(counts.get(0).getRating()).isEqualTo(Rating.AVERAGE);
		assertThat(counts.get(0).getCount()).isGreaterThan(1L);
	}
}