package org.csu.controller;

import org.csu.domain.Comment;
import org.csu.vo.ResponseResult;
import org.csu.domain.Topic;
import org.csu.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forum/")
public class ForumController {
    @Autowired
    private ForumService forumService;

    //创建帖子
    @PostMapping("/topic")
    public ResponseResult createTopic(@RequestBody Topic topic){
        forumService.createTopic(topic);
        return forumService.createTopic(topic);
    }

    //获取某个帖子以及下面的所有回复
    @GetMapping("/topic/{id}")
    public ResponseResult getTopicById(@PathVariable("id") Long id){
        return forumService.getTopicById();
    }

    //进行回复
    @PostMapping("/comment")
    public ResponseResult comment(@RequestBody Comment comment){
        return forumService.comment(comment);
    }

    //对帖子进行举报
    @PostMapping("/report/topic/{id}")
    public ResponseResult reportTopicById(@PathVariable("id") Long id){
        return forumService.reportTopicById(id);
    }

    //对评论进行举报
    @PostMapping("/report/comment/{id}")
    public ResponseResult reportCommentById(@PathVariable("id") Long id){
        return forumService.reportCommentById(id);
    }

    //获取所有被举报的帖子
    @GetMapping("/report")
    @PreAuthorize("hasAuthority('system:forum:report')")
    public ResponseResult getTopicReported(){
        return forumService.getTopicReported();
    }

    //获取所有被举报的评论
    @GetMapping("/comment")
    @PreAuthorize("hasAuthority('system:forum:report')")
    public ResponseResult getCommentReported(){
        return forumService.getCommentReported();
    }
}
