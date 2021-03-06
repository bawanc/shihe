package cn.pogotravel.shihe.controller;

import cn.pogotravel.shihe.dto.PaginationDTO;
import cn.pogotravel.shihe.mapper.UserMapper;
import cn.pogotravel.shihe.model.User;
import cn.pogotravel.shihe.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/index1")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        @RequestParam(name = "search",required = false) String search
                        ) {
        PaginationDTO pagination= questionService.list(search,page,size);
        model.addAttribute("pagination",pagination);
        model.addAttribute("search",search);

        return "index";
    }
    @GetMapping("/")
    public String login() {

        return "login";
    }
}
