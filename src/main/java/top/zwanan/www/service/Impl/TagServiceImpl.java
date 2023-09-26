package top.zwanan.www.service.Impl;

import top.zwanan.www.entity.Tag;
import top.zwanan.www.repository.TagRepository;
import top.zwanan.www.service.TagService;
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
