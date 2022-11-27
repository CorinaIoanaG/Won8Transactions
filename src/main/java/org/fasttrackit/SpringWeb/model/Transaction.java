package org.fasttrackit.SpringWeb.model;

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
