package cn.pogotravel.shihe.controller;

import cn.pogotravel.shihe.dto.CommentCreateDTO;
import cn.pogotravel.shihe.dto.CommentDTO;
import cn.pogotravel.shihe.dto.ResultDTO;
import cn.pogotravel.shihe.enums.CommentTypeEnum;
import cn.pogotravel.shihe.exception.CustomizeErrorCode;
import cn.pogotravel.shihe.mapper.CommentMapper;
import cn.pogotravel.shihe.model.Comment;
import cn.pogotravel.shihe.model.User;
import cn.pogotravel.shihe.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public  Object post(@RequestBody CommentCreateDTO commentDTO, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user ==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if(commentDTO==null || StringUtils.isBlank(commentDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentid(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtmodified(System.currentTimeMillis());
        comment.setGmtcreate(System.currentTimeMillis());
        comment.setLikecount(0L);
        //commentService.insert(comment);
//        comment.setCommentator(user.getId());
        comment.setCommentator(user.getId());
        commentMapper.insert(comment);
        return ResultDTO.okOf();
    }
    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name="id") Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);//ctrl+F6可以改变调用方法传入的类型 shift+F6可以改变所有的名字
        return ResultDTO.okOf(commentDTOS);
    }
}
