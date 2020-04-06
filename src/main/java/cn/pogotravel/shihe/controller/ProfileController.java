package cn.pogotravel.shihe.controller;

import cn.pogotravel.shihe.dto.NotificationOneDTO;
import cn.pogotravel.shihe.dto.PaginationDTO;
import cn.pogotravel.shihe.mapper.UserMapper;
import cn.pogotravel.shihe.model.NotificationOne;
import cn.pogotravel.shihe.model.User;
import cn.pogotravel.shihe.service.NotificationOneServer;
import cn.pogotravel.shihe.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationOneServer notificationOneServer;

    @GetMapping("/profile/{action}")
    private String profile(HttpServletRequest request,
                           @PathVariable(name = "action") String action, Model model,
                           @RequestParam(name = "page", defaultValue = "1") Integer page,
                           @RequestParam(name = "size", defaultValue = "2") Integer size) {


        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }


        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            PaginationDTO list = questionService.list(user.getId(), page, size);
            model.addAttribute("pagination", list);
        } else if ("replies".equals(action)) {
            PaginationDTO list = notificationOneServer.list(user.getId(), page, size);
            Long unreadCount = notificationOneServer.unreadCount(user.getId());
            model.addAttribute("section", "replies");
            model.addAttribute("pagination", list);
            model.addAttribute("sectionName", "最新回复");
        }

        return "profile";
    }

}
