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
import redis.clients.jedis.JedisPool;
import utils.Pagination.PageUtils;
import utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
            HttpServletRequest httpServletRequest) throws IOException {

        httpServletResponse.setContentType("image/png");

        // 三个参数分别为宽、高、位数
        SpecCaptcha captcha = new SpecCaptcha(120, 30, 4);

        // 设置类型 数字和字母混合
        captcha.setCharType(Captcha.TYPE_DEFAULT);

        //设置字体
        captcha.setCharType(Captcha.FONT_9);

        // 将文本存储到redis
        // Jedis jedis = jedisPool.getResource();
        // jedis.setex(username,300, captcha.text());
        httpServletRequest.getSession().setAttribute("code", captcha.text());

        // 输出图片流
        captcha.out(httpServletResponse.getOutputStream());
    }


    /**
     * 方法作废用spring-security
     * @param request
     * @param httpServletRequest
     * @param userLoginVo
     * @return
     * @throws Exception
     */
    @PostMapping("login")
    public R login(
            HttpServletRequest request,
            @RequestBody UserLoginVo userLoginVo) throws Exception {

        // 从redis中取出验证码
        // Jedis jedis = jedisPool.getResource();
        // String code = jedis.get(userLoginVo.getUsername());

        String code = (String) request.getSession().getAttribute("code");

        if (code.toLowerCase().equals(userLoginVo.getCode().toLowerCase())){
            try{
                    User user = new User();
                    BeanUtils.copyProperties(userLoginVo,user);
                    R result = userService.getUser(user, request);
                    return result;

            }catch (Exception e){
                e.printStackTrace();
                return R.error(REnum.DATABASE_ERROR.getStatusCode(),
                        REnum.DATABASE_ERROR.getStatusMsg());
            }
        }else {
            return R.error(REnum.VALIDATE_CODE_ERROR.getStatusCode(),
                    REnum.VALIDATE_CODE_ERROR.getStatusMsg());
        }
    }

    @GetMapping("session")
    public R session(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");

        if (user == null){
            return R.error(REnum.PLEASE_LOGIN.getStatusCode(),
                    REnum.PLEASE_LOGIN.getStatusMsg());
        }

        return R.ok(REnum.WELCOME_MAIN.getStatusCode(),
                REnum.WELCOME_MAIN.getStatusMsg())
                .put("user", user);
    }


    @GetMapping("cleanSession")
    public R cleanSession(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().removeAttribute("user");
        return R.ok(REnum.LOGOUT_SUCCESS.getStatusCode(),
                REnum.LOGOUT_SUCCESS.getStatusMsg());
    }


    @GetMapping("userList")
    public R getUserList(){
        try{
            List<User> userList = userService.getUserList();
            return R.ok(REnum.GET_USER_LIST_SUCCESS.getStatusCode(),
                    REnum.GET_USER_LIST_SUCCESS.getStatusMsg())
                    .put("userList", userList);

        }catch (Exception e){
            e.printStackTrace();
            return R.error(REnum.GET_USER_LIST_FAIL.getStatusCode(),
                    REnum.GET_USER_LIST_FAIL.getStatusMsg());
        }
    }


//    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("userPage")
    public R getUserPage(@RequestParam Map<String, Object> params){
        try{

            // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // System.out.println(authentication.getPrincipal());
            PageUtils userList = userService.getUserPage(params);
            return R.ok(REnum.GET_USER_LIST_SUCCESS.getStatusCode(),
                            REnum.GET_USER_LIST_SUCCESS.getStatusMsg())
                    .put("userList", userList);

        }catch (Exception e){
            e.printStackTrace();
            return R.error(REnum.GET_USER_LIST_FAIL.getStatusCode(),
                    REnum.GET_USER_LIST_FAIL.getStatusMsg());
        }
    }


    @PostMapping("addUser")
    public R addUser(@RequestBody User user){
        try{
            userService.addUser(user);
            return R.ok(REnum.ADD_USER_SUCCESS.getStatusCode(),
                    REnum.ADD_USER_SUCCESS.getStatusMsg());

        }catch (Exception e){
            e.printStackTrace();
            return R.error(REnum.ADD_USER_FAIL.getStatusCode(),
                    REnum.ADD_USER_FAIL.getStatusMsg());
        }
    }

    @PostMapping("editUser")
    public R editUser(@RequestBody User user){
        try{
            userService.editUser(user);
            return R.ok(REnum.EDIT_USER_SUCCESS.getStatusCode(),
                    REnum.EDIT_USER_SUCCESS.getStatusMsg());

        }catch (Exception e){
            e.printStackTrace();
            return R.error(REnum.EDIT_USER_FAIL.getStatusCode(),
                    REnum.EDIT_USER_FAIL.getStatusMsg());
        }
    }


    @PostMapping("deleteUser")
    public R deleteUser(@RequestBody User user){
        try{
            userService.deleteUserById(user.getId());
            return R.ok(REnum.DELETE_USER_SUCCESS.getStatusCode(),
                    REnum.DELETE_USER_SUCCESS.getStatusMsg());

        }catch (Exception e){
            e.printStackTrace();
            return R.error(REnum.DELETE_USER_FAIL.getStatusCode(),
                    REnum.DELETE_USER_FAIL.getStatusMsg());
        }
    }






}
