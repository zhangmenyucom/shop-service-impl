package com.taylor.api.shop.dao.impl;

import org.springframework.stereotype.Repository;

import com.taylor.api.shop.entity.ShopUser;  
import com.taylor.api.shop.dao.ShopUserDao;  

/**
 * @notes:用户信息表Dao实现类
 *
 * @author taylor
 *
 * @date 2016-04-18 00:47:19	Email:516195940@qq.com
 */
@Repository
public class ShopUserDaoImpl extends BaseDaoImpl<ShopUser> implements ShopUserDao {

	@Override
	public String getDao4MapperPackage() {
		return "mapper.ShopUser";
	}

}