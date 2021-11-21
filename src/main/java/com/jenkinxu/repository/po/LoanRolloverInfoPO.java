package com.jenkinxu.repository.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection="loan_survey")
public class LoanRolloverInfoPO {
    @Id
    private String id;//主键
    private String loanNo;
    private String rolloverFlag;
    private String ccy;
    private BigDecimal rolloverAmount;
    private BigDecimal loanRate;
    private LocalDate valueDate;
    private LocalDate maturityDate;
    private Integer rolloverLoanPeriods;
    private String rolloverTermUnit;
    private String expectedLoanNo;
    private String bizNo;
    private String outerMaker;
    private String outerCheckers;
    private String outerExecutor;
}
