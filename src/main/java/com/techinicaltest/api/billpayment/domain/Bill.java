package com.techinicaltest.api.billpayment.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "bill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue
    private UUID id;

    private String description;

    private BigDecimal amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paymentDate;

    private PaymentStatus status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    public Bill(String description, BigDecimal amount, Date dueDate, Date paymentDate, PaymentStatus status, Date createdAt, Date updatedAt) {
        Assert.notNull(description, "description must not be null");
        Assert.notNull(amount, "amount must not be null");
        Assert.notNull(dueDate, "dueDate must not be null");
        this.description = description;
        this.amount = amount;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
