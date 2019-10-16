package cn.pogotravel.shihe.controller;

import cn.pogotravel.shihe.dto.AccessTokenDTO;
import cn.pogotravel.shihe.dto.GithubUserDTO;
import cn.pogotravel.shihe.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("github.client.id")
    private String clientid;
    @Value("github.client.secret")
    private String clientsecret;
    @Value("github.redirect.url")
    private String redirecturl;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state") String state ){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientid);
        accessTokenDTO.setClient_secret(clientsecret);
        accessTokenDTO.setRedirect_url(redirecturl);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUserDTO githubUserDTO = githubProvider.gitUser(accessToken);
        System.out.println(githubUserDTO.getName());

        return "index";
    }
}
