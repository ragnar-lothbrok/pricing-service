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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public List<PricingResponse> getCoursePrice(Long courseId, String currency, Boolean inline) {
        LOGGER.info("Calculating Course Price for Course = {} and Currency = {} ", courseId, currency);
        try {
            List<CoursePrice> coursePrices = coursePriceRepository.findByCourseIdAndCurrency(courseId, currency);
            if(CollectionUtils.isEmpty(coursePrices)) {
                LOGGER.info("Course Price not found.");
                throw new ServiceException(ErrorConstants.COURSE_PRICING_NOT_FOUND, "Course Pricing not found", HttpStatus.NOT_FOUND.value());
            }

            List<TaxDetails> taxDetails = null;
            if(Objects.nonNull(inline) && inline) {
                taxDetails = taxDetailsRepository.findByCurrency(currency);
            }

            List<TaxDetails> finalTaxDetails = taxDetails;
            return coursePrices.stream().map(coursePrice -> {
                PricingResponse pricingResponse = constructBasicPriceResponse(currency, coursePrice);
                if(!CollectionUtils.isEmpty(finalTaxDetails)) {
                    if(pricingResponse.getSalePrice().floatValue() != 0) {
                        pricingResponse.setTaxes(calculateTax(finalTaxDetails, coursePrice));
                        pricingResponse.setTotalTax(roundingFloat(pricingResponse.getTaxes().stream().map(tax -> tax.getTaxAmount()).reduce(0.0f, Float::sum)));
                        pricingResponse.setTotalPrice(roundingFloat(coursePrice.getSalePrice() + pricingResponse.getTotalTax()));
                    } else {
                        pricingResponse.setTotalTax(0.00f);
                        pricingResponse.setTotalPrice(0.00f);
                        LOGGER.info("Skipping tax because sale price is zero.");
                    }
                }
                return pricingResponse;
            }).collect(Collectors.toList());
        } catch(DataAccessException dataAccessException) {
            LOGGER.error("Exception occurred while fetching data from database = {} ", dataAccessException);
            throw new ServiceException(ErrorConstants.DATABASE_ACCESS_ERROR, "Not able to fetch Pricing details from database. Please try after some time.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        } catch(Exception exception) {
            LOGGER.error("Exception occurred while fetching pricing data = {} ", exception);
            throw new ServiceException(ErrorConstants.DEFAULT_EXCEPTION, "Not able to fetch Pricing Details. Please try after some time.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    /**
     * Returns Basic Pricinig Response
     * @param currency
     * @param coursePrice
     * @return PricingResponse
     */
    private PricingResponse constructBasicPriceResponse(String currency, CoursePrice coursePrice) {
        PricingResponse pricingResponse = new PricingResponse();
        pricingResponse.setCurrency(CurrencyEnum.valueOf(currency));
        pricingResponse.setSalePrice(roundingFloat(coursePrice.getSalePrice()));
        pricingResponse.setPrice(roundingFloat(coursePrice.getPrice()));
        pricingResponse.setSubscriptionId(coursePrice.getCoursePricePK().getSubscriptionType().getId());
        pricingResponse.setSubscriptionName(coursePrice.getCoursePricePK().getSubscriptionType().getTimeUnitValue() + " " + coursePrice.getCoursePricePK().getSubscriptionType().getTimeUnit());
        return pricingResponse;
    }


    /**
     * Returns Taxes calculation as per Currency.
     * @param taxDetails
     * @param coursePrice
     * @return
     */
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

    /**
     * Convert Float value to decimal 2
     * @param value
     * @return
     */
    private Float roundingFloat(Float value) {
        return Float.valueOf(ServiceConstants.DECIMAL_FORMAT.format(value));
    }
}
