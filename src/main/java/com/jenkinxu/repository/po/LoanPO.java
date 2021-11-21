package com.jenkinxu.repository.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndexes({
        @CompoundIndex(name = "pk_loan_no", def = "{'loanNo':1}", unique = true),
        @CompoundIndex(name = "idx_loan_account_no", def = "{'loanAccountNo':1}"),
})
@Document(collection = "loan")//可以省略，如果省略，则默认使用类名小写映射集合
public class LoanPO {
    @Id
    private String id;//主键
    private String loanNo;
    private String loanAccountNo;
    private String customerNo;
    private Integer status;
    private String companyEnName;
    private String companyCnName;
    private Integer loanTenor;
    private String termUnit;
    private LocalDate valueDate;
    private LocalDate maturityDate;
    private String ccy;
    private BigDecimal loanAmount;
    private Integer drawdownType;
    private BigDecimal withdrawalFee;
    private BigDecimal delayWithdrawalFee;
    private BigDecimal actualPayAmount;
    private Integer interestDayBasis;
    private String supplierType;
    private String supplierName;
    private String supplierCnName;
    private BigDecimal exchangeRate;
    private String remarks;
    private Integer rateFloatType;
    private String referenceRateType;
    private String referenceReteTerm;
    private BigDecimal costRate;
    private Integer floatMethod;
    private BigDecimal rateSpread;
    private BigDecimal percentSpread;
    private BigDecimal loanRate;
    private Integer overdueReferenceRateMode;
    private String overdueReferenceRateType;
    private String overdueReferenceRateTerm;
    private BigDecimal overdueReferenceRate;
    private BigDecimal overdueRateSpread;
    private BigDecimal overdueInterestRate;
    private BigDecimal repaymentSeqCode;
    private String partRepaymentFlag;
    private Integer repaymentMethod;
    private Integer repaymentIntervalPeriods;
    private BigDecimal earlyRepaymentPercent;
    private BigDecimal minimumEarlyRepayment;
    private Integer totalDeferTimes;
    private Integer totalDeferPeriods;
    private Integer graceDays;
    private Integer graceHandleType;
    private BigDecimal minimumPaymentPercent;
    private String rolloverFlag;
    private Integer comparisonRateMode;
    private String referenceRateType2;
    private String referenceRateTerm2;
    private BigDecimal costRate2;
    private BigDecimal rateSpread2;
    private Integer rateRepricingFrequency;
    private String rateRepricingFrequencyUnit;
    private LocalDate previousRepricingDate;
    private LocalDate nextRepricingDate;
    private Integer rateRepricingCount;
    private String outerMaker;
    private String outerCheckers;
    private String outerExecutor;
    private String applyChannel;
    private String creator;
}
