package cn.pogotravel.shihe.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你的问题不在了，要不换一个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选择任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登陆，请登录后重试！"),
    SYS_ERROR(2004,"服务已经不行了，稍后再试试。。。。。"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或者不存在！"),
    COMMENT_NOT_FOUND(2006,"回复的评论不在了，要不换一个试试？"),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空！");

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private String message;
    private Integer code;
    //ctrl+F6 改变参数位置
    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
