package com.techinicaltest.api.billpayment.application;

import com.techinicaltest.api.billpayment.domain.Bill;
import com.techinicaltest.api.billpayment.domain.PaymentStatus;
import com.techinicaltest.api.billpayment.infrastructure.BillRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CsvUseCase {

    @Autowired
    private BillRepository repository;
    public List<Bill> parseCsvFile(MultipartFile file) {
        List<Bill> bills = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Bill bill = new Bill();
                bill.setDescription(csvRecord.get("description"));
                bill.setAmount(new BigDecimal(csvRecord.get("amount")));
                bill.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse(csvRecord.get("dueDate")));
                bill.setStatus(PaymentStatus.OPENED);
                bill.setCreatedAt(new Date());
                bill.setUpdatedAt(new Date());

                bills.add(bill);
            }
        } catch (IOException | ParseException e) {
            // Handle exceptions
            e.printStackTrace();
        }

        return bills;
    }

    public List<Bill> execute(List<Bill> bills) {
        return repository.saveAll(bills);
    }
}
