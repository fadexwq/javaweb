package cn.xwq.logistics.controller;


import cn.xwq.logistics.mo.MessageObject;
import cn.xwq.logistics.pojo.BaseData;
import cn.xwq.logistics.pojo.BaseDataExample;
import cn.xwq.logistics.service.BaseDataService;
import cn.xwq.logistics.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/baseData")
public class BaseDataController {

    @Autowired
    private BaseDataService baseDataService;

    @Autowired
    private RoleService roleService;

    //数据页面
    @RequestMapping("/baseDataPage")
    public String baseDataPage(){
        return "baseDataPage";
    }

    //数据列表
    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<BaseData> list(@RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize,
                               String keyword){

        PageHelper.startPage(pageNum,pageSize);
        BaseDataExample example = new BaseDataExample();

        if (StringUtils.isNotBlank(keyword)){
            BaseDataExample.Criteria criteria1 = example.createCriteria();
            criteria1.andBaseNameLike("%"+keyword+"%");

        }

        List<BaseData> baseDatas = baseDataService.selectByExample(example);
        PageInfo<BaseData> pageInfo = new PageInfo<BaseData>(baseDatas);
        return pageInfo;
    }


    /**
     *
     * 删除用户
     *
     */
    @RequestMapping("/delete")
    @ResponseBody
    public MessageObject delete(Long baseDataId){

        /*BaseDataExample example = new BaseDataExample();
        BaseDataExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(baseDataId);
        List<BaseData> children = baseDataService.selectByExample(example);
        if (children.size() > 0){
            return new MessageObject(0, "还有子权限，不能删除");
        }*/


        MessageObject mo = new MessageObject(0, "删除数据失败");
        int row = baseDataService.deleteByPrimaryKey(baseDataId);
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
    public String baseDataEdit(Model m,Long baseDataId){
        //判断baseDataId是否为空，空则是添加；不空则是修改。因为添加和修改用同一个界面
        if (baseDataId != null){
            BaseData baseData = baseDataService.selectByPrimaryKey(baseDataId);
            m.addAttribute("baseData",baseData);
        }

        //查询所有权限，用来选择
        BaseDataExample example = new BaseDataExample();
        List<BaseData> baseDatas = baseDataService.selectByExample(example);

        m.addAttribute("baseDatas",baseDatas);

        return "baseDataAdd";
    }

    /**
     *
     * 添加管理员submit
     *
     */
    @RequestMapping("/insert")
    @ResponseBody
    public MessageObject insert(BaseData baseData){

        MessageObject mo = new MessageObject(0,"添加失败");

        int row = baseDataService.insert(baseData);
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
    public MessageObject update(BaseData baseData){

        MessageObject mo = new MessageObject(0,"修改失败");

        int row = baseDataService.updateByPrimaryKeySelective(baseData);
        if (row == 1){
            mo = new MessageObject(1,"修改成功");
        }
        return mo;
    }



}
