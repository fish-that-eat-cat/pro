package cn.yunhe.mapper;

import cn.yunhe.entity.QueryVo;
import cn.yunhe.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface UserMapper {
    /*
    * 查询
    * */
    List<User> findAll();
    /*
    * 增加
    * */
    void addUser(User user);
    /*
     * 修改
     * */
    void editUser(User user);
    /*
     * 删除
     * */
    void delUser(int id);
    /*
    * 多条件查询
    * */
    List<User> findBySelect(@Param("username")String uname,@Param("sex")String usex);
    /*
    * 利用map查询
    * */
    List<User> findBySelectMap(HashMap<Object, Object> map);
    /*
    * 根据user对象查询
    * */
    List<User> findBySelectPOJO2(User user);
    /*
    * 根据queryVo类对象查询
    * */
    List<User> findBySelectPOJO1(QueryVo queryVo);

    /*
    * 模糊查询
    * */
    List<User> findByLike(String username);

    /*
    * 多条件查询
    * */
    List<User> findSelectByLike(User user);

    /*
     * 返回主键
     * */
    void addUserReturnKey(User user);

    /*
     * 选择更新
     * */
    void updateUserById(User user);

    /*
     * 批量删除 *****
     * */
    //用集合
    void deleteAll1(ArrayList<Integer> ids);
    //用数组
    void deleteAll2(Integer arr[]);
    //用QueryVo实体类
    void deleteAll3(QueryVo queryVo);
}
