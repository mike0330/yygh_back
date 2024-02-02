package com.tlm.yygh.cmn.controller;

import com.tlm.yygh.cmn.service.DictService;
import com.tlm.yygh.common.result.Result;
import com.tlm.yygh.model.cmn.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author:TLM
 * Date:2024/2/2
 * Time:14:51
 * Description:cmn控制层
 */

@RestController
@RequestMapping("/admin/cmn/dict")
public class CmnController {

    @Autowired
    DictService dictService;
    /**
     * 根据上级id获取子节点
     *
     */
    @GetMapping("findByParentId/{parentId}")
    public Result findByParentId(@PathVariable Long parentId){

        List<Dict> dictList = dictService.findByParentId(parentId);
        return Result.ok(dictList);
    }
}
