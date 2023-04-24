package com.example.demo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_statement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankStatement {

    @Id
    private Number id;

    private long accountId;

    private String transactionDate;

    private Number amount;
}
