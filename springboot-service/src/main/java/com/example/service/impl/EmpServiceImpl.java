package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.service.EmpService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public boolean updateById(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        return empMapper.updateById(emp) > 0;
    }

    @Override
    public IPage<Emp> getPage(Integer currentPage, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        IPage<Emp> page = new Page<>(currentPage, pageSize);

        LambdaQueryWrapper<Emp> lqw = new LambdaQueryWrapper<>();

        lqw.like(Strings.isNotEmpty(name), Emp::getName, name)
                .eq(gender != null, Emp::getGender, gender)
                .lt(end != null, Emp::getEntrydate, end)
                .gt(begin != null, Emp::getEntrydate, begin);

        return empMapper.selectPage(page, lqw);
    }

    @Override
    public Emp login(Emp emp) {
        LambdaQueryWrapper<Emp> lqw = new LambdaQueryWrapper<>();

        lqw.eq(Emp::getUsername, emp.getUsername())
                .eq(Emp::getPassword, emp.getPassword());

        return empMapper.selectOne(lqw);
    }

    @Override
    public boolean save(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        emp.setCreateTime(LocalDateTime.now());
        return empMapper.insert(emp) > 0;
    }
}
