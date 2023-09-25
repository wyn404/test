package com.example.service.impl;

import com.example.mapper.DeptMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.Dept;
import com.example.pojo.DeptLog;
import com.example.service.DeptLogService;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class) // spring事务管理,默认情况下，只有RuntimeException才回滚事务
    @Override
    public void delete(Integer id) {
        try {
            deptMapper.deleteById(id);
//            int i = 1 / 0; // 模拟抛出异常
//        if (true) { throw new Exception("出错啦..."); }
            empMapper.deleteById(id);

        } finally {
            DeptLog deptLog = new DeptLog();

            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门操作，此次解散的是"+ id + "号部门");
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }

    @Override
    public void updateDeptById(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.updateDept(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.selectById(id);
    }


}
