package com.example.demo.transformer;

import com.example.demo.model.entity.BankStatement;
import com.example.demo.model.response.BankStatementResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.security.SecureRandom;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class BankStatementTransformer {

    private String encodeNumber(long data) {
        int strength = 10;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        return bCryptPasswordEncoder.encode(String.valueOf(data));
    }

    private BankStatementResponse transformToBankStatementResponse(BankStatement bankStatement) {
        BankStatementResponse bankStatementResponse = new BankStatementResponse();
        bankStatementResponse.setAmount(bankStatement.getAmount());
        bankStatementResponse.setTransactionDate(Date.valueOf(bankStatement.getTransactionDate()));
        bankStatementResponse.setAccountId(encodeNumber(bankStatement.getAccountId()));
        return bankStatementResponse;
    }

    public List<BankStatementResponse> transformToBankStatementResponses(List<BankStatement> bankStatements) {
        List<BankStatementResponse> bankStatementResponses = new ArrayList<>();
        if (CollectionUtils.isEmpty(bankStatements)) {
            return bankStatementResponses;
        }
        bankStatements.stream().forEach(bankStatement -> bankStatementResponses.add(transformToBankStatementResponse(bankStatement)));
        return bankStatementResponses;
    }
}
