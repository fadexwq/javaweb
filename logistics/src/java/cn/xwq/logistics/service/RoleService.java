package cn.xwq.logistics.service;

import cn.xwq.logistics.pojo.Role;
import cn.xwq.logistics.pojo.RoleExample;

import java.util.List;

public interface RoleService {

    int deleteByPrimaryKey(Long roleId);

    int insert(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(Role record);
}
