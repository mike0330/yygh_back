package com.tlm.yygh.cmn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tlm.yygh.cmn.mapper.DictMapper;
import com.tlm.yygh.cmn.service.DictService;
import com.tlm.yygh.model.cmn.Dict;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:TLM
 * Date:2024/1/25
 * Time:17:11
 * Description:
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public List<Dict> findByParentId(Long id) {
//        生成queryWrapper
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        List<Dict> dicts = baseMapper.selectList(wrapper);
//        遍历子节点将子节点的hasChildren赋值
        for(Dict dict : dicts){
            Long dictId = dict.getId();
            Boolean b = hasChildren(dictId);
            dict.setHasChildren(b);
        }
        return dicts;
    }

//    判断是否有子节点
    private Boolean hasChildren(Long id){
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Long count = baseMapper.selectCount(wrapper);
        return  count > 0;
    }
}
