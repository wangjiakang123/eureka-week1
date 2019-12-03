package com.wjk.demo.service;

import com.github.pagehelper.PageInfo;
import com.wjk.demo.model.Shoop;

public interface ShoopService {

    PageInfo<Shoop> list(Integer pageNum, Integer pageSize, Shoop shoop);
    int insetshoop(Shoop shoop);
   int uptshoop(Shoop shoop);
    int delshoop(Integer id);
    Shoop getid(Integer id);

}
