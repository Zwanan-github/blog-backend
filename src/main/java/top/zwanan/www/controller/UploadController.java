package top.zwanan.www.controller;

import top.zwanan.www.common.RestBean;
import top.zwanan.www.common.RestException;
import top.zwanan.www.service.UploadService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {

    final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PreAuthorize("hasAnyRole('upload_image')")
    @PostMapping
    public RestBean<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        if (null == contentType || !contentType.startsWith("image/")) {
            throw new RestException(HttpStatus.BAD_REQUEST.value(), "错误的文件请求格式!");
        }
        return RestBean.success(uploadService.upload(file));
    }



}
