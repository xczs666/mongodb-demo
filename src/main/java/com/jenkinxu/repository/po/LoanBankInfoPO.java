package com.jenkinxu.repository.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="loan_bank_info")//可以省略，如果省略，则默认使用类名小写映射集合
public class LoanBankInfoPO {
    @Id
    private String id;//主键
    private String loanNo;
    private String loanAccountNo;
    private Integer infoType;
    private String bankAccountNo;
    private String bankAccountName;
    private String bankCode;
    private String bankName;
    private Integer payChannel;
}
