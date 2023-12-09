package com.heima.projectp.controller;
import com.heima.projectp.pojo.PageBean;
import com.heima.projectp.pojo.Result;
import com.heima.projectp.pojo.Emp;
import com.heima.projectp.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/emps")  //抽取路徑前墜
public class EmpController {

    @Autowired
    EmpService empService;

//    private static Logger log = LogManager.getLogger(EmpController.class);


    /**
     * 新增
     * @param emp
     * @return
     */
    @PostMapping
    public Result empInsert(@RequestBody Emp emp){
        empService.EmpInsert(emp);
        return Result.success("新增成功");
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public Result empDeleteById(@PathVariable List<Integer> ids){
        Integer i = empService.EmpDeleteById(ids);
        log.info("执行删除操作: {} 数据受到影响",i);
        if(i>0){
            return Result.success("删除成功");
        }
        else{
            return Result.success("删除失败");
        }
    }

    @PutMapping
    public Result empUpdate(@RequestBody Emp emp){
        empService.EmpUpdate(emp);
        return Result.success("修改成功","");
    }


    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/empSelectAll")
    @GetMapping
    public Result empSelectAll(){
        List<Emp> list = empService.EmpSelectAll();
        log.info("查询了全部部门的数据   {}","看得到我吗?");
        return Result.success(list);
    }

    /**
     * 依照ID查詢
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result empSelectById(@PathVariable Integer id){
        Emp emp = empService.EmpSelectById(id);
        return Result.success(emp);
    }

    /**
     * 顯示對應頁數查詢
     * @param page
     * @param pageSize
     * @param name
     * @param create_time
     * @return
     */
    @GetMapping("/page")
    public Result empPage(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "5") Integer pageSize,
                          String name, String create_time){
        PageBean pb = empService.EmpPage(page, pageSize, name, create_time);
        log.info("頁數:{},大小:{}",page, pageSize);
        return Result.success(pb);
    }





//    @PutMapping("/{id}")
//    public Result empUpdateBy(@PathVariable Integer id){
//        empService.EmpUpdateById(id);
//    }



}
