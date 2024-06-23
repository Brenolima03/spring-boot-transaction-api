package com.breno.transaction_api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionWebController {
    @GetMapping("/post-transaction")
    public String postTransaction(Model model) {
        return "post-transaction.html";
    }
}
