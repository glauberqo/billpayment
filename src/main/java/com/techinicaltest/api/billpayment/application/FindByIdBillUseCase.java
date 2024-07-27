package com.techinicaltest.api.billpayment.application;

import com.techinicaltest.api.billpayment.domain.Bill;
import com.techinicaltest.api.billpayment.infrastructure.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindByIdBillUseCase {

    @Autowired
    private BillRepository repository;

    public Bill execute(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bill not found"));

    }
}
