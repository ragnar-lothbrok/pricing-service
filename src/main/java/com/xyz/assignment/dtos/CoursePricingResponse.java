package com.xyz.assignment.dtos;

public class CoursePricingResponse {

    private Long courseId;
    private PricingResponse pricingResponse;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public PricingResponse getPricingResponse() {
        return pricingResponse;
    }

    public void setPricingResponse(PricingResponse pricingResponse) {
        this.pricingResponse = pricingResponse;
    }
}
