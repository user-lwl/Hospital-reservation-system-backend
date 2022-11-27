package com.lwl.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lwl.yygh.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @author user-lwl
* @description 针对表【dict(组织架构表)】的数据库操作Service
* @createDate 2022-11-23 19:14:31
*/
public interface DictService extends IService<Dict> {

    /**
     * 根据数据id查询子数据列表
     * @param id id
     * @return dict的List
     */
    List<Dict> findChildData(Long id);

    /**
     * 导出数据字典接口
     * @param response 响应
     */
    void exportDictData(HttpServletResponse response);

    /**
     * 导入数据字典
     * @param file 文件
     */
    void importDictData(MultipartFile file);

    /**
     * 根据dictCode和value查询
     * @param dictCode dictCode
     * @param value value
     * @return 名字
     */
    String getDictName(String dictCode, String value);

    /**
     * 根据dictCode获取下级节点
     * @param dictCode dictCode
     * @return Dict的List
     */
    List<Dict> findByDictCode(String dictCode);
}
