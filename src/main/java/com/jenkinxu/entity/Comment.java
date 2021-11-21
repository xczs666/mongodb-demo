package com.jenkinxu.entity;

import com.jenkinxu.repository.po.CommentPO;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String id;//主键
    //该属性对应mongodb的字段的名字，如果一致，则无需该注解
    @Setter
    private String content;//吐槽内容
    @Setter
    private LocalDateTime publishtime;//发布日期
    @Setter
    private String userid;//发布人ID
    @Setter
    private String nickname;//昵称
    @Setter
    private LocalDateTime createdatetime;//评论的日期时间
    @Setter
    private Integer likenum;//点赞数
    @Setter
    private Integer replynum;//回复数
    @Setter
    private String state;//状态
    @Setter
    private String parentid;//上级ID
    @Setter
    private String articleid;

    public static Comment from(CommentPO po) {
        return Comment.builder()
                .id(po.getId())
                .content(po.getContent())
                .publishtime(Optional.ofNullable(po.getPublishtime()).map(o -> new Timestamp(o.getTime()).toLocalDateTime()).orElse(null))
                .userid(po.getUserid())
                .nickname(po.getNickname())
                .createdatetime(po.getCreatedatetime())
                .likenum(po.getLikenum())
                .replynum(po.getReplynum())
                .state(po.getState())
                .parentid(po.getParentid())
                .articleid(po.getArticleid())
                .build();
    }

    public CommentPO toPO() {
        return CommentPO.builder()
                .id(this.getId())
                .content(this.getContent())
                .publishtime(this.getPublishtime() == null ? null : Timestamp.valueOf(this.getPublishtime()))
                .userid(this.getUserid())
                .nickname(this.getNickname())
                .createdatetime(this.getCreatedatetime())
                .likenum(this.getLikenum())
                .replynum(this.getReplynum())
                .state(this.getState())
                .parentid(this.getParentid())
                .articleid(this.getArticleid())
                .build();
    }
}
