package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Dept;
import org.springframework.transaction.annotation.Transactional;

public interface DeptService extends IService<Dept> {

    @Transactional(rollbackFor = Exception.class) // spring事务管理,默认情况下，只有RuntimeException才回滚事务
    void removeById(Integer id);

}
