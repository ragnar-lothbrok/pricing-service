package com.xyz.assignment.jpa.repositories;

import com.xyz.assignment.jpa.entities.TaxDetails;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxDetailsRepository extends CrudRepository<TaxDetails, Long> {

    @Query("FROM TaxDetails where localeId = :localeId and active = true")
    List<TaxDetails> findByLocaleId(@Param("localeId") String localeId);
}