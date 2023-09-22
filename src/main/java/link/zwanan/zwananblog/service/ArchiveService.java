package link.zwanan.zwananblog.service;

import link.zwanan.zwananblog.model.vo.response.ArchiveResponse;

import java.util.List;

public interface ArchiveService {
    List<ArchiveResponse> getArchive();
}
