package link.zwanan.zwananblog.service.Impl;

import link.zwanan.zwananblog.entity.Tag;
import link.zwanan.zwananblog.repository.TagRepository;
import link.zwanan.zwananblog.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }
}
