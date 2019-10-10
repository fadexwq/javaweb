package cn.xwq.logistics.service;

import cn.xwq.logistics.pojo.BaseData;
import cn.xwq.logistics.pojo.BaseDataExample;

import java.util.List;

public interface BaseDataService {

    int deleteByPrimaryKey(Long baseDataId);

    int insert(BaseData record);

    List<BaseData> selectByExample(BaseDataExample example);

    BaseData selectByPrimaryKey(Long baseDataId);

    int updateByPrimaryKeySelective(BaseData record);
}
