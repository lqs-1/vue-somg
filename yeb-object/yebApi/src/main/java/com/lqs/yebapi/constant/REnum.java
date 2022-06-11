package com.lqs.yebapi.constant;


/**
 * @author : 李奇凇
 * @date : 2022/5/5 8:46
 * @do : 相应对象默认状态码和状态信息的枚举，
 *          SUCCESS和FAIL是通用的，
 *          后面的都是独立的前缀为1表示成功，前缀为0表示失败，且10000和00001是一对
 */
public enum REnum {

    // 用于不覆写，通用
    SUCCESS(0, "success"),
    // 用于不覆写，通用
    FAIL(1, "fail"),
    WELCOME_MAIN(11000, "欢迎来到主页"),

    LOGIN_SUCCESS(10000, "登录成功"),
    LOGIN_FAIL(00001, "登录失败");


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