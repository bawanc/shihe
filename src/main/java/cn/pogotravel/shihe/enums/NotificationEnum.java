package cn.pogotravel.shihe.enums;

public enum NotificationEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论");
    private  int type;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    private String name;

    NotificationEnum(int status, String name) {
        this.type = status;
        this.name = name;
    }
}
