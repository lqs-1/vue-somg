package com.lqs.yebapi.constant;


/**
 * @author : 李奇凇
 * @date : 2022/5/5 8:46
 * @do : 相应对象默认状态码和状态信息的枚举，
 *          SUCCESS和FAIL是通用的，
 *          后面的1开头的都是成功,2开头都是失败
 */
public enum REnum {

    // 用于不覆写，通用
    SUCCESS(0, "success"),
    // 用于不覆写，通用
    FAIL(1, "fail"),
    WELCOME_MAIN(11000, "欢迎来到主页"),
    PLEASE_LOGIN(21000, "请先登录"),
    DATABASE_ERROR(21001, "数据库链接错误"),
    VALIDATE_CODE_ERROR(21002, "验证码错误"),
    USER_DOES_NOT_EXIST(21003, "用户不存在"),
    USER_PASSWORD_ERROR(21004, "用户密码错误"),
    LOGIN_SUCCESS(11001, "登录成功"),
    LOGOUT_SUCCESS(11002, "注销成功"),
    ADD_USER_SUCCESS(11003, "用户添加成功"),
    ADD_USER_FAIL(21005, "用户添加失败"),
    EDIT_USER_SUCCESS(11004, "用户修改成功"),
    EDIT_USER_FAIL(21006, "用户修改失败"),
    DELETE_USER_SUCCESS(11005, "删除用户成功"),
    DELETE_USER_FAIL(21007, "删除用户失败"),
    GET_USER_LIST_SUCCESS(11006, "获取用户列表成功"),
    GET_USER_LIST_FAIL(21008, "获取用户列表失败"),
    USER_PERMISSIONS_ERROR(21009, "权限不足"),
    LOGIN_FAIL(21010, "登录失败"),
    REGISTER_SUCCESS(11007, "用户注册成功"),
    REGISTER_FAIL(21011, "用户注册失败"),
    ALTER_PASSWORD_SUCCESS(11008, "用户密码修改成功"),
    ALTER_PASSWORD_FAIL(21012, "用户密码修改失败")
    ;


    private Integer statusCode;
    private String statusMsg;

    REnum(Integer statusCode, String statusMsg){
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }
}