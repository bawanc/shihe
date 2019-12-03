package cn.pogotravel.shihe.provider;

import cn.pogotravel.shihe.dto.AccessTokenDTO;
import cn.pogotravel.shihe.dto.GithubUserDTO;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();

//            Request request = new Request.Builder()
//                    .get()
//                .url("https://github.com/login/oauth/access_token?client_id="+
//                        accessTokenDTO.getClient_id()+"?client_secret="+accessTokenDTO.getClient_secret()+
//                        "?code="+accessTokenDTO.getCode())
//                .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                System.out.println(string);
                String[]  split = string.split("&");
                String tokenstr = split[0];
                String token = tokenstr.split("=")[1];
                return token;

            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }
    public GithubUserDTO gitUser(String accessToken){
        OkHttpClient client=new OkHttpClient();
        Request request= new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUserDTO githubUserDTO = JSON.parseObject(string, GithubUserDTO.class);
            return githubUserDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
