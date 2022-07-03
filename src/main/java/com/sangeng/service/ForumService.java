package com.sangeng.service;

import com.sangeng.domain.Comment;
import com.sangeng.vo.ResponseResult;
import com.sangeng.domain.Topic;

public interface ForumService {
    ResponseResult createTopic(Topic topic);

    ResponseResult getTopicById();

    ResponseResult comment(Comment comment);

    ResponseResult reportTopicById(Long id);

    ResponseResult reportCommentById(Long id);

    ResponseResult getTopicReported();

    ResponseResult getCommentReported();
}
