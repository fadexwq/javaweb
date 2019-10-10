package cn.xwq.logistics.controller;


import cn.xwq.logistics.mo.MessageObject;
import cn.xwq.logistics.pojo.Role;
import cn.xwq.logistics.pojo.RoleExample;
import cn.xwq.logistics.pojo.User;
import cn.xwq.logistics.pojo.UserExample;
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

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    //数据页面
    @RequestMapping("/adminPage")
    public String adminPage(){
        return "adminPage";
    }

    //数据列表
    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<User> list(@RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize,
                               String keyword){

        PageHelper.startPage(pageNum,pageSize);
        UserExample example = new UserExample();

        if (StringUtils.isNotBlank(keyword)){
            UserExample.Criteria criteria1 = example.createCriteria();
            criteria1.andUsernameLike("%"+keyword+"%");

            UserExample.Criteria criteria2 = example.createCriteria();
            criteria2.andRealnameLike("%"+keyword+"%");

            example.or(criteria2);
        }

        List<User> users = userService.selectByExample(example);
        PageInfo<User> pageInfo = new PageInfo<User>(users);
        return pageInfo;
    }


    /**
     *
     * 删除用户
     *
     */
    @RequestMapping("/delete")
    @ResponseBody
    public MessageObject delete(Long userId){

        MessageObject mo = new MessageObject(0, "删除数据失败");
        int row = userService.deleteByPrimaryKey(userId);
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
    public String adminEdit(Model m,Long userId){
        //判断uesrId是否为空，空则是添加；不空则是修改。因为添加和修改用同一个界面
        if (userId != null){
            User user = userService.selectByPrimaryKey(userId);
            m.addAttribute("user",user);
        }

        //查询所有角色，用来选择
        RoleExample example = new RoleExample();
        List<Role> roles = roleService.selectByExample(example);

        m.addAttribute("roles",roles);

        return "adminAdd";
    }

    /**
     *
     * 添加管理员submit
     *
     */
    @RequestMapping("/insert")
    @ResponseBody
    public MessageObject insert(User user){

        user.setCreateDate(new Date());
        MessageObject mo = new MessageObject(0,"添加失败");

        int row = userService.insert(user);
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
    public MessageObject update(User user){

        MessageObject mo = new MessageObject(0,"修改失败");

        int row = userService.updateByPrimaryKeySelective(user);
        if (row == 1){
            mo = new MessageObject(1,"修改成功");
        }
        return mo;
    }

    /**
     *
     * 检测用户名是否存在
     *
     */
    @RequestMapping("/checkUsername")
    @ResponseBody
    public boolean checkUsername(String username){
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userService.selectByExample(example);

        return users.size() > 0 ? false : true;
    }


}
