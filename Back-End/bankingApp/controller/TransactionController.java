package com.bank.bankingApp.controller;

import com.bank.bankingApp.model.Transaction;
import com.bank.bankingApp.model.User;
import com.bank.bankingApp.service.TransactionService;
import com.bank.bankingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:3000") // Enable frontend-backend communication
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    // Get transactions for a specific user
    @GetMapping("/{userId}")
    public ResponseEntity<?> getTransactionsByUserId(@PathVariable Long userId) {
        // Check if user exists
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        // Retrieve transactions
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        if (transactions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No transactions available for this user.");
        }
        return ResponseEntity.ok(transactions);
    }

    // Get user balance
    @GetMapping("/{userId}/balance")
    public ResponseEntity<?> getUserBalance(@PathVariable Long userId) {
        // Check if user exists
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        // Calculate balance
        double balance = transactionService.calculateUserBalance(userId);
        return ResponseEntity.ok(balance);
    }

    // Add a new transaction
    @PostMapping("/{userId}")
    public ResponseEntity<?> addTransaction(@PathVariable Long userId, @RequestBody Transaction transaction) {
        // Check if user exists
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        // Add transaction
        Transaction newTransaction = transactionService.addTransaction(transaction, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
    }
}
