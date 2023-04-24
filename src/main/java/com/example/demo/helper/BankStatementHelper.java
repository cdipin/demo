package com.example.demo.helper;

import com.example.demo.model.request.StatementRequest;
import com.example.demo.model.response.BankStatementResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

@Component
public class BankStatementHelper {

    public List<BankStatementResponse> filterData(StatementRequest statementRequest, List<BankStatementResponse> bankStatements) {
        if (CollectionUtils.isEmpty(bankStatements)) {
            return bankStatements;
        }
        return filterByDate(statementRequest, bankStatements);
    }

    private List<BankStatementResponse> filterByDate(StatementRequest statementRequest, List<BankStatementResponse> bankStatements) {
        Predicate<BankStatementResponse> predicate = null;
        if (statementRequest.getStartDate() != null || statementRequest.getEndDate() != null) {
            if (statementRequest.getStartDate() != null && statementRequest.getEndDate() != null) {
                predicate = bankStatement -> bankStatement.getTransactionDate().after(statementRequest.getStartDate()) && bankStatement.getTransactionDate().before(statementRequest.getEndDate());
            } else if (statementRequest.getStartDate() != null) {
                predicate = bankStatement -> bankStatement.getTransactionDate().after(statementRequest.getStartDate());
            } else if (statementRequest.getEndDate() != null) {
                predicate = bankStatement -> bankStatement.getTransactionDate().before(statementRequest.getEndDate());
            }
        } else {
            LocalDate currentDate = LocalDate.now();
            Date startDate = Date.valueOf(currentDate.minusMonths(3));
            Date endDate = Date.valueOf(currentDate);
            predicate = bankStatement -> bankStatement.getTransactionDate().after(startDate) && bankStatement.getTransactionDate().before(endDate);
        }
        List<BankStatementResponse> filteredBankStatements = bankStatements.stream().filter(predicate).toList();
        return filterByAmount(statementRequest, filteredBankStatements);
    }

    private List<BankStatementResponse> filterByAmount(StatementRequest statementRequest, List<BankStatementResponse> bankStatements) {
        if (CollectionUtils.isEmpty(bankStatements) || (statementRequest.getMinAmount() == 0.0 && statementRequest.getMaxAmount() == 0.0)) {
            return bankStatements;
        }
        return bankStatements.stream().filter(bankStatement -> bankStatement.getAmount().doubleValue() >= statementRequest.getMinAmount() && bankStatement.getAmount().doubleValue() <= statementRequest.getMaxAmount()).toList();
    }
}
