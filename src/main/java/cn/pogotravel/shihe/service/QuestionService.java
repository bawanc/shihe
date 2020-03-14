package cn.pogotravel.shihe.service;

import cn.pogotravel.shihe.dto.PaginationDTO;
import cn.pogotravel.shihe.dto.QuestionDTO;
import cn.pogotravel.shihe.exception.CustomizeErrorCode;
import cn.pogotravel.shihe.exception.CustomizeException;
import cn.pogotravel.shihe.mapper.QuestionMapper;
import cn.pogotravel.shihe.mapper.UserMapper;
import cn.pogotravel.shihe.model.Question;
import cn.pogotravel.shihe.model.QuestionExample;
import cn.pogotravel.shihe.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;

        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());


        if (totalCount %size==0) {
            totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }



        if (page<1)
            page=1;
        if (page>totalPage) {
            page=totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        Integer offset;
        if (page!=0)
          offset = size * (page-1);
        else
            offset=0;
//        List<Question> questions = questionMapper.list(offset,size);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        for (Question question: questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(questionExample);


        if (totalCount %size==0) {
            totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }



        if (page<1)
            page=1;
        if (page>totalPage) {
            page=totalPage;
        }
        paginationDTO.setPagination(totalPage,page);


        Integer offset =0;
        if(page!=0){
            offset = size * (page-1);
        }else {
            offset = 0;
        }
//        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));


        for (Question question: questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question=questionMapper.selectByPrimaryKey(id);
        if(question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;

    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            question.setGmtcreate(System.currentTimeMillis());
            question.setGmtmodified(question.getGmtcreate());
            questionMapper.insert(question);
        }else{
            Question updateQuestion = new Question();
            updateQuestion.setGmtmodified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
            if(updated!=1)
            {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
}
