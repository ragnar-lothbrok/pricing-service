package com.xyz.assignment.controller;

import com.xyz.assignment.dtos.CoursePricingResponse;
import com.xyz.assignment.dtos.ResponseDto;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/courses")
public class CoursePriceController {

    @GetMapping(value = "/{courseId}/price")
    @ResponseBody
    public ResponseDto<CoursePricingResponse> getPrice() {
        return new ResponseDto(Optional.empty());
    }

}
