package com.jenkinxu.repository;

import com.jenkinxu.repository.dao.LoanDraftDAO;
import com.jenkinxu.repository.po.LoanPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanDraftRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private LoanDraftDAO loanDraftDAO;

    public void drop() {
        mongoTemplate.dropCollection(LoanPO.class);
    }

    public void insert(LoanPO po) {
        po.setId(null);
        //如果需要自定义主键，可以在这里指定主键；如果不指定主键，MongoDB会自动生成主键
        mongoTemplate.insert(po);
    }

    public Page<LoanPO> findByCompanyEnName(String companyEnName, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Query query = Query.query(Criteria.where("companyEnName").is(companyEnName)).with(pageable);
        List<LoanPO> list = mongoTemplate.find(query, LoanPO.class);
        return PageableExecutionUtils.getPage(list, pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), LoanPO.class));
    }

    public Page<LoanPO> findByLoanAccountNo(String loanAccountNo, int page, int size) {
        return loanDraftDAO.findByLoanAccountNo(loanAccountNo, PageRequest.of(page, size));
    }

    /**
     * 无索引模糊查询
     */
    public Page<LoanPO> findByCompanyCnNameIsLike(String cnName, int page, int size) {
        return loanDraftDAO.findByCompanyCnNameIsLike(cnName, PageRequest.of(page, size));
    }

    public List<LoanPO> findAll() {
        //调用dao
        return mongoTemplate.findAll(LoanPO.class);
    }
}
