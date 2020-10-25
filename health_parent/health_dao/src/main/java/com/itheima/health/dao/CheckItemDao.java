package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.Exception.MyException;
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
public interface CheckItemDao {
    /**
     * 查询所有检查项
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 添加检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    Page<CheckItem> findPage(String queryString);

    /**
     * 删除
     * @param id
     * @return
     */
    int findCountByCheckItemId(int id);

    /**
     * 通过id删除
     * @param id
     */
    void deleteById(int id) throws MyException;
}
