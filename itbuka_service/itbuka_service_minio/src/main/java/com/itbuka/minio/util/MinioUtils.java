package com.itbuka.minio.util;

import com.itbuka.minio.domin.MinioProp;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Slf4j
@Component
public class MinioUtils {

    @Autowired
    private MinioClient client;
    @Autowired
    private MinioProp minioProp;


    /**
     * 创建bucket
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void createBucket(String bucketName) {
        if (!client.bucketExists(bucketName)) {
            client.makeBucket(bucketName);
            client.setBucketPolicy(bucketName,"*", PolicyType.READ_WRITE);
        }
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return
     */
    public String uploadFile(MultipartFile file) throws Exception {
        // 判断上传文件是否为空
        if (null == file || 0 == file.getSize()) {
            return "2";
        }
        try {
            String originalFilename = file.getOriginalFilename();
            String bucketName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            // 判断存储桶是否存在
            createBucket(bucketName);
            // 文件名

            // 新的文件名 = 存储桶名称_时间戳.后缀名
            String fileName = bucketName + "_" + System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
            // 开始上传
            client.putObject(bucketName, fileName, file.getInputStream(), file.getContentType());
            String url = minioProp.getEndpoint() + "/" + bucketName + "/" + fileName;
            return url;
        } catch (Exception e) {
            log.error("上传文件失败：{}", e.getMessage());
        }
        return "1";
    }


    /**
     * 文件删除
     *
     * @param name
     * @return
     */
    public String delete(String name) {
        try {
            String bucketName = name.substring(name.lastIndexOf(".") + 1);
            // 判断存储桶是否存在
            client.removeObject(bucketName, name);
        } catch (Exception e) {
            return "删除失败" + e.getMessage();
        }
        return "删除成功";
    }

    /**
     * 下载文件
     */
    @SneakyThrows
    public boolean download(String objectName, HttpServletResponse response) {
        try {
            String bucketName = objectName.substring(objectName.lastIndexOf(".") + 1);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();//创建输出流对象
            InputStream inputStream = client.getObject(bucketName, objectName);
            OutputStream outputStream = response.getOutputStream();
            response.setContentType("application/" + bucketName);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + objectName);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
