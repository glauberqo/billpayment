package com.techinicaltest.api.billpayment.application;

import com.techinicaltest.api.billpayment.domain.Bill;
import com.techinicaltest.api.billpayment.domain.BillRequestDTO;
import com.techinicaltest.api.billpayment.infrastructure.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddBillUseCase {

    @Autowired
    BillRepository repository;

    public Bill execute(BillRequestDTO data) {

        Bill newBill = new Bill(
                data.description(),
                data.amount(),
                data.dueDate(),
                data.paymentDate(),
                data.status(),
                new Date(),
                new Date()
        );

        return repository.save(newBill);
    }
}
