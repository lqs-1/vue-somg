package com.lqs.authapi.handler;

import com.alibaba.fastjson.JSON;
import com.lqs.authapi.constant.REnum;
import org.springframework.security.core.Authentication;
import utils.R;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.setContentType("application/json;charset=utf-8");

        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter writer = response.getWriter();

        writer.write(JSON.toJSONString(R.ok(REnum.LOGOUT_SUCCESS.getStatusCode(), REnum.LOGOUT_SUCCESS.getStatusMsg())));

    }
}
