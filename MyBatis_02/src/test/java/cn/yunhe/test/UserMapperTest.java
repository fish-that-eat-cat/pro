package cn.yunhe.test;


import cn.yunhe.entity.QueryVo;
import cn.yunhe.entity.User;
import cn.yunhe.mapper.UserMapper;
import cn.yunhe.utils.SessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserMapperTest {
    @Test
    /*
     *查询所有
     * */
    public void testFindAll() {
        /*调用工具类 创建会话工厂*/
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        /*从工厂中获取sqlSession对象*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*调用接口*/
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        /*执行方法*/
        List<User> all = mapper.findAll();
        all.forEach(System.out::println);
        /*关闭资源*/
        sqlSession.close();
    }

    /*
     *添加用户
     * */@Test
    public void testAddUser() {
        /*调用工具类 创建会话工厂*/
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        /*从工厂中获取sqlSession对象*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*调用接口*/
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        /*执行方法*/
        User user = new User();
        user.setSex("男");
        user.setBirthday("2000-03-06");
        user.setUsername("刘晓");
        user.setAddress("燕京");
        userMapper.addUser(user);
        sqlSession.commit();
        /*关闭资源*/
        sqlSession.close();
    }

    /*
    * 删除
    * */
    @Test
    public void testDelUser() {
        /*调用工具类 创建会话工厂*/
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        /*从工厂中获取sqlSession对象*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*调用接口*/
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        /*执行方法*/
        userMapper.delUser(52);
        sqlSession.commit();
        /*关闭资源*/
        sqlSession.close();
    }

    /*
    * 修改
    * */
    @Test
    public void testEditUser() {
        /*调用工具类 创建会话工厂*/
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        /*从工厂中获取sqlSession对象*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*调用接口*/
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        /*执行方法*/
        User user = new User();
        user.setSex("男");
        user.setBirthday("2000-03-06");
        user.setUsername("刘晓楠");
        user.setAddress("北京");
        user.setId(62);
        userMapper.editUser(user);
        sqlSession.commit();
        /*关闭资源*/
        sqlSession.close();
    }
    /*
    * 多条件查询
    * */
    @Test
    public void testFindBySelect() {
        /*调用工具类 创建会话工厂*/
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        /*从工厂中获取sqlSession对象*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*调用接口*/
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        /*执行方法*/
        List<User> list = userMapper.findBySelect("王小二", "女");
        list.forEach(System.out::println);
        sqlSession.commit();
        /*关闭资源*/
        sqlSession.close();
    }
    /*
     * resultMap使用***
     * */
    @Test
    public void findUserBySelect(){
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("uname","王小二");
        map.put("usex","女");

        for (User user : userMapper.findBySelectMap(map)) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void testFindBySelectPOJO1(){
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("王小二");
        user.setSex("女");
        queryVo.setUser(user);
        for (User user1 : userMapper.findBySelectPOJO1(queryVo)) {
            System.out.println(user1);
        }
        sqlSession.close();
    }
    /*
    * 利用user对象查询
    * */
    @Test
    public void testFindBySelectPOJO2(){
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("王小二");
        user.setSex("女");
        for (User user1 : userMapper.findBySelectPOJO2(user)) {
            System.out.println(user1);
        }
        sqlSession.close();
    }
    /*
    * 模糊查询
    * */
    @Test
    public void testFindByLike(){
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        for (User user1 : userMapper.findByLike("二")) {
            System.out.println(user1);
        }
        sqlSession.close();
    }
    /*
    * 多条件查询
    * */
    @Test
    public void testFindSelectByLike(){
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("二");
        user.setSex("女");
        for (User user1 : userMapper.findSelectByLike(user)) {
            System.out.println(user1);
        }
        sqlSession.close();
    }
    /*
    * 返回主键
    * */
    @Test
    public void testAddUserReturnKey() {
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("张小三");
        user.setBirthday("2000-04-17");
        user.setSex("女");
        user.setAddress("景阳冈");
        userMapper.addUserReturnKey(user);
        System.out.println("新增加的元素的id为"+user.getId());
        sqlSession.commit();
        sqlSession.close();
    }

    /*
     * 选择更新
     * */
    @Test
    public void testUpdateUserById() {
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("哈哈哈哈");
        user.setId(48);
        userMapper.updateUserById(user);
        sqlSession.commit();
        sqlSession.close();
    }

    /*
     * 批量删除  *****
     * */
    //第一种用list集合
    @Test
    public void testDeleteAll1() {
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(53);
        list.add(54);
        list.add(55);
        userMapper.deleteAll1(list);
        sqlSession.commit();
        sqlSession.close();
    }
    //第二种用Array数组
    @Test
    public void testDeleteAll2() {
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Integer arr[]={56,57,58};
        userMapper.deleteAll2(arr);
        sqlSession.commit();
        sqlSession.close();
    }

    //第三种用QueryVo实体类
    @Test
    public void testDeleteAll3() {
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        QueryVo queryVo = new QueryVo();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(59);
        list.add(60);
        list.add(61);
        queryVo.setList(list);
        userMapper.deleteAll3(queryVo);
        sqlSession.commit();
        sqlSession.close();
    }

}