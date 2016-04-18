package com.taylor.api.shop.common;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

/**
 * @notes:数据库连接通用类
 * @author taylor
 * @date 2014-4-3 下午5:48:13
 */
public class ConnectDb {

    private static final Logger LOG = Logger.getLogger(ConnectDb.class);

    private static SqlSessionFactory sqlSessionFactory4Reader;

    private static SqlSessionFactory sqlSessionFactory4Writer;

    private static volatile ConnectDb connectDb = null;

    public static ConnectDb getInstance() {
        if (connectDb == null) {
            synchronized (ConnectDb.class) {
                if (connectDb == null)
                    connectDb = new ConnectDb();
            }
        }
        return connectDb;
    }

    private ConnectDb() {
        try {
            Reader reader1 = Resources.getResourceAsReader("META-INF/spring/sqlConfig.xml");
            sqlSessionFactory4Reader = new SqlSessionFactoryBuilder().build(reader1, "shopReader");
            reader1.close();

            Reader reader2 = Resources.getResourceAsReader("META-INF/spring/sqlConfig.xml");
            sqlSessionFactory4Writer = new SqlSessionFactoryBuilder().build(reader2, "shopWriter");
            reader2.close();
        } catch (IOException e) {
            LOG.error("error", e);
        }
    }

    /**
     * @notes:获取读操作SqlSession对象
     * @author taylor 2014-4-3 下午5:55:00
     */
    public SqlSession getReaderSqlSession() {
        return sqlSessionFactory4Reader.openSession();
    }

    /**
     * @notes:获取写操作SqlSession对象
     *
     * @author taylor 2015-8-11 下午6:29:05
     */
    public SqlSession getWriteSqlSession() {
        return sqlSessionFactory4Writer.openSession();
    }

}
