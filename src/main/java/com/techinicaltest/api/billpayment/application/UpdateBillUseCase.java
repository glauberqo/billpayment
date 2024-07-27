package com.techinicaltest.api.billpayment.application;

import com.techinicaltest.api.billpayment.domain.Bill;
import com.techinicaltest.api.billpayment.domain.BillResponseDTO;
import com.techinicaltest.api.billpayment.infrastructure.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateBillUseCase {

    @Autowired
    private BillRepository repository;

    public Bill execute(BillResponseDTO request, UUID id) {

        Optional<Bill> billOptional = repository.findById(id);

        if(!billOptional.isPresent()) {
            throw new IllegalArgumentException("Bill is not exist");
        }
        Bill bill = billOptional.get();

        if(request.amount() != null && !"".equals(request.amount())) {
            bill.setAmount(request.amount());
        }

        if(request.description() != null && !"".equals(request.description())) {
            bill.setDescription(request.description());
        }

        if(request.dueDate() != null && !"".equals(request.dueDate())) {
            bill.setDueDate(request.dueDate());
        }

        if(request.paymentDate() != null && !"".equals(request.paymentDate())) {
            bill.setPaymentDate(request.paymentDate());
        }

        if(request.status() != null && !"".equals(request.status())) {
            bill.setStatus(request.status());
        }

        bill.setUpdatedAt(new Date());

        return repository.save(bill);
    }
}
