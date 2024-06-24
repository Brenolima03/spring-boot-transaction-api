package com.breno.transaction_api.controllers;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.breno.transaction_api.dtos.TransactionDTO;
import com.breno.transaction_api.entities.Transaction;
import com.breno.transaction_api.services.TransactionService;

@Controller
@RequestMapping("/transactions-web")
public class TransactionWebController {

    private final TransactionService transactionService;

    public TransactionWebController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/form")
    public String transactionForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "index";
    }
    
    @PostMapping("/transaction-response")
    public String submitTransaction(@ModelAttribute("transactionDTO") TransactionDTO transactionDTO, Model model) {
        Transaction createdTransaction = transactionService.createTransaction(transactionDTO);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate = createdTransaction.getCreatedAt().format(formatter);
        model.addAttribute("transaction", createdTransaction);
        model.addAttribute("formattedCreatedAt", formattedDate);
        return "transaction-response";
    }
}
