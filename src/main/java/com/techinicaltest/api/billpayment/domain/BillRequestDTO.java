package com.techinicaltest.api.billpayment.domain;

import java.math.BigDecimal;
import java.util.Date;

public record BillRequestDTO(
        String description,
        BigDecimal amount,
        Date dueDate,
        Date paymentDate,
        PaymentStatus status
) {
}
