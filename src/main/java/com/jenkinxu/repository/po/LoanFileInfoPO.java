package com.jenkinxu.repository.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="loan_file_info")//可以省略，如果省略，则默认使用类名小写映射集合
public class LoanFileInfoPO {
    @Id
    private String id;//主键
    private Integer keyType;
    private String keyNo;
    private String originalFileName;
    private String obsKey;
    private String originalChannel;
}
