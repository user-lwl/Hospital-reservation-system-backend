package com.lwl.yygh.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.lwl.yygh.cmn.mapper.DictMapper;
import com.lwl.yygh.model.cmn.Dict;
import com.lwl.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;

/**
 * @author user-lwl
 * @createDate 2022/11/25 14:04
 */
public class DictListener extends AnalysisEventListener<DictEeVo> {
    @Resource
    private DictMapper dictMapper;

    public DictListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo, dict);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
