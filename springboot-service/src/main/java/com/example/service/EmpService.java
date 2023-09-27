package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Emp;

import java.time.LocalDate;

public interface EmpService extends IService<Emp> {

    IPage<Emp> getPage(Integer currentPage, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    Emp login(Emp emp);
}
