package org.fasttrackit.SpringWeb.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.fasttrackit.SpringWeb.model.Transaction;
import org.fasttrackit.SpringWeb.model.Type;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service

public class TransactionsService {
    private final TransactionsReader transactionsReader;
    private List<Transaction> transactionList;

    public TransactionsService(TransactionsReader transactionsReader) {
        this.transactionsReader = transactionsReader;
        transactionList = transactionsReader.getTransactions();
    }

    public List<Transaction> getAllTransactions() {
        return transactionList;
    }

    public List<Transaction> getTransactionsByProduct(String product) {
        return transactionList.stream()
                .filter(item -> item.getProduct().equals(product))
                .toList();
    }

    public List<Transaction> getTransactionsByMinAmount(Double amount) {
        return transactionList.stream()
                .filter(item -> item.getAmount() > amount)
                .toList();
    }

    public List<Transaction> getTransactionsByMaxAmount(Double amount) {
        return transactionList.stream()
                .filter(item -> item.getAmount() < amount)
                .toList();
    }

    public List<Transaction> getTransactionsByType(Type type) {
        return transactionList.stream()
                .filter(item -> item.getType() == type)
                .toList();
    }

    public Transaction getById(int id) {
        return transactionList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Transaction missing: " + id));
    }

    public Transaction add(Transaction transaction) {
        transaction.setId(TransactionsReader.transactionId++);
        transactionList.add(transaction);
        return transaction;
    }

    public Transaction update(int id, Transaction transaction) {
        Transaction transaction1 = getById(id);
        transaction1.setProduct(transaction.getProduct());
        transaction1.setType(transaction.getType());
        transaction1.setAmount(transaction.getAmount());
        return transaction1;
    }

    public Transaction deleteById(int id) {
        Transaction transaction = getById(id);
        transactionList.remove(transaction);
        return transaction;
    }

    public Map<Type,Transaction> getTransactionsMapByType() {
        return transactionList.stream()
                .collect(Collectors.toMap(Transaction::getType, Function.identity()));
    }

    public Map<String,Transaction> getTransactionsMapByProduct() {
        return transactionList.stream()
                .collect(Collectors.toMap(Transaction::getProduct, Function.identity()));
    }

}
