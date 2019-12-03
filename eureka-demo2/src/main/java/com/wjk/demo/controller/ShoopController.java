package com.wjk.demo.controller;

import com.wjk.demo.model.Shoop;
import com.wjk.demo.service.ShoopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/shoop/")
public class ShoopController {

    @Autowired
    private ShoopService shoopService;

   //列表+分页+查询
    @GetMapping("list")
    public Object list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "3") Integer pageSize, Shoop shoop){
        return shoopService.list(pageNum,pageSize,shoop);
    }
    //添加商品
    @PostMapping("save")
    public Object save(@RequestBody Shoop shoop){
        if(shoop.getDase().equals("上架")){
            shoop.setUpper_Status(1);
        }else{
            shoop.setUpper_Status(0);
        }

        System.out.println(shoop);
        return shoopService.insetshoop(shoop);
    }
    //根据id获取详情
    @GetMapping("getid")
    public Object getid(Integer id){

      Shoop shoop=  shoopService.getid(id);
        if(shoop.getUpper_Status()==1){
            shoop.setDase("上架");
        }else{
            shoop.setDase("下架");
        }
        return shoop;
    }
    //根据id删除数据
    @GetMapping("del")
    public Object del(Integer id){
        return shoopService.delshoop(id);
    }
    //修改数据
    @PostMapping("upt")
    public Object upt(@RequestBody  Shoop shoop){

        if(shoop.getDase().equals("上架")){
            shoop.setUpper_Status(1);
        }else{
            shoop.setUpper_Status(0);
        }
        System.out.println(shoop);
        return shoopService.uptshoop(shoop);
    }




}
