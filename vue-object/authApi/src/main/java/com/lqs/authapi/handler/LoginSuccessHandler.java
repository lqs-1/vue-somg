package com.lqs.authapi.handler;

import com.alibaba.fastjson.JSON;
import com.lqs.authapi.constant.REnum;
import com.lqs.authapi.domain.MyUser;
import com.lqs.authapi.domain.User;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import utils.R;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    // private String url;

    // public LoginSuccessHandler(String url){
    //     this.url = url;
    // }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 获取客户端ip
        // System.out.println(request.getRemoteAddr());

        // 获取SpringSecurity里面的User对象
        // User user = (User) authentication.getPrincipal();
        // 获取权限列表
        // System.out.println(user.getAuthorities());
        //  获取用户名
        // System.out.println(user.getUsername());
        // 获取用户密码，null，为了安全
        // System.out.println(user.getPassword());
        // response.sendRedirect(this.url);

        response.setContentType("application/json;charset=utf-8");

        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter writer = response.getWriter();

        String code = (String) request.getSession().getAttribute("code");


        if (code.toLowerCase().equals((String) request.getParameter("code").toLowerCase())){
            // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // System.out.println(authentication.getPrincipal());

            MyUser principal = (MyUser) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

            User user = new User();

            BeanUtils.copyProperties(principal, user);

            request.getSession().setAttribute("user", user);

            writer.write(JSON.toJSONString(R
                    .ok(REnum.LOGIN_SUCCESS.getStatusCode(),
                    REnum.LOGIN_SUCCESS.getStatusMsg())));

        }else {

            writer.write(JSON.toJSONString(R.error(REnum.VALIDATE_CODE_ERROR.getStatusCode(),
                    REnum.VALIDATE_CODE_ERROR.getStatusMsg())));
        }

        writer.flush();

    }
}
