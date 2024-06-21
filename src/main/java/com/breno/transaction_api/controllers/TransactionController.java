package com.breno.transaction_api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.breno.transaction_api.dtos.TransactionDTO;
import com.breno.transaction_api.entities.Transaction;
import com.breno.transaction_api.exceptions.TransactionNotFoundException;
import com.breno.transaction_api.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/post")
    public ResponseEntity<Transaction> create(
        @RequestBody TransactionDTO transactionDTO
    ) {
        try {
            Transaction createdTransaction =
                transactionService.createTransaction(transactionDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdTransaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(transactionService.findAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable("id") UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                .body(transactionService.findTransactionById(id));
        } catch (TransactionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Transaction> update(
        @PathVariable("id") UUID id,
        @RequestBody TransactionDTO transactionDTO
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                .body(transactionService.updateTransaction(id, transactionDTO));
        } catch (TransactionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok("Transaction deleted successfully!");
    }
}
