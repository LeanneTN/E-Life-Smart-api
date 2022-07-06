package org.csu.service;

import org.csu.domain.Comment;
import org.csu.vo.ResponseResult;
import org.csu.domain.Topic;

public interface ForumService {
    //创建话题
    ResponseResult createTopic(Topic topic);
    //根据ID，获取话题
    ResponseResult getTopicById(long id);
    //根据ID，获取话题，以及话题包含的帖子
    ResponseResult getTopic_CommentsById(long id);
    //根据ID，举报话题
    ResponseResult reportTopicById(Long id);
    //根据状态（未被举报：0、被举报：1、所有：其它），找到话题
    ResponseResult getTopicByStatus(boolean isReported);
    //根据用户ID，找到该用户创建的所有话题
    ResponseResult getTopicsByUser(long id);
    //根据ID，删除话题
    ResponseResult deleteTopicById(Long id);

//    以下为回帖相关操作
    //创建消息
    ResponseResult createComment(Comment comment);
    //根据ID查找回帖
    ResponseResult getCommentById(Long id);
    //根据用户ID，找到该用户创建的所有回帖
    ResponseResult getCommentsByUser(long id);
    //根据ID，查找所有跟它的toId相同的回帖
    //根据ID，举报回帖
    ResponseResult reportCommentById(Long id);
    //根据状态（未被举报：0、被举报：1、所有：其它），找到回帖
    ResponseResult getCommentByStatus(boolean isReported);
    //根据ID，删除回帖
    ResponseResult deleteCommentById(Long id);
    //根据ID将回帖设为楼主
    ResponseResult setLandlordById(long id);


}
