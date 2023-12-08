package com.heima.projectp.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.heima.projectp.mapper.EmpMapper;
import com.heima.projectp.pojo.Emp;
import com.heima.projectp.pojo.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EmpService {
    @Autowired
    EmpMapper empMapper;
    public List<Emp> EmpSelectAll(){
        List<Emp> emps = empMapper.EmpSelectAll();
        return emps;
    }

    public void EmpInsert(Emp emp) {
        empMapper.EmpInsert(emp);
    }

    public Integer EmpDeleteById(List<Integer> ids) {
        return empMapper.EmpDeleteById(ids);
    }

    public void EmpUpdateById(Integer id) { empMapper.EmpUpdateById(id);}


    public Emp EmpSelectById(Integer id) {
        Emp emp = empMapper.EmpSelectById(id);
        return emp;
    }

    public PageBean EmpPage(Integer page, Integer pageSize, String name, String create_time) {
//        List<Emp> emps = empMapper.EmpPage(team*(page-1), team);
//        Integer max = empMapper.EmpMax();
        //1.设置分页参数
        PageHelper.startPage(page,pageSize);
        //2.执行查询
        List<Emp> emps = empMapper.EmpSelectAll2(name,create_time);
        Page<Emp> p =(Page<Emp>) emps;
//        PageInfo<Emp> p = new PageInfo<>(emps);
        log.info("p.getTotal():{}, p.getList():{}",p.getTotal(),  p.getResult());

        return new PageBean((int) p.getTotal(), p.getResult());
    }
}
