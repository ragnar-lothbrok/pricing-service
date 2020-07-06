package com.xyz.assignment.jpa.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tax_details")
public class TaxDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @Column(name = "currency", columnDefinition = "varchar(6)", nullable = false)
    private String currency;

    @Column(name = "label", columnDefinition = "varchar(20)", nullable = false)
    private String label;

    @Column(name = "percentage", columnDefinition = "NUMERIC(5, 2)", nullable = false)
    private Float percentage;

    @Column(name = "fixed_tax_amount", columnDefinition = "NUMERIC(5, 2)", nullable = false)
    private Float fixedTaxAmount;

    @Column(name = "active", columnDefinition = "BOOLEAN", nullable = false)
    private Boolean active;

    @Column(name="created_at", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public Float getFixedTaxAmount() {
        return fixedTaxAmount;
    }

    public void setFixedTaxAmount(Float fixedTaxAmount) {
        this.fixedTaxAmount = fixedTaxAmount;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
}
