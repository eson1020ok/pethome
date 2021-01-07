package org.eson.basic.controller;

import org.apache.commons.io.FilenameUtils;
import org.eson.basic.util.AjaxResult;
import org.eson.basic.util.FastDfsApiOpr;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件controller层
 */
@RestController
@RequestMapping("/dfs")
public class FastDfsController {

    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file) {
        try {
            // 获取上传附件名
            String filename = file.getOriginalFilename();
            // 根据附件名获取后缀名
            String extName = FilenameUtils.getExtension(filename);
            // 得到上传附件的路径
            String uploadPath = FastDfsApiOpr.upload(file.getBytes(), extName);
            // 返回给前端
            return AjaxResult.me().setResultObject(uploadPath);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "上传附件失败");
        }
    }

    /**
     * 删除附件
     * @param path
     *          /group1/M00/00/3F/rBEAA1_wfRaAHH4yAAR8MhmIwPU524.jpg
     * @return
     */
    @DeleteMapping("/deleteDfs")
    public AjaxResult dalete(@RequestParam(required = true, value = "path") String path) {
        try {
            String filePathTmp = path.substring(1); //  group1/M00/00/3F/rBEAA1_wfRaAHH4yAAR8MhmIwPU524.jpg
            // 获取组名：group1
            String groupName = filePathTmp.substring(0, filePathTmp.indexOf("/"));
            // 存储在fastdfs中的附件路径：M00/00/3F/rBEAA1_wfRaAHH4yAAR8MhmIwPU524.jpg
            String annexPath  = filePathTmp.substring(filePathTmp.indexOf("/") + 1);
            // 执行删除附件的方法
            FastDfsApiOpr.delete(groupName, annexPath);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, e.getMessage());
        }
    }
}
