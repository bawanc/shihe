package cn.pogotravel.shihe.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtcreate;
    private Long gmtmodified;
    private String avatarUrl;
    private Integer creator;
    private Integer viewcount;
    private Integer commentcount;
    private Integer likecount;
}
