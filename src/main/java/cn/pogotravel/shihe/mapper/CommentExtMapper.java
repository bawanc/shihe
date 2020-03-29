package cn.pogotravel.shihe.mapper;

import cn.pogotravel.shihe.model.Comment;
import cn.pogotravel.shihe.model.CommentExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment record);
}