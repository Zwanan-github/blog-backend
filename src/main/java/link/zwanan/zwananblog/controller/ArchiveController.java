package link.zwanan.zwananblog.controller;

import link.zwanan.zwananblog.common.RestBean;
import link.zwanan.zwananblog.model.vo.response.ArchiveResponse;
import link.zwanan.zwananblog.service.ArchiveService;
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
