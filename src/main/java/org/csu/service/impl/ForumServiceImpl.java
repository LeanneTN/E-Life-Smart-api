package org.csu.service.impl;

import org.csu.domain.Comment;
import org.csu.vo.ResponseResult;
import org.csu.domain.Topic;
import org.csu.mapper.CommentMapper;
import org.csu.mapper.TopicMapper;
import org.csu.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public ResponseResult createTopic(Topic topic) {
        return null;
    }

    @Override
    public ResponseResult getTopicById() {
        return null;
    }

    @Override
    public ResponseResult comment(Comment comment) {
        return null;
    }

    @Override
    public ResponseResult reportTopicById(Long id) {
        return null;
    }

    @Override
    public ResponseResult reportCommentById(Long id) {
        return null;
    }

    @Override
    public ResponseResult getTopicReported() {
        return null;
    }

    @Override
    public ResponseResult getCommentReported() {
        return null;
    }
}
