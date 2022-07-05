package org.csu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.sf.jsqlparser.statement.select.Top;
import org.csu.domain.Comment;
import org.csu.vo.ResponseCode;
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

    //创建话题
    @Override
    public ResponseResult createTopic(Topic topic) {
        //首先查询话题是否已经存在
        LambdaQueryWrapper<Topic> wrapper = new QueryWrapper<Topic>().lambda();
        topicMapper.insert(topic);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(),"话题创建成功");
    }

    //根据ID，获取话题
    @Override
    public ResponseResult getTopicById() {
        LambdaQueryWrapper<Topic> wrapper = new QueryWrapper<Topic>().lambda();
//        wrapper.eq()
        return null;
    }

    //提交评论
    @Override
    public ResponseResult comment(Comment comment) {
        this.commentMapper.insert(comment);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(),"评论提交成功");
    }

    //根据ID举报话题
    @Override
    public ResponseResult reportTopicById(Long id) {
        return null;
    }
    //根据ID举报评论
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
