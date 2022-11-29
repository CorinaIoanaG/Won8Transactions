package org.fasttrackit.SpringWeb.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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

    @GetMapping(value = "{type}")
    public List<Transaction> getByType(@RequestParam Type type) {
        return transactionsService.getTransactionsByType(type);
    }

    @GetMapping(value = "/min/{amount}")
    public List<Transaction> getByMinAmount(@RequestParam Double amount) {
        return transactionsService.getTransactionsByMinAmount(amount);
    }

    @GetMapping(value = "/max/{amount}")
    public List<Transaction> getByMaxAmount(@RequestParam Double amount) {
        return transactionsService.getTransactionsByMaxAmount(amount);
    }

    @GetMapping(value = "{id}")
    public Transaction getById(@PathVariable int id) {
        return transactionsService.getById(id);
    }

    @PostMapping
    public Transaction add(@RequestBody Transaction transaction) {
        return transactionsService.add(transaction);
    }

    @PutMapping(value = "{id}")
    public Transaction update(@PathVariable int id, @RequestBody Transaction transaction) {
        return transactionsService.update(id, transaction);
    }

    @DeleteMapping(value = "{id}")
    public Transaction deleteById(@PathVariable int id) {
        return transactionsService.deleteById(id);
    }

    @GetMapping(value = "/type-group")
    public Map<Type, Transaction> getMapByType() {
        return transactionsService.getTransactionsMapByType();
    }

    @GetMapping(value = "/product-group")
    public Map<String, Transaction> getMapByProduct() {
        return transactionsService.getTransactionsMapByProduct();
    }

}
