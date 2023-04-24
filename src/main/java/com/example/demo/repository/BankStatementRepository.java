package com.example.demo.repository;

import com.example.demo.model.entity.BankStatement;
import com.example.demo.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class BankStatementRepository {

    @Value("${spring.datasource.url}")
    private String dbUri = null;

    public List<BankStatement> findAccountStatements(long accountId) {
        List<BankStatement> bankStatements = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUri); PreparedStatement preparedStatement = connection.prepareStatement(Constants.FETCH_STATEMENTS_BY_ACCOUNT_NUMBER_QUERY)) {
            preparedStatement.setLong(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                BankStatement bankStatement = new BankStatement(rs.getInt(Constants.COLUMN_ID), rs.getLong(Constants.COLUMN_ACCOUNT_ID), rs.getString(Constants.COLUMN_DATE_FIELD), Double.parseDouble(rs.getString(Constants.COLUMN_AMOUNT)));
                bankStatements.add(bankStatement);
            }
        } catch (SQLException e) {
            log.error("Error occurred while fetching the data and error is {}", e.getMessage());
        }
        return bankStatements;
    }
}
