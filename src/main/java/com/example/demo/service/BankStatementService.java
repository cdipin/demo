package com.example.demo.service;

import com.example.demo.model.request.StatementRequest;
import com.example.demo.model.response.BankStatementResponse;

import java.util.List;


public interface BankStatementService {

    List<BankStatementResponse> findAccountStatements(StatementRequest statementRequest);
}
