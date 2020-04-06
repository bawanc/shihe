package cn.pogotravel.shihe.dto;

import cn.pogotravel.shihe.model.User;
import lombok.Data;

@Data
public class NotificationOneDTO {
    private Long id;
    private Integer status;
    private Long gmtcreate;
    private String notifiername;
    private User notifier;
    private  String outertitle;
    private Long outerid;
    private String typename;
    private Integer type;
}
