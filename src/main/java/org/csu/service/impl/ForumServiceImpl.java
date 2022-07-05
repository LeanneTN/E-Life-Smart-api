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
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.stereotype.Service;

import javax.management.QueryEval;
import java.util.List;
import java.util.Map;

@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private CommentMapper commentMapper;

    //创建话题
    @Override
    public ResponseResult createTopic(Topic topic) {
        topicMapper.insert(topic);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(),"话题创建成功");
    }

    //根据ID，获取话题
    @Override
    public ResponseResult getTopicById(long id) {
        Topic topic = topicMapper.selectById(id);
        if(topic==null){
            return new ResponseResult(ResponseCode.NO_TOPIC_LOG.getCode(), "该话题不存在");
        }
        return new ResponseResult(200,"获取话题成功",topic);
    }
    //根据ID，获得话题所拥有的回帖
    @Override
    public ResponseResult getTopic_CommentsById(long id) {
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
    public ResponseResult getTopicByStatus(int status) {
        //0：未被举报的话题。1：被举报的话题
        if(status==0 || status==1){
            QueryWrapper<Topic> wrapper = new QueryWrapper<>();
            wrapper.eq("status",status);
            List<Topic> topics = topicMapper.selectList(wrapper);
            return new ResponseResult(200,"成功获取话题",topics);
        }
        //否则，获取所有话题
        List<Topic> topics = topicMapper.selectList(null);
        return new ResponseResult(200,"成功获取所有的话题",topics);
    }

    //找到某一个用户创建的所有话题
    @Override
    public ResponseResult getTopicsByUser(long id) {
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
        //修改话题的状态，改为正在审核中
        topic.setStatus(1);
        topicMapper.updateById(topic);
        return new ResponseResult(2,"您已举报成功");
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


    //提交回帖
    @Override
    public ResponseResult createComment(Comment comment) {
        //一：判断该回帖是不是话题的第一个帖子
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("to_id",comment.getToId());
        List<Comment> comments = commentMapper.selectList(wrapper);
        if(comments.size()==0){ //如果暂时没有，说明这是第一个帖子，设为楼主
            comment.setLandlord(true);
        }
        this.commentMapper.insert(comment);
        return new ResponseResult(200,"评论提交成功");
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
    public ResponseResult getCommentsByUser(long id) {
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
        comment.setStatus(1);
        commentMapper.updateById(comment);
        return null;
    }

    //获取指定状态的回帖
    @Override
    public ResponseResult getCommentByStatus(int status) {
        //0：未被举报的回帖。1：被举报的回帖
        if(status==0 || status==1){
            QueryWrapper<Comment> wrapper = new QueryWrapper<>();
            wrapper.eq("status",status);
            List<Comment> comments = commentMapper.selectList(wrapper);
            return new ResponseResult(200,"成功获取回帖",comments);
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
        topicMapper.deleteById(id);
        return new ResponseResult(2,"回帖删除成功");
    }

    //根据设置回帖为楼主
    //-----------------------------此处需要鉴权，只有话题所有者可以设置楼主----------------------------------------------------
    @Override
    public ResponseResult setLandlordById(long id) {
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
