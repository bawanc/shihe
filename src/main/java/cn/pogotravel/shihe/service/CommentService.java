package cn.pogotravel.shihe.service;

import cn.pogotravel.shihe.dto.CommentDTO;
import cn.pogotravel.shihe.enums.CommentTypeEnum;
import cn.pogotravel.shihe.exception.CustomizeErrorCode;
import cn.pogotravel.shihe.exception.CustomizeException;
import cn.pogotravel.shihe.mapper.*;
import cn.pogotravel.shihe.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;

    @Transactional
    public void insert(Comment comment) {
        if(comment.getParentid()==null||comment.getParentid()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType()==null||!CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment=commentMapper.selectByPrimaryKey(comment.getParentid());
            if(dbComment==null)
            {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);

            //增加评论数
            Comment parentComment = new Comment();
            parentComment.setParentid(comment.getParentid());
            parentComment.setCommentcount(1);
            commentExtMapper.incCommentCount(parentComment);
//            CommentDTO parentComment = new CommentDTO();
//            parentComment.setParentid(comment.getParentid());
//            parentComment.setCommentcount(1);
//            commentExtMapper.incCommentCount(parentComment);

        }else{
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentid());
            if(question==null)
            {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentcount(1);
            questionExtMapper.incCommentCount(question);

        }
    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();

        commentExample.createCriteria().andParentidEqualTo(id).
                andTypeEqualTo(type.getType());//type ctrl+alt+p
        //commentExample.setOrderByClause("gmtcreate desc");
        List<Comment> comments=commentMapper.selectByExample(commentExample);
        if(comments.size()==0){
            return new ArrayList<>();
        }
        //获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> users=new ArrayList<>();
        users.addAll(commentators);

        //获取评论人并转换为Map
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(users);
        List<User> users1 = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users1.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //转换comment为commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}
