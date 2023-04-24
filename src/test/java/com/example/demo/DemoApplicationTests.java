package com.example.demo;

import com.example.demo.helper.BankStatementHelper;
import com.example.demo.model.entity.BankStatement;
import com.example.demo.model.request.StatementRequest;
import com.example.demo.model.response.BankStatementResponse;
import com.example.demo.repository.BankStatementRepository;
import com.example.demo.transformer.BankStatementTransformer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    BankStatementRepository bankStatementRepository;

    @Autowired
    BankStatementTransformer bankStatementTransformer;

    @Autowired
    BankStatementHelper bankStatementHelper;

    @Test
    void findAccountStatements() {
        StatementRequest statementRequest = new StatementRequest(1, 0, 10000, null, null);
        List<BankStatement> bankStatements = bankStatementRepository.findAccountStatements(statementRequest.getAccountId());
        List<BankStatementResponse> bankStatementResponses = bankStatementTransformer.transformToBankStatementResponses(bankStatements);
        bankStatementResponses = bankStatementHelper.filterData(statementRequest, bankStatementResponses);
        assertNotNull(bankStatementResponses);
    }
}
