package com.example.demo.utils;

public class Constants {

    private Constants(){

    }

    public static final String STATEMENT_TABLE = "Statement";

    public static final String COLUMN_ID = "ID";

    public static final String COLUMN_ACCOUNT_ID = "account_id";

    public static final String COLUMN_AMOUNT = "AMOUNT";

    public static final String COLUMN_DATE_FIELD = "datefield";

    public static final String FETCH_STATEMENTS_BY_ACCOUNT_NUMBER_QUERY = "SELECT * FROM " + STATEMENT_TABLE + " WHERE " + COLUMN_ACCOUNT_ID + " = ?";
}
