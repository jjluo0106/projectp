package com.heima.projectp.mapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DeptMapper {

    public void DeptDeleteById(Integer id);
}
