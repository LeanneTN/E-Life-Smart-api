package org.csu.controller;

import org.csu.domain.Comment;
import org.csu.vo.ResponseCode;
import org.csu.vo.ResponseResult;
import org.csu.domain.Topic;
import org.csu.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.server.header.ReferrerPolicyServerHttpHeadersWriter;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.PublicKey;

@RestController
@RequestMapping("/api/forum/")
public class ForumController {
    @Autowired
    private ForumService forumService;

    //创建话题
    @PostMapping("/topic")
    public ResponseResult createTopic(@RequestBody Topic topic){
        return forumService.createTopic(topic);
    }

    //获取所有的话题
    @GetMapping("/topic")
    public ResponseResult getAllTopic(){
        return forumService.getAllTopic();
    }

    //根据关键词查询话题
    @GetMapping("/topic/search")
    public ResponseResult getTopicByKeywords(@RequestParam("keywords") String keywords){
        return forumService.getTopicByKeywords(keywords);
    }

    //根据话题ID，获得某个话题下的所有回帖
    @GetMapping("/topic/{id}")
    public ResponseResult getTopicById(@PathVariable("id") Long id){
        return forumService.getTopic_CommentsById(id);
    }

    //对话题进行举报
    @PostMapping("/report/topic/{id}")
    public ResponseResult reportTopicById(@PathVariable("id") Long id){
        return forumService.reportTopicById(id);
    }

    //获取所有被举报的话题
    @GetMapping("/report/topics")
    @PreAuthorize("hasAuthority('system:forum:report')")
    public ResponseResult getTopicReported(){
        return forumService.getTopicByStatus(true);
    }

    //根据ID，获取某个用户所创建的所有话题
    @GetMapping("/user/topics/{id}")
    public ResponseResult getTopicsByUserId(@PathVariable("id") long id){
        return forumService.getTopicsByUser(id);
    }

    //根据ID删除话题：
    @DeleteMapping("/topic/{id}")
    public ResponseResult deleteTopicById(@PathVariable("id") Long id){
        return forumService.deleteTopicById(id);
    }

    //创建回帖
    @PostMapping("/comment")
    public ResponseResult comment(@RequestBody Comment comment){
        return forumService.createComment(comment);
    }

    //根据ID获取回帖
    @GetMapping("/coment/{id}")
    public ResponseResult getCommentById(@PathVariable("id") long id){
        return forumService.getCommentById(id);
    }

    //根据ID，获取某个用户所创建的所有回帖
    @GetMapping("/user/comments/{id}")
    public ResponseResult getCommentsByUserId(@PathVariable("id") long id){
        return forumService.getCommentsByUser(id);
    }

    //对回帖进行举报
    @PostMapping("/report/comment/{id}")
    public ResponseResult reportCommentById(@PathVariable("id") Long id){
        return forumService.reportCommentById(id);
    }

    //获取所有被举报的回帖
    @GetMapping("/report/comments")
    @PreAuthorize("hasAuthority('system:forum:report')")
    public ResponseResult getCommentReported(){
        return forumService.getCommentByStatus(true);
    }

    //根据ID，将某个回帖设置为楼主
    @PostMapping("/comment/{id}/landlord")
    public ResponseResult setLandlordById(@PathVariable("id") long id){
        return forumService.setLandlordById(id);
    }


}
