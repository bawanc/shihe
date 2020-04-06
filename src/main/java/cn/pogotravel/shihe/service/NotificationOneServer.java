package cn.pogotravel.shihe.service;

import cn.pogotravel.shihe.dto.NotificationOneDTO;
import cn.pogotravel.shihe.dto.PaginationDTO;
import cn.pogotravel.shihe.dto.QuestionDTO;
import cn.pogotravel.shihe.enums.NotificationStatusEnum;
import cn.pogotravel.shihe.enums.NotificationTypeEnum;
import cn.pogotravel.shihe.exception.CustomizeErrorCode;
import cn.pogotravel.shihe.exception.CustomizeException;
import cn.pogotravel.shihe.mapper.NotificationOneMapper;
import cn.pogotravel.shihe.mapper.UserMapper;
import cn.pogotravel.shihe.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Notification;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationOneServer {

    @Autowired
    private NotificationOneMapper notificationOneMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Long id, Integer page, Integer size) {
        PaginationDTO<NotificationOneDTO> paginationDTO = new PaginationDTO();
        Integer totalPage;

        NotificationOneExample notificationOne = new NotificationOneExample();
        notificationOne.createCriteria().andReceiverEqualTo(id);
        Integer totalCount = (int)notificationOneMapper.countByExample(notificationOne);


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

        NotificationOneExample example = new NotificationOneExample();
        example.createCriteria().andReceiverEqualTo(id);
        example.setOrderByClause("gmtcreat desc");
        List<NotificationOne> notificationOnes = notificationOneMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));

        if(notificationOnes.size()==0){
            return paginationDTO;
        }


//        Set<Long> disUserIds = notificationOnes.stream().map(notify -> notify.getNotifier()).collect(Collectors.toSet());
//        List<Long> userIds = new ArrayList<>(disUserIds);
//        UserExample userExample =new UserExample();
//        userExample.createCriteria().andIdIn(userIds);
//        List<User> users = userMapper.selectByExample(userExample);
//        Map<Long,User> userMap=users.stream().collect(Collectors.toMap(u->u.getId(),u->u));

        List<NotificationOneDTO> notificationOneDTOS = new ArrayList<>();

        for (NotificationOne notification:notificationOnes) {
            NotificationOneDTO notificationOneDTO=new NotificationOneDTO();
            BeanUtils.copyProperties(notification,notificationOneDTO);
            notificationOneDTO.setTypename(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationOneDTOS.add(notificationOneDTO);
        }

        paginationDTO.setData(notificationOneDTOS);
        return paginationDTO;
    }

    public Long unreadCount(Long id) {
        NotificationOneExample example = new NotificationOneExample();
        example.createCriteria().andReceiverEqualTo(id).andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return  notificationOneMapper.countByExample(example);
    }

    public NotificationOneDTO read(Long id, User user) {
        NotificationOne notificationOne = notificationOneMapper.selectByPrimaryKey(id);
        if(notificationOne==null){
            throw  new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if(!Objects.equals(notificationOne.getReceiver(), user.getId())){
            throw  new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        notificationOne.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationOneMapper.updateByPrimaryKey(notificationOne);
        NotificationOneDTO notificationOneDTO=new NotificationOneDTO();
        BeanUtils.copyProperties(notificationOne,notificationOneDTO);
        notificationOneDTO.setTypename(NotificationTypeEnum.nameOfType(notificationOne.getType()));
        return notificationOneDTO;
    }
}
