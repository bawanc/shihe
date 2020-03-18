package cn.pogotravel.shihe.controller;

import cn.pogotravel.shihe.dto.CommentDTO;
import cn.pogotravel.shihe.dto.ResultDTO;
import cn.pogotravel.shihe.exception.CustomizeErrorCode;
import cn.pogotravel.shihe.mapper.CommentMapper;
import cn.pogotravel.shihe.model.Comment;
import cn.pogotravel.shihe.model.User;
import cn.pogotravel.shihe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public  Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user ==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentid(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtmodified(System.currentTimeMillis());
        comment.setGmtcreate(System.currentTimeMillis());
        comment.setLikecount(0L);
        commentService.insert(comment);
        comment.setCommentator(user.getId());
        comment.setCommentator(1l);
        commentMapper.insert(comment);

        return ResultDTO.okOf();
    }
}
