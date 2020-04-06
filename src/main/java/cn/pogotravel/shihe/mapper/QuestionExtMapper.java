package cn.pogotravel.shihe.mapper;

import cn.pogotravel.shihe.dto.QuestionQueryDTO;
import cn.pogotravel.shihe.model.Question;
import cn.pogotravel.shihe.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);
    int incCommentCount(Question record);
    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
//    int incView(Question record, @Param("example") QuestionExample example);
}