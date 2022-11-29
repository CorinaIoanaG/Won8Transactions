package org.fasttrackit.SpringWeb.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class Transaction {
    private int id;
    private String product;
    private Type type;
    private double amount;
}
