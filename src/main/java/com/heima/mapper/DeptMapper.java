package com.heima.mapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DeptMapper {

    public void DeptDeleteById(Integer id);
}
