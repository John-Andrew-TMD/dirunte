package com.example.yin.controller;

import com.example.yin.common.ErrorMessage;
import com.example.yin.common.SuccessMessage;
import com.example.yin.domain.Comment;
import com.example.yin.service.impl.CommentServiceImpl;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    // 提交评论
    @ResponseBody
    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    public Object addComment(HttpServletRequest req) {
        String user_id = req.getParameter("userId");
        String type = req.getParameter("type");
        String status = req.getParameter("status");
        String song_list_id = req.getParameter("songListId");
        String song_id = req.getParameter("songId");
        String content = req.getParameter("content").trim();

        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(user_id));
        comment.setType(new Byte(type));
        if (new Byte(type) == 0) {
            comment.setSongId(Integer.parseInt(song_id));
        } else if (new Byte(type) == 1) {
            comment.setSongListId(Integer.parseInt(song_list_id));
        }
        comment.setContent(content);
        comment.setStatus(new Byte(status));
        comment.setCreateTime(new Date());
        boolean res = commentService.addComment(comment);
        if (res) {
            return new SuccessMessage<Null>("评论成功").getMessage();
        } else {
            return new ErrorMessage("评论失败").getMessage();
        }
    }

    // 删除评论
    @RequestMapping(value = "/comment/delete", method = RequestMethod.GET)
    public Object deleteComment(HttpServletRequest req) {
        String id = req.getParameter("id");

        boolean res = commentService.deleteComment(Integer.parseInt(id));
        if (res) {
            return new SuccessMessage<Null>("删除成功").getMessage();
        } else {
            return new ErrorMessage("删除失败").getMessage();
        }
    }
    // 更新评论状态
    @ResponseBody
    @RequestMapping(value = "/comment/update", method = RequestMethod.POST)
    public Object updateComment(HttpServletRequest req) {
        String id = req.getParameter("id");
        String status = req.getParameter("status");
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setStatus(new Byte(status));
        boolean result = commentService.updateCommentMsg(comment);
        if (result) {
            return new SuccessMessage<Null>("用户评论修改成功").getMessage();
        } else {
            return new ErrorMessage("修改失败").getMessage();
        }
    }
    // 获得指定歌曲 ID 的评论列表
    @RequestMapping(value = "/comment/song/detail", method = RequestMethod.GET)
    public Object commentOfSongId(HttpServletRequest req) {
        String songId = req.getParameter("songId");

        return new SuccessMessage<List<Comment>>(null, commentService.commentOfSongId(Integer.parseInt(songId))).getMessage();
    }
    // 获得指定歌曲 ID 的评论列表
//    @PostMapping(value = "/comment/detail")
    @ResponseBody
    @RequestMapping(value = "/comment/detail", method = RequestMethod.POST)
    public Object commentOfType(HttpServletRequest req) {
        String type = req.getParameter("type");
        String status = req.getParameter("status");
        Comment comment = new Comment();
        comment.setType(new Byte(type));
        if(status!=null){
            comment.setStatus(new Byte(status));
        }
       return new SuccessMessage<List<Comment>>(null, commentService.commentOfType(comment)).getMessage();
        // boolean result = commentService.commentOfType(comment);
        // if (result) {
        //     return new SuccessMessage<Null>("查询成功").getMessage();
        // } else {
        //     return new ErrorMessage("查询失败").getMessage();
        // }
    }

    // 获得指定歌单 ID 的评论列表
    @RequestMapping(value = "/comment/songList/detail", method = RequestMethod.GET)
    public Object commentOfSongListId(HttpServletRequest req) {
        String songListId = req.getParameter("songListId");

        return new SuccessMessage<List<Comment>>(null, commentService.commentOfSongListId(Integer.parseInt(songListId)))
                .getMessage();
    }

    // 点赞
    @ResponseBody
    @RequestMapping(value = "/comment/like", method = RequestMethod.POST)
    public Object commentOfLike(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String up = req.getParameter("up").trim();

        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));

        boolean res = commentService.updateCommentMsg(comment);
        if (res) {
            return new SuccessMessage<Null>("点赞成功").getMessage();
        } else {
            return new ErrorMessage("点赞失败").getMessage();
        }
    }
}
