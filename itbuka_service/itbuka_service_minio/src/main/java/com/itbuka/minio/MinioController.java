package com.itbuka.minio;

import com.alibaba.fastjson.JSONObject;

import com.itbuka.entity.Result;
import com.itbuka.entity.StatusCode;
import com.itbuka.minio.util.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/minio")
@CrossOrigin
public class MinioController {
    @Autowired
    private MinioUtils minioUtils;
    /**
     * 上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam(name = "file", required = false) MultipartFile file) {
        String url = null;
        try {
            url = minioUtils.uploadFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (url.equals("1")){
            return  Result.fail("错误");
        }else if (url.equals("2")){
            return Result.fail("上传文件不能为空");
        }
        return new Result(true, StatusCode.OK,"上传成功", url);
    }


    /**
     * 文件删除
     * @param name
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String name) {
        try {
            minioUtils.delete(name);
        } catch (Exception e) {
            return new Result(false, StatusCode.ERROR,"删除失败");
        }
        return new Result(true, StatusCode.OK,"删除成功");
    }

    /**
     * 文件下载
     * @param name
     * @return
     */
    @GetMapping("/download")
    public Result download(@RequestParam String name, HttpServletResponse response) {
        boolean download = minioUtils.download(name, response);
        if (download){
            return new Result(true, StatusCode.OK,"下载成功");
        }else {
            return new Result(false, StatusCode.ERROR,"下载失败");
        }
    }

}
