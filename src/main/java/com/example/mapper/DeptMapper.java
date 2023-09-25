package com.example.mapper;

import com.example.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {
    /**
     * 查询全部部门
     * @return
     */
    @Select("select * from dept")
    List<Dept> list();

    /**
     * 根据ID删除部门
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    @Insert("insert into dept(name, create_time, update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void updateDept(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept selectById(Integer id);
}
