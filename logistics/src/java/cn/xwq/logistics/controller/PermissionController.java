package cn.xwq.logistics.controller;


import cn.xwq.logistics.mo.MessageObject;
import cn.xwq.logistics.pojo.Role;
import cn.xwq.logistics.pojo.RoleExample;
import cn.xwq.logistics.pojo.Permission;
import cn.xwq.logistics.pojo.PermissionExample;
import cn.xwq.logistics.service.RoleService;
import cn.xwq.logistics.service.PermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    //数据页面
    @RequestMapping("/permissionPage")
    public String permissionPage(){
        return "permissionPage";
    }

    //数据列表
    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Permission> list(@RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize,
                               String keyword){

        PageHelper.startPage(pageNum,pageSize);
        PermissionExample example = new PermissionExample();

        if (StringUtils.isNotBlank(keyword)){
            PermissionExample.Criteria criteria1 = example.createCriteria();
            criteria1.andNameLike("%"+keyword+"%");

        }

        List<Permission> permissions = permissionService.selectByExample(example);
        PageInfo<Permission> pageInfo = new PageInfo<Permission>(permissions);
        return pageInfo;
    }


    /**
     *
     * 删除用户
     *
     */
    @RequestMapping("/delete")
    @ResponseBody
    public MessageObject delete(Long permissionId){

        //删除分类，有子权限的不能删
        PermissionExample example = new PermissionExample();
        PermissionExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(permissionId);
        List<Permission> children = permissionService.selectByExample(example);
        if (children.size() > 0){
            return new MessageObject(0, "还有子权限，不能删除");
        }


        MessageObject mo = new MessageObject(0, "删除数据失败");
        int row = permissionService.deleteByPrimaryKey(permissionId);
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
    public String permissionEdit(Model m,Long permissionId){
        //判断permissionId是否为空，空则是添加；不空则是修改。因为添加和修改用同一个界面
        if (permissionId != null){
            Permission permission = permissionService.selectByPrimaryKey(permissionId);
            m.addAttribute("permission",permission);
        }

        //查询所有权限，用来选择
        PermissionExample example = new PermissionExample();
        List<Permission> permissions = permissionService.selectByExample(example);

        m.addAttribute("permissions",permissions);

        return "permissionAdd";
    }

    /**
     *
     * 添加管理员submit
     *
     */
    @RequestMapping("/insert")
    @ResponseBody
    public MessageObject insert(Permission permission){

        MessageObject mo = new MessageObject(0,"添加失败");

        int row = permissionService.insert(permission);
        if (row == 1){
            mo = new MessageObject(1,"添加成功");
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
    public MessageObject update(Permission permission){

        MessageObject mo = new MessageObject(0,"修改失败");

        int row = permissionService.updateByPrimaryKeySelective(permission);
        if (row == 1){
            mo = new MessageObject(1,"修改成功");
        }
        return mo;
    }

    /**
     *
     * 获取所有权限数据，json格式(给zTree使用)
     *
     */


    @RequestMapping("/getAllPermission")
    @ResponseBody
    public List<Permission> getAllPermission(){
        PermissionExample example = new PermissionExample();
        List<Permission> permissions = permissionService.selectByExample(example);
        return permissions;
    }



}
