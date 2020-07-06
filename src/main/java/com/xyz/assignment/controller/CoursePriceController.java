package com.xyz.assignment.controller;

import com.xyz.assignment.apis.CoursePricingApi;
import com.xyz.assignment.dtos.CoursePricingResponse;
import com.xyz.assignment.dtos.ResponseDto;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/lms/v1/price")
public class CoursePriceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoursePriceController.class);

    @Autowired
    private CoursePricingApi coursePricingApi;

    /**
     * Return Pricing of a given Course.
     * @param courseId
     * @param currency
     * @param inline
     * @param request
     * @return ResponseDto<CoursePricingResponse>
     */
    @GetMapping(value = "/course/{courseId}")
    @ResponseBody
    public ResponseDto<CoursePricingResponse> getPrice(@PathVariable("courseId") Long courseId, @RequestHeader(value = "currency" , required =  true) String currency,  @RequestParam(value= "inline", required = false) Boolean inline, HttpServletRequest request) {
        LOGGER.info("Request received for Course ={} with Currency = {}", courseId, currency);
        return new ResponseDto(coursePricingApi.getCoursePrice(courseId, currency, inline));
    }

}
