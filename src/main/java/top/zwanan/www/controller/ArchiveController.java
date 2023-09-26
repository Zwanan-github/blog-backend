package top.zwanan.www.controller;

import top.zwanan.www.common.RestBean;
import top.zwanan.www.model.vo.response.ArchiveResponse;
import top.zwanan.www.service.ArchiveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/archive")
public class ArchiveController {

    final ArchiveService archiveService;

    public ArchiveController(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @GetMapping
    public RestBean<List<ArchiveResponse>> getArchive() {
        return RestBean.success(archiveService.getArchive());
    }

}
