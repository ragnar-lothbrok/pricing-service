package com.xyz.assignment.jpa.repositories;

import com.xyz.assignment.jpa.entities.CoursePrice;
import com.xyz.assignment.jpa.entities.CoursePrice.CoursePricePK;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePriceRepository extends CrudRepository<CoursePrice, CoursePricePK> {

    @Query("FROM CoursePrice where coursePricePK.id = :courseId AND coursePricePK.localeId = :localeId")
    List<CoursePrice> findByCourseIdAndLocaleId(@Param("courseId") Long courseId, @Param("localeId") String localeId);
}
