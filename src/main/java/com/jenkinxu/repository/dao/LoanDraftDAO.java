package com.jenkinxu.repository.dao;

import com.jenkinxu.repository.po.LoanPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface LoanDraftDAO extends MongoRepository<LoanPO, String> {
    Page<LoanPO> findByLoanAccountNo(String loanAccountNo, Pageable pageable);

    @Query("{'customerNo': ?#{[0]}}")
    Page<LoanPO> findByQueryWithExpression(String customerNo, Pageable pageable);

    Page<LoanPO> findByCompanyCnNameIsLike(String cnName, Pageable pageable);
}
