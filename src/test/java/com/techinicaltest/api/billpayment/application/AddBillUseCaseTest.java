package com.techinicaltest.api.billpayment.application;

import com.techinicaltest.api.billpayment.domain.Bill;
import com.techinicaltest.api.billpayment.domain.BillRequestDTO;
import com.techinicaltest.api.billpayment.domain.PaymentStatus;
import com.techinicaltest.api.billpayment.infrastructure.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddBillUseCaseTest {

    @Mock
    private BillRepository repository;

    @InjectMocks
    private AddBillUseCase addBillUseCase;

    private Bill billOne;

    private BillRequestDTO billRequestDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        billOne = new Bill(
                "light",
                new BigDecimal(720.50),
                new Date(),
                new Date(),
                PaymentStatus.OPENED,
                new Date(),
                new Date()
        );

        billRequestDTO = new BillRequestDTO(
                billOne.getDescription(),
                billOne.getAmount(),
                billOne.getDueDate(),
                billOne.getPaymentDate(),
                billOne.getStatus()
        );
    }

    @Test
    public void testAddBill() {
        when(repository.save(any())).thenReturn(billOne);

        Bill result = addBillUseCase.execute(billRequestDTO);
        assertNotNull(result);
        verify(repository, times(1)).save(any());

    }
}
