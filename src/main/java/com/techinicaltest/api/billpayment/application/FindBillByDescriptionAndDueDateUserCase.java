package com.techinicaltest.api.billpayment.application;

import com.techinicaltest.api.billpayment.domain.Bill;
import com.techinicaltest.api.billpayment.infrastructure.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FindBillByDescriptionAndDueDateUserCase {

    @Autowired
    private BillRepository repository;

    public List<Bill> execute(String description, Date dueDate) {

        return repository.findByDescriptionAndDueDate(description, dueDate);
    }
}
