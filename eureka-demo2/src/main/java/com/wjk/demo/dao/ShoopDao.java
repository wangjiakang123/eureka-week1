package com.wjk.demo.dao;

import com.wjk.demo.model.Shoop;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShoopDao {


    List<Shoop> list(@Param("shoop") Shoop shoop);
    int insetshoop(@Param("shoop") Shoop shoop);
    @Update("update shooping set name=#{shoop.name},price =#{shoop.price},imge_url=#{shoop.imge_url},factory_date=#{shoop.factory_date},upper_Status=#{shoop.upper_Status}, where id=#{shoop.id}")
    int uptshoop(@Param("shoop") Shoop shoop);
    @Delete("delete from shooping where id=#{id}")
    int delshoop(Integer id);
    @Select("select * from shooping where id=#{id}")
    Shoop getid(Integer id);

}
