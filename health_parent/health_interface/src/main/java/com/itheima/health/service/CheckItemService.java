package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/10/22
 */
public interface CheckItemService {
    /**
     * 查询所有的检查项
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 添加检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /*
    分页查询
    */
    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    /**
     * 通过id删除
     * @param id
     */
    void deleteById(int id);
}
