package com.lwl.yygh.cmn.controller;

import com.lwl.yygh.cmn.service.DictService;
import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author user-lwl
 * @createDate 2022/11/24 19:38
 */
@Api("数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
public class DictController {
    @Resource
    private DictService dictService;

    /**
     * 根据数据id查询子数据列表
     * @param id id
     * @return Dict的List
     */
    @GetMapping("/findChildData/{id}")
    @ApiOperation("根据数据id查询子数据列表")
    public Result<List<Dict>> findChildData(@PathVariable Long id) {
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }

    /**
     * 导出数据字典接口
     * @param response 响应
     */
    @GetMapping(value = "/exportData", produces = "application/vnd.ms-excel")
    public void exportDict(HttpServletResponse response) {
        dictService.exportDictData(response);
    }

    /**
     * 导入数据字典
     * @param file 文件
     */
    @PostMapping("/importData")
    public Result importDict(MultipartFile file) {
        dictService.importDictData(file);
        return Result.ok();
    }
}
