package com.example.controller;

import com.example.anno.Log;
import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    //private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    /**
     * 查询部门数据
     * @return
     */
    //@RequestMapping(value = "/depts",method = RequestMethod.GET) //指定请求方式为GET
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
        //调用service查询部门数据
        List<Dept> deptList =  deptService.list();
        return Result.success(deptList);
    }


    /**
     * 删除部门
     * @return
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门:{}",id);
        //调用service删除部门
        deptService.delete(id);
        return Result.success();
    }


    /**
     * 新增部门
     * @return
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门: {}" , dept);
        //调用service新增部门
        deptService.add(dept);
        return Result.success();
    }

    @Log
    @PutMapping
    public Result updateDept(@RequestBody Dept dept) {
        log.info("修改部门：{}", dept);
        deptService.updateDeptById(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
        log.info("根据id查询部门：{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }
}
