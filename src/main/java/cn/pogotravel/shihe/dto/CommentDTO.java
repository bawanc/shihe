package cn.pogotravel.shihe.dto;

import cn.pogotravel.shihe.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long parentid;
    private Integer type;
    private Long commentator;
    private Long gmtcreate;
    private Long gmtmodified;
    private Long commentCount;
    private Long likecount;
    private String content;
    private User user;
}
