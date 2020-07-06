package com.xyz.assignment.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "course_price")
public class CoursePrice {

    @EmbeddedId
    private CoursePricePK coursePricePK;

    @Column(name = "sale_price", columnDefinition = "NUMERIC(10, 2)", nullable = false)
    private Float salePrice;

    @Column(name = "price", columnDefinition = "NUMERIC(10, 2)", nullable = false)
    private Float price;

    @Column(name="created_at", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Embeddable
    public static class CoursePricePK implements Serializable {

        @Column(name = "course_id", nullable = false)
        private Long id;

        @Column(name = "locale_id", columnDefinition = "varchar(6)", nullable = false)
        private String localeId;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "subscription_id", nullable = false)
        private SubscriptionType subscriptionType;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getLocaleId() {
            return localeId;
        }

        public void setLocaleId(String localeId) {
            this.localeId = localeId;
        }

        public SubscriptionType getSubscriptionType() {
            return subscriptionType;
        }

        public void setSubscriptionType(SubscriptionType subscriptionType) {
            this.subscriptionType = subscriptionType;
        }
    }

    public CoursePricePK getCoursePricePK() {
        return coursePricePK;
    }

    public void setCoursePricePK(CoursePricePK coursePricePK) {
        this.coursePricePK = coursePricePK;
    }

    public Float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
