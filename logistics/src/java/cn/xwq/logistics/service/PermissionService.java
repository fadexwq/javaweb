package cn.xwq.logistics.service;

import cn.xwq.logistics.pojo.Permission;
import cn.xwq.logistics.pojo.PermissionExample;

import java.util.List;

public interface PermissionService {

    int deleteByPrimaryKey(Long permissionId);

    int insert(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Long permissionId);

    int updateByPrimaryKeySelective(Permission record);
}
