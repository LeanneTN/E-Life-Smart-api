package org.csu.service;

import org.csu.domain.Comment;
import org.csu.vo.ResponseResult;
import org.csu.domain.Topic;

public interface ForumService {
    ResponseResult createTopic(Topic topic);

    ResponseResult getTopicById();

    ResponseResult comment(Comment comment);

    ResponseResult reportTopicById(Long id);

    ResponseResult reportCommentById(Long id);

    ResponseResult getTopicReported();

    ResponseResult getCommentReported();
}
