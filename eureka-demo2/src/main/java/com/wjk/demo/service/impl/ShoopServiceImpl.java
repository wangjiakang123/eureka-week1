package com.wjk.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjk.demo.dao.ShoopDao;
import com.wjk.demo.model.Shoop;
import com.wjk.demo.service.ShoopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShoopServiceImpl implements ShoopService {

   @Autowired
   private ShoopDao shoopDaos;

    @Override
    public PageInfo<Shoop> list(Integer pageNum, Integer pageSize, Shoop shoop) {
        PageHelper.startPage(pageNum,pageSize);
        System.out.println("------------------");
       List<Shoop> list= shoopDaos.list(shoop);

       for (int i=0;i<list.size();i++){
           Shoop shoops= list.get(i);
           if(shoops.getUpper_Status()==1){
               shoops.setDase("上架");
           }else{
               shoops.setDase("下架");
           }
       }
        System.out.println(list);
        return new PageInfo<>(list);
    }

    @Override
    public int insetshoop(Shoop shoop) {
        return shoopDaos.insetshoop(shoop);
    }

    @Override
    public int uptshoop(Shoop shoop) {
        return shoopDaos.uptshoop(shoop);
    }

    @Override
    public int delshoop(Integer id) {
        return shoopDaos.delshoop(id);
    }

    @Override
    public Shoop getid(Integer id) {
        return shoopDaos.getid(id);
    }
}
