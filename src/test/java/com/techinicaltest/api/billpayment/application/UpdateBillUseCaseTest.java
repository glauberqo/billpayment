package com.techinicaltest.api.billpayment.application;

import com.techinicaltest.api.billpayment.domain.Bill;
import com.techinicaltest.api.billpayment.domain.BillResponseDTO;
import com.techinicaltest.api.billpayment.domain.PaymentStatus;
import com.techinicaltest.api.billpayment.infrastructure.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UpdateBillUseCaseTest {

    @Mock
    private BillRepository repository;

    @InjectMocks
    private UpdateBillUseCase updateBillUseCase;

    private Bill existingBill;

    private Bill updatedBill;

    private BillResponseDTO billResponseDTO;

    private UUID id = new UUID(1, 1);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        existingBill = new Bill(
                id,
                "light",
                new BigDecimal(720.50),
                new Date(),
                new Date(),
                PaymentStatus.OPENED,
                new Date(),
                new Date()
        );

        updatedBill = new Bill(
                id,
                "light",
                new BigDecimal(720.50),
                new Date(),
                new Date(),
                PaymentStatus.PAYED,
                new Date(),
                new Date()
        );

        billResponseDTO = new BillResponseDTO(
                updatedBill.getId(),
                updatedBill.getDescription(),
                updatedBill.getAmount(),
                updatedBill.getDueDate(),
                updatedBill.getPaymentDate(),
                updatedBill.getStatus(),
                updatedBill.getCreatedAt(),
                updatedBill.getUpdatedAt()
        );
    }

    @Test
    public void testUpdateBillSuccess() {
        when(repository.findById(id)).thenReturn(Optional.of(existingBill));
        when(repository.save(any(Bill.class))).thenReturn(existingBill);

        Bill result = updateBillUseCase.execute(billResponseDTO, id);
        assertNotNull(result);
        assertEquals(PaymentStatus.PAYED, result.getStatus());
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(Bill.class));
    }
}

