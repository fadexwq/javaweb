package cn.xwq.logistics.controller;


import cn.xwq.logistics.mo.MessageObject;
import cn.xwq.logistics.pojo.*;
import cn.xwq.logistics.service.RoleService;
import cn.xwq.logistics.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    //数据页面
    @RequestMapping("/rolePage")
    public String rolePage(){
        return "rolePage";
    }

    //数据列表
    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Role> list(@RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize,
                               String keyword){

        PageHelper.startPage(pageNum,pageSize);
        RoleExample example = new RoleExample();

        if (StringUtils.isNotBlank(keyword)){
            RoleExample.Criteria criteria1 = example.createCriteria();
            criteria1.andRolenameLike("%"+keyword+"%");

        }

        List<Role> roles = roleService.selectByExample(example);
        PageInfo<Role> pageInfo = new PageInfo<Role>(roles);
        return pageInfo;
    }


    /**
     *
     * 删除用户
     *
     */
    @RequestMapping("/delete")
    @ResponseBody
    public MessageObject delete(Long roleId){

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        List<User> users = userService.selectByExample(example);
        if (users.size() > 0){
            return new MessageObject(0, "此角色关联有管理员，不能删除");
        }

        MessageObject mo = new MessageObject(0, "删除数据失败");
        int row = roleService.deleteByPrimaryKey(roleId);
        if (row == 1){
            mo = new MessageObject(1,"删除数据成功");
        }
        return mo;
    }


    /**
     *
     * 添加管理员操作
     * 发送数据库中存在的role
     */
    @RequestMapping("/add")
    public String roleEdit(Model m,Long roleId){
        //判断roleId是否为空，空则是添加；不空则是修改。因为添加和修改用同一个界面
        if (roleId != null){
            Role role = roleService.selectByPrimaryKey(roleId);
            m.addAttribute("role",role);
        }

        //查询所有权限，用来选择
        RoleExample example = new RoleExample();
        List<Role> roles = roleService.selectByExample(example);

        m.addAttribute("roles",roles);

        return "roleAdd";
    }

    /**
     *
     * 添加管理员submit
     *
     */
    @RequestMapping("/insert")
    @ResponseBody
    public MessageObject insert(Role role){


        MessageObject mo = new MessageObject(0,"添加失败");

        int row = roleService.insert(role);
        if (row == 1){
            mo = new MessageObject(1,"添加成功！！");
        }
        return mo;

    }

    /**
     *
     * 添加管理员submit
     *
     */
    @RequestMapping("/update")
    @ResponseBody
    public MessageObject update(Role role){

        System.out.println(role.toString()+"角色修改后！！！");
        MessageObject mo = new MessageObject(0,"修改失败");

        int row = roleService.updateByPrimaryKeySelective(role);
        if (row == 1){
            mo = new MessageObject(1,"修改成功！！！");
        }
        return mo;
    }

    /**
     *
     *
     *
     */


    /*@RequestMapping("/checkRolename")
    @ResponseBody
    public boolean checkRolename(Long roleId) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        List<Role> roles = roleService.selectByExample(example);

        return roles.size() > 0 ? false : true;

    }*/



}
