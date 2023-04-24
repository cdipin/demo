package com.example.demo.service;

import com.example.demo.helper.BankStatementHelper;
import com.example.demo.model.entity.BankStatement;
import com.example.demo.model.request.StatementRequest;
import com.example.demo.model.response.BankStatementResponse;
import com.example.demo.repository.BankStatementRepository;
import com.example.demo.transformer.BankStatementTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankStatementServiceImpl implements BankStatementService {

    @Autowired
    BankStatementRepository bankStatementRepository;

    @Autowired
    BankStatementTransformer bankStatementTransformer;

    @Autowired
    BankStatementHelper bankStatementHelper;

    @Override
    public List<BankStatementResponse> findAccountStatements(StatementRequest statementRequest) {
        List<BankStatement> bankStatements = bankStatementRepository.findAccountStatements(statementRequest.getAccountId());
        List<BankStatementResponse> bankStatementResponses = bankStatementTransformer.transformToBankStatementResponses(bankStatements);
        return bankStatementHelper.filterData(statementRequest, bankStatementResponses);
    }
}
