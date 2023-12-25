package com.heima.projectp.mapper;


import com.heima.projectp.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper {

//    @Select("select * from db1.emp")

    /**查询所有员工*/
    public List<Emp> EmpSelectAll();
    public List<Emp> EmpSelectAll2(String name, String create_time);
    /**新增员工*/
    public void EmpInsert(Emp emp);

    /**删除员工*/
    public Integer EmpDeleteById(List<Integer> ids);
    /**依照dept_id刪除員工*/
    void EmpDeleteByDeptId(Integer id);


    /**修改員工*/
    void EmpUpdateById(Integer id);
    /**修改员工*/


    /**依照id寻找员工*/
    Emp EmpSelectById(Integer id);
    /**修改員工資料*/
    void EmpUpdate(Emp emp);
    /**
     * 登入
     *
     * @return
     */
    List<Emp> EmpSelectByUsername(Emp emp);




//    /**依照 頁數,一次顯示數量 查詢員工*/
//    List<Emp> EmpPage(Integer page,Integer team);
//    /**返回最大數量*/
//    Integer EmpMax();
}
