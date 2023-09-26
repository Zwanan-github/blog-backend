package top.zwanan.www.service;

import top.zwanan.www.model.vo.response.ArchiveResponse;

import java.util.List;

public interface ArchiveService {
    List<ArchiveResponse> getArchive();
}
