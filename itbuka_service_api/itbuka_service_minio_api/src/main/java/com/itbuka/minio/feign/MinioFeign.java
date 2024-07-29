package com.itbuka.minio.feign;

import com.itbuka.entity.Result;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@EnableFeignClients("minio")
public interface MinioFeign {
    /**
     * 上传
     *
     * @param file
     * @return
     */
    @PostMapping("/minio/upload")
    public Result upload(@RequestParam(name = "file", required = false) MultipartFile file);

    /**
     * 文件删除
     *
     * @param name
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String name);

    /**
     * 文件下载
     *
     * @param name
     * @return
     */
    @GetMapping("/download")
    public Result download(@RequestParam String name, HttpServletResponse response);
}