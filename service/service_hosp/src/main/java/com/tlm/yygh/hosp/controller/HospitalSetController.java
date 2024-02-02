package com.tlm.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tlm.yygh.common.result.Result;

import com.tlm.yygh.common.util.MD5;
import com.tlm.yygh.hosp.service.HospitalSetService;

import com.tlm.yygh.model.hosp.HospitalSet;

import com.tlm.yygh.vo.hosp.HospitalSetQueryVo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;


/**
 * Author:TLM
 * Date:2024/1/25
 * Time:17:24
 * Description:controller
 */
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {
    @Autowired
    HospitalSetService hospitalSetService;
//    查询医院设置表中所有的是数据

    @GetMapping("getAll")
    public Result getHospSet(){
        val list = hospitalSetService.list();

        return Result.ok(list);
    }

//    分页查询
    @PostMapping("findPage/{pageNo}/{pageSize}")
    public Result getPageHospSet(@PathVariable Integer pageNo ,@PathVariable Integer pageSize
    ,@RequestBody HospitalSetQueryVo hospitalSetQueryVo){
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(hospitalSetQueryVo.getHosname())){

            queryWrapper.like("hosname",hospitalSetQueryVo.getHosname());
        }
        if(!StringUtils.isEmpty(hospitalSetQueryVo.getHoscode())){

            queryWrapper.eq("hoscode",hospitalSetQueryVo.getHoscode());
        }
        Page<HospitalSet> hospitalSetPage = new Page<>(pageNo, pageSize);
        Page<HospitalSet> page = hospitalSetService.page(hospitalSetPage,queryWrapper);
        return Result.ok(page);
    }
//    新增hospSet
    @PostMapping("saveHospSet")
    public Result addHospSet(@RequestBody HospitalSet hospitalSet){
        // 手动设置签名密钥和状态  状态（1可用,0不能用）
        hospitalSet.setStatus(1);
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));
        boolean save = hospitalSetService.save(hospitalSet);
        return save ? Result.ok() : Result.fail();
    }
//根据id删除hospSet
    @DeleteMapping("delHospSetByIds")
    public Result delHospSetById(@RequestBody List<Integer> ids ){
        boolean b = hospitalSetService.removeByIds(ids);
        return b ? Result.ok() : Result.fail();
    }
//    修改医院设置
    @PutMapping("updateById")
    public Result updateHospSetById(@RequestBody HospitalSet hospitalSet){
        boolean b = hospitalSetService.updateById(hospitalSet);
        return Result.ok(b);
    }
//    根据id查询医院设置
    @GetMapping("findHospSetById")
    public Result getHospSetById (@RequestParam Integer id ){
        HospitalSet byId = hospitalSetService.getById(id);
        return Result.ok(byId);

    }
//    医院设置的锁定和解锁（更改status）
    @PutMapping("lockHospSet/{id}/{status}")
    public Result lockHospSetById(@PathVariable Integer id,@PathVariable Integer status  ){
        HospitalSet byId = hospitalSetService.getById(id);
        byId.setStatus(status);
        boolean b = hospitalSetService.updateById(byId);
        return  Result.ok(b);
    }
//    发送签名密钥(通过短信发送)
    @PostMapping("sendKey")
    public Result sendHospSetKey(@RequestBody Integer id){
        HospitalSet byId = hospitalSetService.getById(id);
        String signKey = byId.getSignKey();
        System.out.println(signKey);
//        TODO 获取signKey后通过短信发送
        return Result.ok();
    }

}
