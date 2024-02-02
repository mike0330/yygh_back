package com.tlm.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tlm.yygh.model.cmn.Dict;
import com.tlm.yygh.model.hosp.HospitalSet;

import java.util.List;

/**
 * Author:TLM
 * Date:2024/1/25
 * Time:17:10
 * Description:
 */

public interface DictService extends IService<Dict> {
    List<Dict> findByParentId(Long parentId);
}
