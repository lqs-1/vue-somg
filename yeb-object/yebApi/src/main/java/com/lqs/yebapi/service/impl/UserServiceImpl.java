package com.lqs.yebapi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqs.yebapi.constant.REnum;
import com.lqs.yebapi.domain.Role;
import com.lqs.yebapi.domain.User;
import com.lqs.yebapi.mapper.UserMapper;
import com.lqs.yebapi.service.RoleService;
import com.lqs.yebapi.service.UserRoleService;
import com.lqs.yebapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Pagination.PageUtils;
import utils.Pagination.QueryPage;
import utils.R;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService/*UserDetailsService */{

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    @Override
    public R getUser(User user, HttpServletRequest request) throws Exception {
        User querUser = this.baseMapper.selectByUserName(user.getUsername());
        if (user == null){
            return R.error(REnum.USER_DOES_NOT_EXIST.getStatusCode(), REnum.USER_DOES_NOT_EXIST.getStatusMsg());
            // TODO 添加加密操作
        } else if (!querUser.getPassword().equals(user.getPassword())) {
            return R.error(REnum.USER_PASSWORD_ERROR.getStatusCode(), REnum.USER_PASSWORD_ERROR.getStatusMsg());
        }
        request.getSession().setAttribute("user", querUser);
        return R.ok(REnum.LOGIN_SUCCESS.getStatusCode(), REnum.LOGIN_SUCCESS.getStatusMsg());
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = this.baseMapper.selectList(null);
        return userList;
    }

    @Override
    public PageUtils getUserPage(Map<String, Object> params) {

        IPage<User> page = this.page(new QueryPage<User>().getPage(params, true),
                new LambdaQueryWrapper<User>().like(User::getUsername, (String) params.get("username")));
        return new  PageUtils(page);


    }

    @Override
    public void addUser(User user) {
        this.baseMapper.insert(user);
    }

    @Override
    public void editUser(User user) {
        this.baseMapper.updateById(user);
    }

    @Override
    public void deleteUserById(Long id) {
        this.baseMapper.deleteById(id);
    }

    // 登录逻辑
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//        try {
//            User user = this.baseMapper.getUserByUserName(username);
//            System.out.println("haha");
//            if (user != null){
//                List<Long> roleIdList = userRoleService.selectByUserId(user.getId());
//                for (Long roleId : roleIdList) {
//                    Role role = roleService.getById(roleId);
//                    System.out.println(role);
//                    simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRolename()));
//
//                }
//            }
//            return new MyUser(username, user.getPassword(), simpleGrantedAuthorities);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new MyUser(username, "", simpleGrantedAuthorities);
//        }
//    }
}
