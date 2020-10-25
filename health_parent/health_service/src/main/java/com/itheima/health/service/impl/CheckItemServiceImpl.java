package com.itheima.health.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.Exception.MyException;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.dao.CheckItemDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * interfaceClass 发布服务时使用的接口
 * @author: Eric
 * @since: 2020/10/22
 */
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    /**
     * 查询所有的检查项
     * @return
     */
    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    /**
     * 添加检查项
     * @param checkItem
     */
    @Override
    public void add(CheckItem checkItem) {

        checkItemDao.add(checkItem);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        //第二种，Mapper接口方式的调用，推荐这种使用方式。
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 模糊查询 拼接 %
        // 判断是否有查询条件
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            // 有查询条件，拼接%
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        // 紧接着的查询语句会被分页
        Page<CheckItem> page = checkItemDao.findPage(queryPageBean.getQueryString());
        // 封装到分页结果对象中
        PageResult<CheckItem> pageResult = new PageResult<CheckItem>(page.getTotal(), page.getResult());
        return pageResult;
    }

    /**
     * 通过id删除
     * @param id
     */
    @Override
    public void deleteById(int id) {
        // 判断是否被检查组使用了
        int count = checkItemDao.findCountByCheckItemId(id);
        if(count > 0){
            // 被使用了，则要报错
            throw new MyException("该检查项已经被使用了，不能删除!");
        }
        // 没使用，就可以删除
        checkItemDao.deleteById(id);
    }

    
}
