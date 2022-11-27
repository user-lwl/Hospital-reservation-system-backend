package com.lwl.yygh.cmn.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.yygh.cmn.listener.DictListener;
import com.lwl.yygh.cmn.mapper.DictMapper;
import com.lwl.yygh.cmn.service.DictService;
import com.lwl.yygh.model.cmn.Dict;
import com.lwl.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
* @author user-lwl
* @description 针对表【dict(组织架构表)】的数据库操作Service实现
* @createDate 2022-11-23 19:14:31
*/
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict>
    implements DictService {

    @Resource
    private DictMapper dictMapper;
    /**
     * 根据数据id查询子数据列表
     * @param id id
     * @return Dict的List
     */
    @Override
    @Cacheable(value = "dict", keyGenerator = "keyGenerator")
    public List<Dict> findChildData(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<Dict> dictList = dictMapper.selectList(wrapper);
        for (Dict dict : dictList) {
            Long dictId = dict.getId();
            dict.setHasChildren(this.isChildren(dictId));
        }
        return dictList;
    }

    /**
     * 导出数据字典接口
     * @param response 响应
     */
    @Override
    public void exportDictData(HttpServletResponse response) {
        //设置下载信息
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        String fileName = "dict";
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        //查库
        List<Dict> dictList = dictMapper.selectList(null);
        //Dict->DictEeVo
        ArrayList<DictEeVo> dictVoList = new ArrayList<>();
        for (Dict dict : dictList) {
            DictEeVo dictEeVo = new DictEeVo();
            BeanUtils.copyProperties(dict, dictEeVo);
            dictVoList.add(dictEeVo);
        }
        //写
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            EasyExcelFactory.write(outputStream, DictEeVo.class)
                    .sheet("dict").doWrite(dictVoList);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入数据字典
     * @param file 文件
     */
    @Override
    @CacheEvict(value = "dict", allEntries = true)
    public void importDictData(MultipartFile file) {
        try {
            EasyExcelFactory.read(file.getInputStream(), DictEeVo.class, new DictListener(dictMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据dictCode和value查询
     * @param dictCode dictCode
     * @param value value
     * @return 名字
     */
    @Override
    public String getDictName(String dictCode, String value) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isEmpty(dictCode)) {
            queryWrapper.eq("value", value);
            return dictMapper.selectOne(queryWrapper).getName();
        } else {
            queryWrapper.eq("dict_code", dictCode);
            Dict dict = dictMapper.selectOne(queryWrapper);
            return dictMapper.selectOne(new QueryWrapper<Dict>()
                    .eq("parent_id", dict.getId())
                    .eq("value", value)).getName();
        }
    }

    /**
     * 根据dictCode获取下级节点
     * @param dictCode dictCode
     * @return Dict的List
     */
    @Override
    public List<Dict> findByDictCode(String dictCode) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dict_code", dictCode);
        Dict dict = dictMapper.selectOne(queryWrapper);
        return this.findChildData(dict.getId());
    }

    /**
     * 判断id下面是否有子节点
     * @param id id
     * @return 是否有子节点
     */
    private boolean isChildren(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        return dictMapper.selectCount(wrapper) > 0;
    }
}




