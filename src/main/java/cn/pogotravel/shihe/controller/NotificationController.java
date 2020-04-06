package cn.pogotravel.shihe.controller;

import cn.pogotravel.shihe.dto.NotificationOneDTO;
import cn.pogotravel.shihe.dto.PaginationDTO;
import cn.pogotravel.shihe.enums.NotificationTypeEnum;
import cn.pogotravel.shihe.model.User;
import cn.pogotravel.shihe.service.NotificationOneServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    private NotificationOneServer notificationOneServer;

    @GetMapping("/notification/{id}")
    private String profile(HttpServletRequest request,@PathVariable(name = "id") Long id) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationOneDTO notificationOneDTO = notificationOneServer.read(id,user);
        if(NotificationTypeEnum.REPLY_COMMENT.getType()==notificationOneDTO.getType()
                ||NotificationTypeEnum.REPLY_QUESTION.getType()==notificationOneDTO.getType())
        {
            return "redirect:/question/"+notificationOneDTO.getOuterid();
        }else {
            return "redirect:/";
        }

    }

}
