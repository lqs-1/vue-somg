package com.lqs.yebapi.controller;

import com.lqs.yebapi.constant.REnum;
import com.lqs.yebapi.domain.User;
import com.lqs.yebapi.service.UserService;
import com.lqs.yebapi.vo.UserLoginVo;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private UserService userService;


    @GetMapping("captcha")
    public void getCaptcha(
            HttpServletResponse httpServletResponse,
            @RequestParam("username") String username) throws IOException {

        httpServletResponse.setContentType("image/png");

        // 三个参数分别为宽、高、位数
        SpecCaptcha captcha = new SpecCaptcha(120, 30, 4);

        // 设置类型 数字和字母混合
        captcha.setCharType(Captcha.TYPE_DEFAULT);

        //设置字体
        captcha.setCharType(Captcha.FONT_9);


        // 将文本存储到redis
         Jedis jedis = jedisPool.getResource();
         jedis.setex(username,300, captcha.text());


        // 输出图片流
        captcha.out(httpServletResponse.getOutputStream());
    }


    @PostMapping("login")
    public R login(HttpServletRequest request, @RequestBody UserLoginVo userLoginVo) throws Exception {

        Jedis jedis = jedisPool.getResource();
        String code = jedis.get(userLoginVo.getUsername());

        User resultUser = null;

        if (code.toLowerCase().equals(userLoginVo.getCode().toLowerCase())){
            User user = new User();
            BeanUtils.copyProperties(userLoginVo,user);
            resultUser = userService.getUser(user);
        }

        if (resultUser != null){
            request.getSession().setAttribute("user", resultUser);
        }

        return R.ok(REnum.LOGIN_SUCCESS.getStatusCode(), REnum.LOGIN_SUCCESS.getStatusMsg());
    }

    @GetMapping("index")
    public R index(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user);

        return R.ok(REnum.WELCOME_MAIN.getStatusCode(), REnum.WELCOME_MAIN.getStatusMsg());
    }





}
