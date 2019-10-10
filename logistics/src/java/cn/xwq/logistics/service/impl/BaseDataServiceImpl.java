package cn.xwq.logistics.service.impl;

import cn.xwq.logistics.mapper.BaseDataMapper;
import cn.xwq.logistics.pojo.BaseData;
import cn.xwq.logistics.pojo.BaseDataExample;
import cn.xwq.logistics.service.BaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BaseDataServiceImpl implements BaseDataService {

    @Autowired
    private BaseDataMapper baseDataMapper;

    @Override
    public int deleteByPrimaryKey(Long baseDataId) {
        return baseDataMapper.deleteByPrimaryKey(baseDataId);
    }

    @Override
    public int insert(BaseData record) {
        return baseDataMapper.insert(record);
    }

    @Override
    public List<BaseData> selectByExample(BaseDataExample example) {
        return baseDataMapper.selectByExample(example);
    }

    @Override
    public BaseData selectByPrimaryKey(Long baseDataId) {
        return baseDataMapper.selectByPrimaryKey(baseDataId);
    }

    @Override
    public int updateByPrimaryKeySelective(BaseData record) {
        return baseDataMapper.updateByPrimaryKeySelective(record);
    }
}
