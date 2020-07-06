package com.xyz.assignment.apis;

import com.xyz.assignment.dtos.PricingResponse;
import java.util.List;

/**
 * This returns Pricing for any given Course.
 */
public interface CoursePricingApi {

    List<PricingResponse> getCoursePrice(Long courseId, String currency, Boolean inline);

}
