package cn.xwq.logistics.test;

import cn.xwq.logistics.pojo.User;
import cn.xwq.logistics.pojo.UserExample;
import cn.xwq.logistics.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectByExample() {

        /*
            PageHelper.startPage(pageNum,pageSize);
            pageNum:页码，默认第一页
            pageNum:每页条数
         */

        int pageNum = 1;
        int pageSize = 10;
        PageHelper.startPage(pageNum,pageSize);

        UserExample example = new UserExample();
        List<User> users = userService.selectByExample(example);

        //创建分页对象，将查询的list集合存放到分页对象中；PageInfo 对象中封装了分页所需要的全部信息
        PageInfo<User> pageInfo = new PageInfo<User>(users);
        System.out.println(pageInfo);

    }

    @Test
    public void selectByPrimaryKey() {
        User user = userService.selectByPrimaryKey(1L);
        System.out.println(user);
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }
}