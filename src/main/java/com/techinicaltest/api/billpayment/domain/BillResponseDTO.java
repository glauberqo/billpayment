package com.techinicaltest.api.billpayment.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public record BillResponseDTO(
        UUID id,
        String description,
        BigDecimal amount,
        Date dueDate,
        Date paymentDate,
        PaymentStatus status,
        Date createdAt,
        Date updatedAt
) {
}
