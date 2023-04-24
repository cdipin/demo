package com.example.demo.model.response;

import lombok.Data;

import java.sql.Date;

@Data
public class BankStatementResponse {

    private String accountId;

    private Date transactionDate;

    private Number amount;
}
