package com.taylor.api.shop.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.taylor.api.shop.common.ConnectDb;
import com.taylor.api.shop.dao.BaseDao;

/**
 * @notes：数据库公共类Dao类
 * @param <T>
 * @param <E>
 * @author fei
 * 
 *         2014-4-3 下午1:47:05
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    private static final Logger LOG = Logger.getLogger(BaseDaoImpl.class);

    private static final String NEW_INSERT_BY_SQL = "NewInsertBySql";

    protected String mapperPackage;

    public BaseDaoImpl() {
        mapperPackage = getDao4MapperPackage();
        mapperPackage += ".";
    }

    /**
     * 关闭链接
     */
    private void closeSqlSession(SqlSession sqlSession) {
        if (sqlSession != null)
            sqlSession.close();
    }

    /**
     * 方法描述:插入信息并返回对应的Id
     *
     * @param sql 插入的SQL
     * @param tablename 插入SQL对应的表名
     * @author fei 2014-4-6 下午10:28:03
     */
    @Override
    public Long insertRtnId(String sql, String tablename) {
        SqlSession sqlSession = ConnectDb.getInstance().getWriteSqlSession();
        try {
            /************ 执行新增Insert ************/
            sqlSession.insert(NEW_INSERT_BY_SQL, sql);
            sqlSession.commit();
            /************ 执行获取新增数据Id ************/
            BigDecimal bigDecimalId = (BigDecimal) sqlSession.selectOne("SELECT IDENT_CURRENT('" + tablename + "')");
            if (null != bigDecimalId) {
                return bigDecimalId.longValue();
            }
        } catch (Exception e) {
            sqlSession.rollback();
            LOG.error("数据处理错误...BaseDaoImpl对象的insertRtnId方法", e);
        } finally {
            closeSqlSession(sqlSession);
        }
        return null;
    }

    /**
     * 方法描述:根据传入的SQL，返回查询的对象（只返回一行记录） <调用commonMapper中的NewSelectBySql实现>
     *
     * @param sql SQL<sql查询的结果必须是一行结果否则会出错>
     * @author fei 2014-4-6 下午10:39:24
     */
    @Override
    public Object selectOne4SQL(String sql) {
        Object object = null;
        SqlSession sqlSession = ConnectDb.getInstance().getReaderSqlSession();
        try {
            object = sqlSession.selectOne("NewSelectBySql", sql);
        } catch (Exception e) {
            LOG.error("数据处理错误...BaseDaoImpl对象的selectOne4SQL方法", e);
        } finally {
            closeSqlSession(sqlSession);
        }
        return object;
    }

    /**
     * 方法描述:根据传入的sql，返回查询结果的Map结构列表 <调用commonMapper中的NewMapSelectBySql实现>
     *
     * @param sql 查询SQL
     * @author fei 2014-4-6 下午10:48:26
     */
    @SuppressWarnings("rawtypes")
    @Override
    public List selectMapList4SQL(String sql) {
        List list = null;
        SqlSession sqlSession = ConnectDb.getInstance().getReaderSqlSession();
        try {
            list = sqlSession.selectList("NewMapSelectBySql", sql);
        } catch (Exception e) {
            LOG.error("数据处理错误...BaseDaoImpl对象的selectMapList4SQL方法", e);
        } finally {
            closeSqlSession(sqlSession);
        }
        return list;
    }

    /**
     * 方法描述:根据传入的sql，返回一条查询结果记录的Map结构列表 <调用commonMapper中的NewMapSelectBySql实现>
     *
     * @param sql 查询SQL
     * @author fei 2014-4-6 下午11:11:42
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Map selectOneMap4SQL(String sql) {
        Map object = null;
        SqlSession sqlSession = ConnectDb.getInstance().getReaderSqlSession();
        try {
            object = (Map) sqlSession.selectOne("NewMapSelectBySql", sql);
        } catch (Exception e) {
            LOG.error("数据处理错误...BaseDaoImpl对象的selectOneMap4SQL方法", e);
        } finally {
            closeSqlSession(sqlSession);
        }
        return object;
    }

    /**
     * 方法描述:带事务的，根据sql添加数据 <调用commonMapper中的NewInsertBySql实现>
     *
     * @param sql
     * @author fei 2014-4-6 下午10:53:55
     */
    @Override
    public void insert4SQL(String sql) {
        SqlSession sqlSession = ConnectDb.getInstance().getWriteSqlSession();
        try {
            sqlSession.insert(NEW_INSERT_BY_SQL, sql);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            LOG.error("数据处理错误...BaseDaoImpl对象的insert4SQL方法", e);
        } finally {
            closeSqlSession(sqlSession);
        }
    }


    /**
     * 方法描述:带事务处理的通用update方法，根据传入的Sql更新 <调用commonMapper中的NewUpdateBySql实现>
     *
     * @param sql
     * @author fei 2014-4-6 下午10:57:18
     */
    @Override
    public void update4SQL(String sql) {
        SqlSession sqlSession = ConnectDb.getInstance().getWriteSqlSession();
        try {
            sqlSession.update("NewUpdateBySql", sql);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            LOG.error("数据处理错误...BaseDaoImpl对象的update4SQL方法", e);
        } finally {
            closeSqlSession(sqlSession);
        }
    }

    /**
     * 方法描述:带事务处理的通用update方法，返回更新成功记录数，根据传入的Sql更新 <调用commonMapper中的NewUpdateBySql实现>
     * 
     * @param sql 更新数据的sql
     * @author fei 2014-4-6 下午10:59:33
     */
    @Override
    public int update4SQLRtnUpNum(String sql) {
        SqlSession sqlSession = ConnectDb.getInstance().getWriteSqlSession();
        int count = 0;
        try {
            count = sqlSession.update("NewUpdateBySql", sql);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            LOG.error("数据处理错误...BaseDaoImpl对象的update4SQLRtnUpNum方法", e);
        } finally {
            closeSqlSession(sqlSession);
        }
        return count;
    }

    /**
     * 方法描述:带事务处理的，通用根据SQL删除数据方法
     *
     * @param sql 传入通用方法的SQL
     * @author fei 2014-4-6 下午11:04:46
     */
    @Override
    public void delete4SQL(String sql) {
        SqlSession sqlSession = ConnectDb.getInstance().getWriteSqlSession();
        try {
            sqlSession.delete("NewDeleteyBySql", sql);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            LOG.error("数据处理错误...BaseDaoImpl对象的delete4SQL方法", e);
        } finally {
            closeSqlSession(sqlSession);
        }
    }

    /**
     * 方法描述:带事务处理的，自定义SQL Mapper 删除 数据方法
     *
     * @param sqlaliasname sql alias name<SQL别名>
     * @param Object 参数对象
     * @author fei 2014-4-6 下午11:13:09
     */
    @Override
    public int delete(String sqlaliasname, Object obj) {
        SqlSession sqlSession = ConnectDb.getInstance().getWriteSqlSession();
        try {
            int exNum = sqlSession.delete(mapperPackage + sqlaliasname, obj);
            sqlSession.commit();
            return exNum;
        } catch (Exception e) {
            sqlSession.rollback();
            LOG.error("数据处理错误...BaseDaoImpl对象的delete方法", e);
            return -1;
        } finally {
            closeSqlSession(sqlSession);
        }
    }

    /**
     * 方法描述:带事务处理的，自定义SQL Mapper 添加 数据方法
     *
     * @param sqlaliasname sql alias name<SQL别名>
     * @param Object 参数对象
     * @author fei 2014-4-6 下午11:13:09
     */
    @Override
    public int insert(String sqlaliasname, Object obj) {
        SqlSession sqlSession = ConnectDb.getInstance().getWriteSqlSession();
        try {
            int exNum = sqlSession.insert(mapperPackage + sqlaliasname, obj);
            sqlSession.commit();
            return exNum;
        } catch (Exception e) {
            sqlSession.rollback();
            LOG.error("数据处理错误...BaseDaoImpl对象的insert方法", e);
            return -1;
        } finally {
            closeSqlSession(sqlSession);
        }
    }

    /**
     * 方法描述:更新 数据方法，带事务处理的，自定义根据sqlaliasname对应SQL Mapper对应语句
     *
     * @param sqlaliasname sql alias name<SQL别名>
     * @param Object 参数对象
     * @author fei 2014-4-6 下午11:14:34
     */
    @Override
    public int update(String sqlaliasname, Object t) {
        SqlSession sqlSession = ConnectDb.getInstance().getWriteSqlSession();
        try {
            int exNum = sqlSession.update(mapperPackage + sqlaliasname, t);
            sqlSession.commit();
            return exNum;
        } catch (Exception e) {
            sqlSession.rollback();
            LOG.error("数据处理错误...BaseDaoImpl对象的update方法", e);
            return -1;
        } finally {
            closeSqlSession(sqlSession);
        }
    }

    /**
     * 方法描述:自定义根据sqlaliasname对应SQL Mapper对应语句，查询单个对象的方法
     *
     * @param sqlaliasname sql alias name<SQL别名>
     * @param Object 参数对象
     * @author fei 2014-4-6 下午11:16:40
     */
    @Override
    public Object selectOne(String sqlaliasname, Object obj) {
        Object object;
        SqlSession sqlSession = null;
        try {
            sqlSession = ConnectDb.getInstance().getReaderSqlSession();
            object = sqlSession.selectOne(mapperPackage + sqlaliasname, obj);
            return object;
        } catch (Exception e) {
            LOG.error("数据处理错误...BaseDaoImpl对象的selectOne方法", e);
            return null;
        } finally {
            closeSqlSession(sqlSession);
        }
    }

    /**
     * @notes:查询列表，自定义根据sqlaliasname对应SQL Mapper对应语句，查询多个对象的方法 类似于：select * from A where b =#names
     * 
     * @param sqlaliasname sql alias name<SQL别名>
     * @param parameter Map参数对象
     * @author fei 2014-4-18 下午5:14:37
     */
    @SuppressWarnings("rawtypes")
    public List selectList(String sqlaliasname, Object parameter) {
        List list = null;
        SqlSession sqlSession = null;
        try {
            sqlSession = ConnectDb.getInstance().getReaderSqlSession();
            list = sqlSession.selectList(mapperPackage + sqlaliasname, parameter);
        } catch (Exception e) {
            LOG.error("数据处理错误...BaseDaoImpl对象的selectList方法", e);
        } finally {
            closeSqlSession(sqlSession);
        }
        return list;
    }

    /**
     * 方法描述:批量添加方法
     *
     * @param sqlaliasname SQL Mapper 别名
     * @param list 添加对象List
     * @author fei 2014-4-6 下午11:50:31
     */
    @Override
    public boolean batchInsert(String sqlaliasname, List<T> list) {
        if (null == list || list.isEmpty())
            return false;

        SqlSession sqlSession = null;
        try {
            sqlSession = ConnectDb.getInstance().getWriteSqlSession();
            for (T t : list) {
                sqlSession.insert(mapperPackage + sqlaliasname, t);
            }
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            if (null != sqlSession) {
                sqlSession.rollback();
            }
            LOG.error("数据处理错误...BaseDaoImpl对象的batchInsert方法", e);
            return false;
        } finally {
            closeSqlSession(sqlSession);
        }
    }

    /**
     * 方法描述:批量修改方法
     *
     * @param sqlaliasname SQL Mapper 别名
     * @param list 添加对象List
     * @author fei 2014-4-6 下午11:50:31
     */
    @Override
    public boolean batchUpdate(String sqlaliasname, List<T> list) {
        if (null == list || list.isEmpty())
            return false;

        SqlSession sqlSession = null;
        try {
            sqlSession = ConnectDb.getInstance().getWriteSqlSession();
            for (T t : list) {
                sqlSession.update(mapperPackage + sqlaliasname, t);
            }
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            if (null != sqlSession) {
                sqlSession.rollback();
            }
            LOG.error("数据处理错误...BaseDaoImpl对象的batchUpdate方法", e);
            return false;
        } finally {
            closeSqlSession(sqlSession);
        }
    }

    /**
     * 方法描述:批量删除方法
     *
     * @param sqlaliasname SQL Mapper 别名
     * @param list 添加对象List
     * @author fei 2014-4-6 下午11:50:31
     */
    @Override
    public boolean batchDelete(String sqlaliasname, List<T> list) {
        if (null == list || list.isEmpty())
            return false;

        SqlSession sqlSession = null;
        try {
            sqlSession = ConnectDb.getInstance().getWriteSqlSession();
            for (T t : list) {
                sqlSession.delete(mapperPackage + sqlaliasname, t);
            }
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            if (null != sqlSession) {
                sqlSession.rollback();
            }
            LOG.error("数据处理错误...BaseDaoImpl对象的batchDelete方法", e);
            return false;
        } finally {
            closeSqlSession(sqlSession);
        }
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * ielts_admin_user
     *
     * @mbggenerated Tue Jan 13 20:14:48 CST 2015
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return delete(mapperPackage + "deleteByPrimaryKey", id);
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * ielts_admin_user
     *
     * @mbggenerated Tue Jan 13 20:14:48 CST 2015
     */
    @Override
    public int insert(T t) {
        return insert("insert", t);
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * ielts_admin_user
     *
     * @mbggenerated Tue Jan 13 20:14:48 CST 2015
     */
    @Override
    public int insertSelective(T t) {
        return insert("insertSelective", t);
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * ielts_admin_user
     *
     * @mbggenerated Tue Jan 13 20:14:48 CST 2015
     */
    @SuppressWarnings("unchecked")
    @Override
    public T selectByPrimaryKey(Integer id) {
        return (T) selectOne("selectByPrimaryKey", id);
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * ielts_admin_user
     *
     * @mbggenerated Tue Jan 13 20:14:48 CST 2015
     */
    @Override
    public int updateByPrimaryKeySelective(T t) {
        return update("updateByPrimaryKeySelective", t);
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * ielts_admin_user
     *
     * @mbggenerated Tue Jan 13 20:14:48 CST 2015
     */
    @Override
    public int updateByPrimaryKey(T t) {
        return update("updateByPrimaryKey", t);
    }
}
