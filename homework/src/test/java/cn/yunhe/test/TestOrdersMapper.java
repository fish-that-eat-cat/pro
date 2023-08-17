package cn.yunhe.test;


import cn.yunhe.entity.Dept;
import cn.yunhe.entity.Orders;
import cn.yunhe.mapper.DeptMapper;
import cn.yunhe.mapper.OrdersMapper;
import cn.yunhe.utils.SessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

public class TestOrdersMapper {

    /*
    * 一对一多表查询
    * */
    @Test
    public void testLazyOneToOne(){
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);
        Orders orders = ordersMapper.findOrders(2);
        System.out.println(orders.getMoney());
        System.out.println(orders.getUser());
        sqlSession.close();
    }

    /*
     * 一对多 多表查询
     * */
    @Test
    public void testLazyOneToMany(){
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = deptMapper.findDept("销售部");
        System.out.println(dept.getDname());
        System.out.println(dept.getEmpList());
        sqlSession.close();
    }

}
