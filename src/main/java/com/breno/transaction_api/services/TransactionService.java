package com.breno.transaction_api.services;

import com.breno.transaction_api.dtos.TransactionDTO;
import com.breno.transaction_api.entities.Transaction;
import com.breno.transaction_api.exceptions.TransactionNotFoundException;
import com.breno.transaction_api.repositories.TransactionRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public Transaction createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        // Map the DTO to the entity.
        BeanUtils.copyProperties(transactionDTO, transaction);
        return transactionRepository.save(transaction);
    }

    @Transactional(readOnly = true)
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Transaction findById(UUID id) {
        return transactionRepository.findById(id)
            .orElseThrow(() -> new TransactionNotFoundException(
                "Transaction not found with ID: " + id)
            );
    }

    @Transactional
    public Transaction update(UUID id, TransactionDTO transactionDTO) {
        // This will throw TransactionNotFoundException if not found
        Transaction existingTransaction = findById(id);
        BeanUtils.copyProperties(transactionDTO, existingTransaction);
        return transactionRepository.save(existingTransaction);
    }
}
