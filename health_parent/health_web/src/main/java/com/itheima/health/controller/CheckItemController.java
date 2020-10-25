package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.Exception.MyException;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/10/22
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    @GetMapping("/findAll")
    public Result findAll(){
        // 调用服务查询
        List<CheckItem> list = checkItemService.findAll();
        // 封装到Result再返回
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
    }

    /**
     * 添加检查项
     */
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        // 调用服务添加
        checkItemService.add(checkItem);
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
        public Result findPage(@RequestBody QueryPageBean queryPageBean){
            //调用服务分页查询
        PageResult<CheckItem> pageResult = checkItemService.findPage(queryPageBean);
        //返回给页面
        /**
         * {
         *     flag
         *     message
         *     data:{
         *         total
         *         rows
         *     }
         * }
         */
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);
        }

    /**
     * 通过id删除
     */
    @PostMapping("/deleteById")
        public Result deleteById(int id){
        //调用业务删除
        
            checkItemService.deleteById(id);

        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
        }
}
