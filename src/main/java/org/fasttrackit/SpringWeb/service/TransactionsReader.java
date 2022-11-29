package org.fasttrackit.SpringWeb.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Value;
import org.fasttrackit.SpringWeb.model.Transaction;
import org.fasttrackit.SpringWeb.model.Type;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Repository

public class TransactionsReader {

    public static int transactionId = 0;

    public List<Transaction> getTransactions() {
        try {
            return Files.lines(Path.of("src/main/resources/transactions.txt"))
                    .map(this::lineToTransaction)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Transaction lineToTransaction(String line) {
        String[] transactions = line.split("\\|");
        return new Transaction(transactionId++, transactions[0], Type.valueOf(transactions[1]), Double.parseDouble(transactions[2]));
    }

}
