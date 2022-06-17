package com.lqs.authapi.handler;

import com.alibaba.fastjson.JSON;
import com.lqs.authapi.constant.REnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import utils.R;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        response.setContentType("application/json;charset=utf-8");

        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter writer = response.getWriter();

        writer.write(JSON.toJSONString(R.ok(REnum.LOGIN_FAIL.getStatusCode(), REnum.LOGIN_FAIL.getStatusMsg())));

    }
}
