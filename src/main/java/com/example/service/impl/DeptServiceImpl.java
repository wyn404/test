package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.DeptMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.Dept;
import com.example.pojo.DeptLog;
import com.example.pojo.Emp;
import com.example.service.DeptLogService;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    @Override
    public boolean save(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        return deptMapper.insert(dept) > 0;
    }

    @Override
    public boolean updateById(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        return deptMapper.updateById(dept) > 0;
    }

    @Transactional(rollbackFor = Exception.class) // spring事务管理,默认情况下，只有RuntimeException才回滚事务
    @Override
    public void removeById(Integer id) {
        try {
            deptMapper.deleteById(id);
            LambdaQueryWrapper<Emp> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Emp::getDeptId, id);
            empMapper.delete(lqw);
        } finally {
            DeptLog deptLog = new DeptLog();

            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门操作，此次解散的是"+ id + "号部门");
            deptLogService.insert(deptLog);
        }
    }


}
