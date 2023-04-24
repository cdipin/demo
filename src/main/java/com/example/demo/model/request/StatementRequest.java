package com.example.demo.model.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatementRequest {

    @Min(value = 0, message = "Account id is mandatory")
    private long accountId;

    @Min(value = 0, message = "Minimum value should be 0")
    private double minAmount;

    @Min(value = 1, message = "Minimum value should be 1")
    private double maxAmount;

    private Date startDate;

    private Date endDate;
}
