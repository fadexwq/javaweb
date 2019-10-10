package cn.xwq.logistics.service;

import cn.xwq.logistics.pojo.User;
import cn.xwq.logistics.pojo.UserExample;

import java.util.List;

public interface UserService {

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);
}
