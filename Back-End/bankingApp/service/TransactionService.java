package com.bank.bankingApp.service;

import com.bank.bankingApp.model.Transaction;
import com.bank.bankingApp.model.User;
import com.bank.bankingApp.repository.TransactionRepository;
import com.bank.bankingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    // Retrieve all transactions for a user
    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    // Calculate balance for a user
    public double calculateUserBalance(Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        double balance = 0;
        for (Transaction transaction : transactions) {
            if ("deposit".equalsIgnoreCase(transaction.getType())) {
                balance += transaction.getAmount();
            } else if ("withdrawal".equalsIgnoreCase(transaction.getType())) {
                balance -= transaction.getAmount();
            }
        }
        return balance;
    }

    // Add a new transaction
    public Transaction addTransaction(Transaction transaction, User user) {
        transaction.setUser(user);
        transaction.setDate(LocalDate.now()); // Set transaction date to today
        return transactionRepository.save(transaction);
    }
}
