package cn.pogotravel.shihe.dto;

import cn.pogotravel.shihe.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtcreate;
    private Long gmtmodified;
    private String avatarUrl;
    private Long creator;
    private Integer viewcount;
    private Integer commentcount;
    private Integer likecount;
    private User user;
}
