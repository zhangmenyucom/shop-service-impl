package com.taylor.api.shop.service.impl;

import com.taylor.api.shop.service.BaseService;

/**
 * @notes:业务公共类Service类接口
 *
 * @author taylor
 *
 *         2015-4-21 下午9:19:05
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    /**
     * @notes:根据主键删除表对象记录
     *
     * @param id
     * @author taylor 2015-4-21 下午9:34:48
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    /**
     * @notes:根据表对象插入对应记录
     *
     * @param t
     * @author taylor 2015-4-21 下午9:35:14
     */
    @Override
    public int insert(T t) {
        return 0;
    }

    /**
     * @notes:根据表对象插入记录的非空值
     *
     * @param t
     * @author taylor 2015-4-21 下午9:35:37
     */
    @Override
    public int insertSelective(T t) {
        return 0;
    }

    /**
     * @notes:根据表主键查询对应的表对象
     *
     * @param id
     * @author taylor 2015-4-21 下午9:36:04
     */
    @Override
    public T selectByPrimaryKey(Integer id) {
        return null;
    }

    /**
     * @notes:根据主键有选择的更新表对象非空字段
     *
     * @param t
     * @author taylor 2015-4-21 下午9:36:27
     */
    @Override
    public int updateByPrimaryKeySelective(T t) {
        return 0;
    }

    /**
     * @notes:根据主键更新表对象表记录所有字段
     *
     * @param t
     * @author taylor 2015-4-21 下午9:37:13
     */
    @Override
    public int updateByPrimaryKey(T t) {
        return 0;
    }

}
