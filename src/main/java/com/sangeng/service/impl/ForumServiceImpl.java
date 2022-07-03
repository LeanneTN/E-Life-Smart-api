package com.sangeng.service.impl;

import com.sangeng.domain.Comment;
import com.sangeng.vo.ResponseResult;
import com.sangeng.domain.Topic;
import com.sangeng.mapper.CommentMapper;
import com.sangeng.mapper.TopicMapper;
import com.sangeng.service.ForumService;
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
