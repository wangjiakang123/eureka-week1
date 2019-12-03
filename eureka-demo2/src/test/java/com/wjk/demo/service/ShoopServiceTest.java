package com.wjk.demo.service;


import com.github.pagehelper.PageInfo;
import com.wjk.demo.model.Shoop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoopServiceTest {

    @Autowired
    private ShoopService shoopService;

    @Test
    public void da(){
        Shoop shoop=new Shoop();
       PageInfo<Shoop> list= shoopService.list(1,3,shoop);
        System.out.println(list);

    }


}
