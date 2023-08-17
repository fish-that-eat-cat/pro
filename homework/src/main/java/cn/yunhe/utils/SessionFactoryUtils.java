package cn.yunhe.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SessionFactoryUtils {
    static SqlSessionFactory  sqlSessionFactory;
    static {
        try {
           InputStream fis = Resources.getResourceAsStream("sqlMapConfig.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }
}
