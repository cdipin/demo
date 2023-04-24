package com.example.demo.facade;

import com.example.demo.model.request.StatementRequest;
import com.example.demo.model.response.BankStatementResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface BankStatementFacade {

    List<BankStatementResponse> findAccountStatements(StatementRequest statementRequest);

    List<BankStatementResponse> findAccountStatementsOfUser(HttpServletRequest request);
}
