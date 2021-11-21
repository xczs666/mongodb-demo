package com.jenkinxu.repository;

import com.jenkinxu.entity.Comment;
import com.jenkinxu.repository.po.CommentPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存一个评论
     * @param comment
     */
    public void saveComment(Comment comment){
        //如果需要自定义主键，可以在这里指定主键；如果不指定主键，MongoDB会自动生成主键
        mongoTemplate.save(comment.toPO());
    }

    /**
     * 查询所有评论
     * @return
     */
    public List<Comment> findCommentList(){
        //调用dao
        return mongoTemplate.findAll(CommentPO.class).stream().map(Comment::from).collect(Collectors.toList());
    }
}
