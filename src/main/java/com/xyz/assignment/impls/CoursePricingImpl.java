package com.xyz.assignment.impls;

import com.xyz.assignment.apis.CoursePricingApi;
import com.xyz.assignment.constants.ErrorConstants;
import com.xyz.assignment.constants.ServiceConstants;
import com.xyz.assignment.dtos.PricingResponse;
import com.xyz.assignment.dtos.Tax;
import com.xyz.assignment.enums.CurrencyEnum;
import com.xyz.assignment.exceptions.ServiceException;
import com.xyz.assignment.jpa.entities.CoursePrice;
import com.xyz.assignment.jpa.entities.TaxDetails;
import com.xyz.assignment.jpa.repositories.CoursePriceRepository;
import com.xyz.assignment.jpa.repositories.TaxDetailsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class CoursePricingImpl implements CoursePricingApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoursePricingImpl.class);

    @Autowired
    private CoursePriceRepository coursePriceRepository;

    @Autowired
    private TaxDetailsRepository taxDetailsRepository;

    @Override
    public List<PricingResponse> getCoursePrice(Long courseId, String localeId) {
        LOGGER.info("Calculating Course Price for Course = {} and Locale = {} ", courseId, localeId);
        List<CoursePrice> coursePrices = coursePriceRepository.findByCourseIdAndLocaleId(courseId, localeId);
        if(CollectionUtils.isEmpty(coursePrices)) {
            LOGGER.info("Course Price not found.");
            throw new ServiceException(ErrorConstants.COURSE_PRICING_NOT_FOUND, "Course Pricing not found", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        List<TaxDetails> taxDetails = taxDetailsRepository.findByLocaleId(localeId);

        return coursePrices.stream().map(coursePrice -> {
            PricingResponse pricingResponse = new PricingResponse();
            pricingResponse.setCurrency(CurrencyEnum.valueOf(localeId));
            pricingResponse.setSalePrice(roundingFloat(coursePrice.getSalePrice()));
            pricingResponse.setPrice(roundingFloat(coursePrice.getPrice()));
            if(CollectionUtils.isEmpty(taxDetails)) {
                LOGGER.info("Tax Details are empty for locale id = {} ", localeId);
            } else {
                pricingResponse.setTaxes(calculateTax(taxDetails, coursePrice));
                pricingResponse.setTotalTax(roundingFloat(pricingResponse.getTaxes().stream().map(tax -> tax.getTaxAmount()).reduce(0.0f, Float::sum)));
                pricingResponse.setTotalPrice(roundingFloat(coursePrice.getSalePrice() + pricingResponse.getTotalTax()));
            }
            pricingResponse.setSubscriptionId(coursePrice.getCoursePricePK().getSubscriptionType().getId());
            pricingResponse.setSubscriptionName(coursePrice.getCoursePricePK().getSubscriptionType().getTimeUnitValue() + " " + coursePrice.getCoursePricePK().getSubscriptionType().getTimeUnit());
            return pricingResponse;
        }).collect(Collectors.toList());
    }

    private List<Tax> calculateTax(List<TaxDetails> taxDetails, CoursePrice coursePrice) {
        return taxDetails.stream().map(taxDetail -> {
            Tax tax = new Tax();
            tax.setLabel(taxDetail.getLabel());
            tax.setPercentage(taxDetail.getPercentage());
            tax.setFixedTaxAmount(taxDetail.getFixedTaxAmount());
            tax.setTaxAmount(taxDetail.getFixedTaxAmount() + (coursePrice.getSalePrice() * .01f * taxDetail.getPercentage()));
            return tax;
        }).collect(Collectors.toList());
    }

    private Float roundingFloat(Float value) {
        return Float.valueOf(ServiceConstants.DECIMAL_FORMAT.format(value));
    }
}
