//package com.lqs.yebapi.config;
//
//import com.lqs.yebapi.handler.LoginFailHandler;
//import com.lqs.yebapi.handler.LoginSuccessHandler;
//import com.lqs.yebapi.handler.LogoutSuccessHandler;
//import com.lqs.yebapi.handler.MyAccessDeniedHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//
//        http.formLogin()
//                .failureHandler(new LoginFailHandler())
//                .successHandler(new LoginSuccessHandler())
//                .loginProcessingUrl("/user/login");
//        http.authorizeRequests()
//                .anyRequest().authenticated();
//
//        http.exceptionHandling() // 自定义权限受限403处理
//                .accessDeniedHandler(new MyAccessDeniedHandler()); // 处理器模式，自由度更广
//        // .accessDeniedPage("/forbidden.html"); // 直接返回一个页面。不和上面的同时使用
//
//
//
//        http.logout().logoutUrl("/user/logout").logoutSuccessHandler(new LogoutSuccessHandler());
//    }
//}
