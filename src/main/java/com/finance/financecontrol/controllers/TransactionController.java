package com.finance.financecontrol.controllers;

import com.finance.financecontrol.dtos.requests.TransactionDtoRequest;
import com.finance.financecontrol.dtos.responses.TransactionDtoResponse;
import com.finance.financecontrol.exceptions.http.TransactionNotFoundException;
import com.finance.financecontrol.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<TransactionDtoResponse> getAllTransactions() {

        return transactionService.getAllTransactions();
    }

    @GetMapping("/{transactionId}")
    public TransactionDtoResponse getTransactionById(@Valid @PathVariable UUID transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    @PostMapping("/create")
    public ResponseEntity<TransactionDtoResponse> createTransaction(@Valid
            @RequestBody TransactionDtoRequest requestDTO) {

        TransactionDtoResponse responseDTO = transactionService.createTransaction(requestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{transactionId}")
    public ResponseEntity<TransactionDtoResponse> updateTransaction(@Valid
            @PathVariable UUID transactionId,
            @RequestBody TransactionDtoRequest updatedData) {

        try {
            TransactionDtoResponse updatedTransaction = transactionService.updateTransaction(transactionId, updatedData);
            return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);

        } catch (TransactionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@Valid @PathVariable UUID transactionId) {
        try {
            transactionService.deleteTransaction(transactionId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (TransactionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
