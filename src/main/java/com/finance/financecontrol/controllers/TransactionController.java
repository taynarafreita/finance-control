package com.finance.financecontrol.controllers;

import com.finance.financecontrol.dtos.requests.TransactionDtoRequest;
import com.finance.financecontrol.dtos.responses.TransactionDtoResponse;
import com.finance.financecontrol.exceptions.TransactionNotFoundException;
import com.finance.financecontrol.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<TransactionDtoResponse> getAllTransactions() {

        return transactionService.getAllTransactions();
    }

    @PostMapping("/create")
    public ResponseEntity<TransactionDtoResponse> createTransaction(
            @RequestBody TransactionDtoRequest requestDTO) {

        TransactionDtoResponse responseDTO = transactionService.createTransaction(requestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{transactionId}")
    public ResponseEntity<TransactionDtoResponse> updateTransaction(
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
    public ResponseEntity<Void> deleteTransaction(@PathVariable UUID transactionId) {
        try {
            transactionService.deleteTransaction(transactionId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (TransactionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
