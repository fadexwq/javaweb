package cn.xwq.logistics.service.impl;

import cn.xwq.logistics.mapper.RoleMapper;
import cn.xwq.logistics.pojo.Role;
import cn.xwq.logistics.pojo.RoleExample;
import cn.xwq.logistics.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int deleteByPrimaryKey(Long roleId) {
        return roleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public int insert(Role record) {
        return roleMapper.insert(record);
    }

    @Override
    public List<Role> selectByExample(RoleExample example) {
        return roleMapper.selectByExample(example);
    }

    @Override
    public Role selectByPrimaryKey(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }
}
