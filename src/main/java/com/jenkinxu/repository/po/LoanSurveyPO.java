package com.jenkinxu.repository.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="loan_survey")
public class LoanSurveyPO {
    @Id
    private String id;//主键
    private String loanNo;
    private String loanAccountNo;
    private String loanUseLocation;
    private String chinaRegion;
    private String chinaProvince;
    private String industry;
}
