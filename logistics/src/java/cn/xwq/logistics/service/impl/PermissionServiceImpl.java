package cn.xwq.logistics.service.impl;

import cn.xwq.logistics.mapper.PermissionMapper;
import cn.xwq.logistics.pojo.Permission;
import cn.xwq.logistics.pojo.PermissionExample;
import cn.xwq.logistics.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public int deleteByPrimaryKey(Long permissionId) {
        return permissionMapper.deleteByPrimaryKey(permissionId);
    }

    @Override
    public int insert(Permission record) {
        return permissionMapper.insert(record);
    }

    @Override
    public List<Permission> selectByExample(PermissionExample example) {
        return permissionMapper.selectByExample(example);
    }

    @Override
    public Permission selectByPrimaryKey(Long permissionId) {
        return permissionMapper.selectByPrimaryKey(permissionId);
    }

    @Override
    public int updateByPrimaryKeySelective(Permission record) {
        return permissionMapper.updateByPrimaryKeySelective(record);
    }
}
