package com.lqs.yebapi.controller;

import com.lqs.yebapi.constant.REnum;
import com.lqs.yebapi.domain.Role;
import com.lqs.yebapi.domain.User;
import com.lqs.yebapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.Pagination.PageUtils;
import utils.R;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @GetMapping("rolePage")
    public R getPage(@RequestParam Map<String, Object> param){
        try{
            PageUtils roleList = roleService.getUserPage(param);
            return R.ok(REnum.GET_ROLE_SUCCESS.getStatusCode(),
                            REnum.GET_ROLE_SUCCESS.getStatusMsg())
                    .put("roleList", roleList);

        }catch (Exception e){
            e.printStackTrace();
            return R.error(REnum.GET_ROLE_FAIL.getStatusCode(),
                    REnum.GET_ROLE_FAIL.getStatusMsg());
        }
    }

    @GetMapping("roleList")
    public R getList(){
        try{
            List<Role> roleList = roleService.getList();
            return R.ok(REnum.GET_ROLE_SUCCESS.getStatusCode(),
                            REnum.GET_ROLE_SUCCESS.getStatusMsg())
                    .put("roleList", roleList);

        }catch (Exception e){
            e.printStackTrace();
            return R.error(REnum.GET_ROLE_FAIL.getStatusCode(),
                    REnum.GET_ROLE_FAIL.getStatusMsg());
        }
    }


    @PostMapping("addRole")
    public R addRole(@RequestBody Role role){
        try{
            R result = roleService.addRole(role);
            return result;

        }catch (Exception e){
            e.printStackTrace();
            return R.error(REnum.ROLE_ADD_FAIL.getStatusCode(),
                    REnum.ROLE_ADD_FAIL.getStatusMsg());
        }
    }


    @PostMapping("editRole")
    public R editRole(@RequestBody Role role){
        try{
            roleService.editRole(role);
            return R.ok(REnum.ROLE_EDIT_SUCCESS.getStatusCode(),
                    REnum.ROLE_EDIT_SUCCESS.getStatusMsg());

        }catch (Exception e){
            e.printStackTrace();
            return R.error(REnum.ROLE_EDIT_FAIL.getStatusCode(),
                    REnum.ROLE_EDIT_FAIL.getStatusMsg());
        }
    }



    @PostMapping("deleteRole")
    public R deleteRole(@RequestBody Role role){
        try{
            roleService.deleteUserById(role.getId());
            return R.ok(REnum.ROLE_DELETE_SUCCESS.getStatusCode(),
                    REnum.ROLE_DELETE_SUCCESS.getStatusMsg());

        }catch (Exception e){
            e.printStackTrace();
            return R.error(REnum.ROLE_DELETE_FAIL.getStatusCode(),
                    REnum.ROLE_DELETE_FAIL.getStatusMsg());
        }
    }


}
