package com.lqs.authapi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqs.authapi.domain.Permission;
import com.lqs.authapi.service.*;
import com.lqs.authapi.constant.REnum;
import com.lqs.authapi.domain.MyUser;
import com.lqs.authapi.domain.Role;
import com.lqs.authapi.domain.User;
import com.lqs.authapi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.Pagination.PageUtils;
import utils.Pagination.QueryPage;
import utils.R;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
    public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserPermissionService userPermissionService;

    @Override
    public R getUser(User user, HttpServletRequest request) throws Exception {

        User querUser = this.baseMapper.selectByUserName(user.getUsername());

        if (user == null){

            return R.error(REnum.USER_DOES_NOT_EXIST.getStatusCode(),
                    REnum.USER_DOES_NOT_EXIST.getStatusMsg());

        } else if (!querUser.getPassword().equals(user.getPassword())) {

            return R.error(REnum.USER_PASSWORD_ERROR.getStatusCode(),
                    REnum.USER_PASSWORD_ERROR.getStatusMsg());

        }
        request.getSession().setAttribute("user", querUser);

        return R.ok(REnum.LOGIN_SUCCESS.getStatusCode(),
                REnum.LOGIN_SUCCESS.getStatusMsg());
    }

    @Override
    public List<User> getUserList() {

        List<User> userList = this.baseMapper.selectList(null);

        return userList;
    }

    @Override
    public PageUtils getUserPage(Map<String, Object> params) {

        IPage<User> page = this.page(new QueryPage<User>().getPage(params, true),
                new LambdaQueryWrapper<User>().like(User::getUsername,
                        (String) params.get("username")));

        return new  PageUtils(page);

    }

    @Override
    public R addUser(User user) {

        User queryUser = this.baseMapper.getUserByUserName(user.getUsername());

        if (queryUser != null){

            return R.error(REnum.USER_DOES_EXIST.getStatusCode(),
                    REnum.USER_DOES_EXIST.getStatusMsg());

        }

        String password = user.getPassword();

        String encodePassword = passwordEncoder.encode(password);

        user.setPassword(encodePassword);

        this.baseMapper.insert(user);

        return R.ok(REnum.REGISTER_SUCCESS.getStatusCode(),
                REnum.REGISTER_SUCCESS.getStatusMsg());

    }

    @Override
    public void editUser(User user) {

        this.baseMapper.updateById(user);

    }

    @Override
    public void deleteUserById(Long id) {

        this.baseMapper.deleteById(id);

    }

    @Override
    @Transactional
    public R alterPwdByUserName(User user) {

        User userByUserName = this.baseMapper.getUserByUserName(user.getUsername());

        if(userByUserName == null){

            return R.error(REnum.USER_DOES_NOT_EXIST.getStatusCode(),
                    REnum.USER_DOES_NOT_EXIST.getStatusMsg());

        }

        String password = user.getPassword();

        String encodePassword = passwordEncoder.encode(password);

        user.setPassword(encodePassword);

        this.baseMapper.alterPwdByUserName(user);

        return R.ok(REnum.ALTER_PASSWORD_SUCCESS.getStatusCode(),
                REnum.ALTER_PASSWORD_SUCCESS.getStatusMsg());
    }

    @Override
    public User selectUserByName(String username) {

        User user = this.baseMapper.getUserByUserName(username);

        return user;

    }

    // 登录逻辑
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

        try {

            User user = this.baseMapper.getUserByUserName(username);

            if (user != null){

                List<Long> roleIdList = userRoleService.selectByUserId(user.getId());

                for (Long roleId : roleIdList) {

                    Role role = roleService.getById(roleId);

                    simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));

                }

                List<Long> permissionIdList = userPermissionService.selectByUserId(user.getId());

                for (Long permissionId : permissionIdList) {

                    Permission permission = permissionService.getById(permissionId);

                    simpleGrantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));

                }


            }

            return new MyUser(username, user.getPassword(), simpleGrantedAuthorities);

        }catch (Exception e){

            e.printStackTrace();

            return new MyUser(username, "", simpleGrantedAuthorities);

        }
    }
}
