package com.heima.projectp.service;

import com.heima.projectp.mapper.DeptMapper;
import com.heima.projectp.mapper.EmpMapper;
import com.heima.projectp.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptService {

    @Autowired
    EmpMapper empMapper;
    @Autowired
    DeptMapper deptMapper;
    @Autowired
    LogService logService;

    @Transactional(rollbackFor = Exception.class) //開啟事務:一個失敗則同時失敗!
    public void deleteById(Integer id) throws Exception {
        try {
            deptMapper.DeptDeleteById(id);
            int i = 1 / 0;
//        if (true) {
//            throw new Exception("出錯啦~");
//        }
            empMapper.EmpDeleteByDeptId(id);


        } finally {
            logService.insert("刪除部門 " + id + " 時失敗，事務回滾");
        }

    }
}
