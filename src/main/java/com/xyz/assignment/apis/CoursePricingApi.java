package com.xyz.assignment.apis;

import com.xyz.assignment.dtos.PricingResponse;
import java.util.List;

public interface CoursePricingApi {

    List<PricingResponse> getCoursePrice(Long courseId, String localeId, Boolean inline);

}
