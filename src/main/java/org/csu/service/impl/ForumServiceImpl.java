package org.csu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.sf.jsqlparser.statement.select.Top;
import org.csu.domain.Comment;
import org.csu.domain.Raw;
import org.csu.mapper.UserMapper;
import org.csu.vo.ResponseCode;
import org.csu.vo.ResponseResult;
import org.csu.domain.Topic;
import org.csu.mapper.CommentMapper;
import org.csu.mapper.TopicMapper;
import org.csu.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.stereotype.Service;

import javax.management.QueryEval;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;

    //创建话题
    @Override
    public ResponseResult createTopic(Topic topic) {
        topicMapper.insert(topic);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(),"话题创建成功");
    }

    //获取所有的话题
    @Override
    public ResponseResult getAllTopic() {
        List<Topic> topics = topicMapper.selectList(null);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), topics);
    }

    //更新话题
    @Override
    public ResponseResult updateTopic(Topic topic){
        topicMapper.updateById(topic);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "修改成功");
    }

    //根据关键词查询话题
    @Override
    public ResponseResult getTopicByKeywords(String keywords) {
        //首先查询该号码在不在库里
        LambdaQueryWrapper<Topic> wrapper = new QueryWrapper<Topic>().lambda();
        wrapper.like(true, Topic::getTitle, keywords);
        List<Topic> topics = topicMapper.selectList(wrapper);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "搜索成功", topics);
    }

    //根据ID，获取话题
    @Override
    public ResponseResult getTopicById(Long id) {
        Topic topic = topicMapper.selectById(id);
        if(topic==null){
            return new ResponseResult(ResponseCode.NO_TOPIC_LOG.getCode(), "该话题不存在");
        }
        return new ResponseResult(200,"获取话题成功",topic);
    }
    //根据ID，获得话题所拥有的回帖
    @Override
    public ResponseResult getTopic_CommentsById(Long id) {
        Topic topic = topicMapper.selectById(id);
        if(topic==null){
            return new ResponseResult(ResponseCode.NO_TOPIC_LOG.getCode(), "该话题不存在");
        }
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("to_id",id);
        List<Comment> comments = commentMapper.selectList(wrapper);
        if(comments.size()==0){
            return new ResponseResult(ResponseCode.NO_COMMENT_LOG.getCode(), "该话题没有回帖");
        }
        return new ResponseResult(200,comments);
    }

    //获取指定状态的话题
    @Override
    public ResponseResult getTopicByStatus(boolean isReported) {
        //只获取被举报的话题
        if(isReported){
            QueryWrapper<Topic> wrapper = new QueryWrapper<>();
            wrapper.eq("is_reported",isReported);
            List<Topic> topics = topicMapper.selectList(wrapper);
            return new ResponseResult(200,"成功获取话题",topics);
        }
        //否则，获取所有话题
        List<Topic> topics = topicMapper.selectList(null);
        return new ResponseResult(200,"成功获取所有的话题",topics);
    }

    //找到某一个用户创建的所有话题
    @Override
    public ResponseResult getTopicsByUser(Long id) {
        QueryWrapper<Topic> wrapper = new QueryWrapper<>();
        wrapper.eq("from_user",id);
        List<Topic> topics = topicMapper.selectList(wrapper);
        return new ResponseResult(200,"获取该用户所创建的话题成功",topics);
    }

    //根据ID举报话题
    @Override
    public ResponseResult reportTopicById(Long id) {
        Topic topic = topicMapper.selectById(id);
        if(topic==null){
            return new ResponseResult(ResponseCode.NO_TOPIC_LOG.getCode(), "该话题不存在");
        }
        //修改话题的状态，改为已被举报
        topic.setReported(true);
        topicMapper.updateById(topic);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "您已举报成功");
    }

    //根据ID删除话题
    //---------------------------此处需要鉴权，只有管理员和话题发布者可以删除话题-------------------------------------
    @Override
    public ResponseResult deleteTopicById(Long id) {
        Topic topic = topicMapper.selectById(id);
        if(topic==null){
            return new ResponseResult(ResponseCode.NO_TOPIC_LOG.getCode(), "该话题不存在");
        }
        //一：先删除话题所含有的回帖
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("to_id",id);
        List<Comment> comments = commentMapper.selectList(wrapper);
        for (Comment item:
             comments) {
            commentMapper.deleteById(item);
        }
        //二：删除话题
        topicMapper.deleteById(id);
        return new ResponseResult(2,"话题删除成功");
    }


    //----------------------------以下为回帖操作--------------------------------------


    //获得所有的回帖
    @Override
    public ResponseResult getAllComment(){
        List<Comment> commentList = commentMapper.selectList(null);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "成功获取所有的回帖信息", commentList);
    }

    //更新回帖
    @Override
    public ResponseResult updateComment(Comment comment){
        commentMapper.updateById(comment);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "更新回帖成功");
    }

    //提交回帖
    @Override
    public ResponseResult createComment(Comment comment) {
        commentMapper.insert(comment);
        //同时还要修改最后回复时间和回复数
        Topic topic = topicMapper.selectById(comment.getToId());
        topic.setResponse(topic.getResponse() + 1);
        topic.setLastReplyTime(new Date());
        topic.setLastReplyUser(userMapper.selectById(comment.getFromUser()).getUserName());
        topicMapper.updateById(topic);

        return new ResponseResult(200,"评论提交成功", comment);
    }

    //根据ID获取回帖
    @Override
    public ResponseResult getCommentById(Long id) {
        Comment comment = commentMapper.selectById(id);
        if(comment==null){
            return new ResponseResult(ResponseCode.NO_COMMENT_LOG.getCode(), "评论不存在");
        }
        return new ResponseResult(200,"获取回帖成功",comment);
    }

    //找到用户创建的所有回帖
    @Override
    public ResponseResult getCommentsByUser(Long id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("from_user",id);
        List<Comment> comments = commentMapper.selectList(wrapper);
        return new ResponseResult(200,"获取该用户创建的回帖成功",comments);
    }

    //根据ID举报评论
    @Override
    public ResponseResult reportCommentById(Long id) {
        Comment comment = commentMapper.selectById(id);
        if(comment==null){
            return new ResponseResult(ResponseCode.NO_COMMENT_LOG.getCode(), "评论不存在");
        }
        comment.setReported(true);
        commentMapper.updateById(comment);
        return null;
    }

    //获取指定状态的回帖
    @Override
    public ResponseResult getCommentByStatus(boolean isReported) {
        //0：未被举报的回帖。1：被举报的回帖
        if(isReported){
            QueryWrapper<Comment> wrapper = new QueryWrapper<>();
            wrapper.eq("is_reported",isReported);
            List<Comment> comments = commentMapper.selectList(wrapper);
            return new ResponseResult(200,"成功获取被举报的回帖",comments);
        }
        //否则，获取所有话题
        List<Comment> comments = commentMapper.selectList(null);
        return new ResponseResult(200,"成功获取所有的回帖",comments);
    }

    //根据ID删除话题
    //---------------------------此处需要鉴权，只有管理员和回帖发布者可以删除话题--------------------
    @Override
    public ResponseResult deleteCommentById(Long id) {
        Comment comment = commentMapper.selectById(id);
        if(comment==null){
            return new ResponseResult(ResponseCode.NO_COMMENT_LOG.getCode(), "该回帖不存在");
        }
        commentMapper.deleteById(comment);
        return new ResponseResult(2,"回帖删除成功");
    }

    //根据设置回帖为楼主
    //-----------------------------此处需要鉴权，只有话题所有者可以设置楼主----------------------------------------------------
    @Override
    public ResponseResult setLandlordById(Long id) {
        //一：将原本的楼主撤销、
        Comment comment = commentMapper.selectById(id);   //找到该帖子
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("to_id",comment.getToId())
                .eq("is_landlord",true);
        Comment proComment = commentMapper.selectOne(wrapper);
        proComment.setLandlord(false);
        commentMapper.updateById(proComment);
        //二：将这条帖子设置为楼主
        comment.setLandlord(true);
        commentMapper.updateById(comment);
        return new ResponseResult(200,"楼主已设置成功");
    }

}
