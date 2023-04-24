package com.example.demo.controller;

import com.example.demo.facade.BankStatementFacade;
import com.example.demo.model.request.StatementRequest;
import com.example.demo.model.response.BankStatementResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bank-statements")
@Validated
public class BankStatementControllerVersion1 {

    @Autowired
    BankStatementFacade bankStatementFacade;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<List<BankStatementResponse>> findAccountStatements(@Valid @RequestBody StatementRequest statementRequest, HttpServletRequest request) {
        return ResponseEntity.ok().body(bankStatementFacade.findAccountStatements(statementRequest));
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping
    public ResponseEntity<List<BankStatementResponse>> findAccountStatementsOfUser(HttpServletRequest request) {
        return ResponseEntity.ok().body(bankStatementFacade.findAccountStatementsOfUser(request));
    }
}
