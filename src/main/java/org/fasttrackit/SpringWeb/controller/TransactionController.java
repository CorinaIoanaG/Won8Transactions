package org.fasttrackit.SpringWeb.controller;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.SpringWeb.model.Transaction;
import org.fasttrackit.SpringWeb.model.Type;
import org.fasttrackit.SpringWeb.service.TransactionsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("transactions")

public class TransactionController {
    private final TransactionsService transactionsService;

    @GetMapping
    public List<Transaction> getAll(@RequestParam(required = false) String product) {
        if (product != null) {
            return transactionsService.getTransactionsByProduct(product);
        } else {
            return transactionsService.getAllTransactions();
        }
    }

    @GetMapping("{type}")
    public List<Transaction> getByType(@PathVariable Type type) {
        return transactionsService.getTransactionsByType(type);
    }

    @GetMapping("{amount1}")
    public List<Transaction> getByMinAmount(@PathVariable Double amount1) {
        return transactionsService.getTransactionsByMinAmount(amount1);
    }

    @GetMapping("{amount2}")
    public List<Transaction> getByMaxAmount(@PathVariable Double amount2) {
        return transactionsService.getTransactionsByMaxAmount(amount2);
    }

    @GetMapping("{id}")
    public Transaction getById(@PathVariable int id) {
        return transactionsService.getById(id);
    }

    @PostMapping
    public Transaction add(@RequestBody Transaction transaction) {
        return transactionsService.add(transaction);
    }

    @PutMapping("{id}")
    public Transaction update(@PathVariable int id, @RequestBody Transaction transaction) {
        return transactionsService.update(id, transaction);
    }

    @DeleteMapping("{id}")
    public Transaction deleteById(@PathVariable int id) {
        return transactionsService.deleteById(id);
    }

    @GetMapping
    public Map<Type, Transaction> getMapByType() {
        return transactionsService.getTransactionsMapByType();
    }

    @GetMapping
    public Map<String, Transaction> getMapByProduct() {
        return transactionsService.getTransactionsMapByProduct();
    }

}
