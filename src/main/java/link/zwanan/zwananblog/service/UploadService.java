package link.zwanan.zwananblog.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    String upload(MultipartFile file) throws IOException;
}
