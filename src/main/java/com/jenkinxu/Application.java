package com.jenkinxu;

import com.jenkinxu.entity.Comment;
import com.jenkinxu.repository.CommentRepository;
import com.jenkinxu.repository.LoanDraftRepository;
import com.jenkinxu.repository.po.LoanPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@SpringBootApplication
public class Application implements ApplicationRunner {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private LoanDraftRepository loanDraftRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("启动成功！");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        testComment();
        testPageUseIndex();
        testPageNoIndex();
        testPageNoIndexAndLike();
    }

    private void testPageNoIndexAndLike() {
        Instant begin = Instant.now();
        Page<LoanPO> page = loanDraftRepository.findByCompanyCnNameIsLike("保险", 100000, 10);
        log.info("无索引模糊查询cost:{}ms",ChronoUnit.MILLIS.between(begin,Instant.now()));
        log.info(page.toString());
        // page.forEach(o -> log.info(o.toString()));
    }

    private void testPageUseIndex() {
        Instant begin = Instant.now();
        Page<LoanPO> page = loanDraftRepository.findByLoanAccountNo("880000000111", 100, 10);
        log.info("使用索引cost:{}ms",ChronoUnit.MILLIS.between(begin,Instant.now()));
        log.info(page.toString());
        // page.forEach(o -> log.info(o.toString()));
    }
    private void testPageNoIndex() {
        Instant begin = Instant.now();
        Page<LoanPO> page = loanDraftRepository.findByCompanyEnName("Zhongan Online P&C Insurance Co., Ltd.", 100000, 10);
        log.info("无索引cost:{}ms",ChronoUnit.MILLIS.between(begin,Instant.now()));
        log.info(page.toString());
        // page.forEach(o -> log.info(o.toString()));
    }

    private void testLoans() {
        loanDraftRepository.drop();
        final LoanPO loanPO = initLoanPO();
        Instant begin = Instant.now();
        for (int i = 1; i <= 10; i++) {
            final String loanAccountNo = String.format("88%010d", i);
            for (int j = 0; j < 10; j++) {
                final String loanNoSeq = String.format("%04d", j);
                loanPO.setLoanAccountNo(loanAccountNo);
                loanPO.setLoanNo(loanAccountNo + loanNoSeq);
                loanDraftRepository.insert(loanPO);
            }
            log.info("插入{}条Loan记录耗时：{}s", i * 10000, ChronoUnit.SECONDS.between(begin, Instant.now()));
        }
        log.info("插入1千万条Loan记录耗时：{}s", ChronoUnit.SECONDS.between(begin, Instant.now()));
    }

    private LoanPO initLoanPO() {
        LocalDate today = LocalDate.now();
        return LoanPO.builder()
                .loanNo("8800000000010001")
                .loanAccountNo("880000000001")
                .customerNo("8000000001")
                .status(1)
                .companyEnName("Zhongan Online P&C Insurance Co., Ltd.")
                .companyCnName("众安在线财产保险股份有限公司")
                .loanTenor(3)
                .termUnit("month")
                .valueDate(today)
                .maturityDate(today)
                .ccy("HKD")
                .loanAmount(BigDecimal.valueOf(5000000))
                .drawdownType(2)
                .withdrawalFee(null)
                .delayWithdrawalFee(null)
                .actualPayAmount(BigDecimal.valueOf(5000000))
                .interestDayBasis(360)
                .supplierType("2")
                .supplierName("供应商名称")
                .supplierCnName("供应商中文名称")
                .exchangeRate(BigDecimal.ONE)
                .remarks("备注一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十")
                .rateFloatType(1)
                .referenceRateType("1")
                .referenceReteTerm("3")
                .costRate(BigDecimal.ZERO)
                .floatMethod(1)
                .rateSpread(BigDecimal.ONE)
                .percentSpread(BigDecimal.valueOf(3.2))
                .loanRate(BigDecimal.ZERO)
                .overdueReferenceRateMode(1)
                .overdueReferenceRateType("029")
                .overdueReferenceRateTerm("301")
                .overdueReferenceRate(BigDecimal.valueOf(0.026367))
                .overdueRateSpread(BigDecimal.valueOf(0.07))
                .overdueInterestRate(BigDecimal.valueOf(0.096367))
                .repaymentSeqCode(BigDecimal.valueOf(12341223))
                .partRepaymentFlag("Y")
                .repaymentMethod(4)
                .repaymentIntervalPeriods(1)
                .earlyRepaymentPercent(BigDecimal.valueOf(0.07))
                .minimumEarlyRepayment(null)
                .totalDeferTimes(0)
                .totalDeferPeriods(0)
                .graceDays(0)
                .graceHandleType(1)
                .minimumPaymentPercent(BigDecimal.valueOf(0.01))
                .rolloverFlag("Y")
                .comparisonRateMode(1)
                .referenceRateType2("1")
                .referenceRateTerm2("3")
                .costRate2(BigDecimal.ONE)
                .rateSpread2(BigDecimal.ONE)
                .rateRepricingFrequency(2)
                .rateRepricingFrequencyUnit("month")
                .previousRepricingDate(today)
                .nextRepricingDate(today)
                .rateRepricingCount(0)
                .outerMaker("xuchenzhi")
                .outerCheckers("xuchenzhi")
                .outerExecutor("jenkinxu")
                .applyChannel("pay")
                .creator("chenzhi.xu")
                .build();
    }

    private void testComment() {
        commentRepository.saveComment(Comment.builder()
                .userid("1000")
                .articleid("100000")
                .content("测试")
                .createdatetime(LocalDateTime.now())
                .nickname("徐琛知")
                .state("1")
                .likenum(0)
                .replynum(0)
                .build());
        log.info(commentRepository.findCommentList().toString());
    }
}
