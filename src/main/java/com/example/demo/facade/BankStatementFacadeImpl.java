package com.example.demo.facade;

import com.example.demo.model.request.StatementRequest;
import com.example.demo.model.response.BankStatementResponse;
import com.example.demo.service.BankStatementService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BankStatementFacadeImpl implements BankStatementFacade {

    @Autowired
    BankStatementService bankStatementService;

    @Override
    public List<BankStatementResponse> findAccountStatements(StatementRequest statementRequest) {
        return bankStatementService.findAccountStatements(statementRequest);
    }

    @Override
    public List<BankStatementResponse> findAccountStatementsOfUser(HttpServletRequest request) {
        StatementRequest statementRequest = new StatementRequest();
        statementRequest.setAccountId(1);
        return bankStatementService.findAccountStatements(statementRequest);
    }
}
