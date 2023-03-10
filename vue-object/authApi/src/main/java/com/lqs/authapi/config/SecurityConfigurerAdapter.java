package com.lqs.authapi.config;

import com.lqs.authapi.handler.LoginFailHandler;
import com.lqs.authapi.handler.LoginSuccessHandler;
import com.lqs.authapi.handler.LogoutSuccessHandler;
import com.lqs.authapi.handler.MyAccessDeniedHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsUtils;

@Configuration
//@EnableWebSecurity
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.cors(Customizer.withDefaults());

        //解决跨域问题。cors 预检请求放行,让Spring security 放行所有preflight request（cors 预检请求）
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

        http.formLogin()

                .failureHandler(new LoginFailHandler())

                .successHandler(new LoginSuccessHandler())

                .loginProcessingUrl("/user/login");

        http.authorizeRequests()

                .antMatchers("/user/captcha", "/user/register", "/user/alterPwd").permitAll()

                .anyRequest().authenticated();

        http.exceptionHandling() // 自定义权限受限403处理

                .accessDeniedHandler(new MyAccessDeniedHandler()); // 处理器模式，自由度更广

        // .accessDeniedPage("/forbidden.html"); // 直接返回一个页面。不和上面的同时使用

        http.logout().logoutUrl("/user/logout").logoutSuccessHandler(new LogoutSuccessHandler());
    }
}
