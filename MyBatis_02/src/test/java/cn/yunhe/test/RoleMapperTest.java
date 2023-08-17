package cn.yunhe.test;

import cn.yunhe.entity.Role;
import cn.yunhe.mapper.RoleMapper;
import cn.yunhe.utils.SessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

public class RoleMapperTest {
    @Test
    public void testFindAll(){
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        for (Role role : roleMapper.findAll()) {
            System.out.println(role);
        }
        sqlSession.close();

    }
}
