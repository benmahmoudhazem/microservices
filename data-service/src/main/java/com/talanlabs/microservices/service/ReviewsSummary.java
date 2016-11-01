package com.talanlabs.microservices.service;

import com.talanlabs.microservices.domain.Rating;

public interface ReviewsSummary {

	long getNumberOfReviewsWithRating(Rating rating);

}