package com.taylor.api.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taylor.api.shop.dao.ShopUserDao;
import com.taylor.api.shop.entity.ShopUser;
import com.taylor.api.shop.service.ShopUserService;

/**
 * @notes:用户信息表Service实现类
 *
 * @author taylor
 *
 * @date 2016-04-18 00:47:19 Email:516195940@qq.com
 */
@Service
public class ShopUserServiceImpl extends BaseServiceImpl<ShopUser> implements ShopUserService {

    @Autowired
    private ShopUserDao shopUserDao;

    @Autowired
    public ShopUserDao getThisDao() {
        return shopUserDao;
    }

    /**
     * @desc countShopUsers(查询用户数量)
     * @author taylor
     * @date 2016年4月18日 上午1:13:58
     */
    @Override
    public Integer countShopUsers() {
        return (Integer) shopUserDao.selectOne("countShopUsers", null);
    }

    /**
     * @desc queryShopUser(查询用户)
     * @param shopUser
     * @author taylor
     * @date 2016年4月19日 上午12:15:10
     */
    @Override
    public ShopUser queryShopUser(ShopUser shopUser) {
        return (ShopUser) shopUserDao.selectOne("queryShopUser", shopUser);
    }
}
