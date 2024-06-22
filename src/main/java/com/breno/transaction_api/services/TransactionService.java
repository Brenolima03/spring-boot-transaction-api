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
        BeanUtils.copyProperties(transactionDTO, transaction);

        String formattedCardNumber = formatCardNumber(
            transaction.getCardNumber()
        );
        transaction.setCardNumber(formattedCardNumber);

        if (!Transaction.isCardNumberValid(transaction.getCardNumber())) {
            throw new IllegalArgumentException("Invalid card number!");
        }

        return transactionRepository.save(transaction);
    }

    @Transactional(readOnly = true)
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Transaction findTransactionById(UUID id) {
        return transactionRepository.findById(id)
            .orElseThrow(() -> new TransactionNotFoundException(
                "Transaction not found with ID: " + id)
            );
    }

    @Transactional
    public Transaction updateTransaction(
        UUID id, TransactionDTO transactionDTO
    ) {
        Transaction transaction = findTransactionById(id);
        BeanUtils.copyProperties(transactionDTO, transaction);

        String formattedCardNumber = formatCardNumber(
            transaction.getCardNumber()
        );
        transaction.setCardNumber(formattedCardNumber);

        if (!Transaction.isCardNumberValid(transaction.getCardNumber())) {
            throw new IllegalArgumentException("Invalid card number!");
        }

        return transactionRepository.save(transaction);
    }

    @Transactional
    public void deleteTransaction(UUID id) {
        Transaction transaction = transactionRepository.findById(id)
            .orElseThrow(() -> new TransactionNotFoundException(
                "Transaction not found with ID: " + id)
            );

        transactionRepository.delete(transaction);
    }

    private String formatCardNumber(String cardNumber) {
        return cardNumber.replace(".", "").replace(" ", "");
    }
}
