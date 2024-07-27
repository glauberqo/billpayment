package com.techinicaltest.api.billpayment.infrastructure;

import com.techinicaltest.api.billpayment.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BillRepository extends JpaRepository<Bill, UUID> {

    @Query("SELECT b FROM Bill b WHERE b.description LIKE %:description% AND b.dueDate = :dueDate")
    public List<Bill> findByDescriptionAndDueDate(@Param("description") String description, @Param("dueDate") Date dueDate);
}
