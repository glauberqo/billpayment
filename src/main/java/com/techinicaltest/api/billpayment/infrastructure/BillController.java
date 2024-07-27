package com.techinicaltest.api.billpayment.infrastructure;

import com.techinicaltest.api.billpayment.application.*;
import com.techinicaltest.api.billpayment.domain.Bill;
import com.techinicaltest.api.billpayment.domain.BillRequestDTO;
import com.techinicaltest.api.billpayment.domain.BillResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/billpayment/bill")
public class BillController {
    @Autowired
    private AddBillUseCase useCase;

    @Autowired
    private FindByIdBillUseCase findByIdBillUseCase;

    @Autowired
    private UpdateBillUseCase updateBillUseCase;
    @Autowired
    private FindBillByDescriptionAndDueDateUserCase findBillByDescriptionAndDueDateUserCase;

    @Autowired
    private CsvUseCase csvUseCase;

    @PostMapping
    public ResponseEntity<Bill> addBill(@RequestBody BillRequestDTO body) {
        Bill newBill = this.useCase.execute(body);

        return ResponseEntity.ok(newBill);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<BillResponseDTO> findByIdBill(@PathVariable(value = "id") String id) {
        Bill bill = this.findByIdBillUseCase.execute(UUID.fromString(id));

        BillResponseDTO response = new BillResponseDTO(
                bill.getId(),
                bill.getDescription(),
                bill.getAmount(),
                bill.getDueDate(),
                bill.getPaymentDate(),
                bill.getStatus(),
                bill.getCreatedAt(),
                bill.getUpdatedAt()
        );
        return ResponseEntity.ok(response);

    }
    @PatchMapping("/{id}")
    public void updateBill(@PathVariable(value = "id") UUID id, @RequestBody @Validated BillResponseDTO response) {
        updateBillUseCase.execute(response, id);
    }

    @GetMapping
    public ResponseEntity<List<Bill>> findByDescritionAndDueDate(@RequestParam(required = true) String description,
                                                                 @RequestParam("dueDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dueDate) {

        List<Bill> billList = findBillByDescriptionAndDueDateUserCase.execute(description, dueDate);

        return ResponseEntity.ok(billList);
    }

    @PostMapping("/upload")
    public ResponseEntity<List<Bill>> uploadCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<Bill> bills = csvUseCase.parseCsvFile(file);
        csvUseCase.execute(bills);
        return ResponseEntity.ok(bills);
    }
}
