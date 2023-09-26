package top.zwanan.www.controller;

import top.zwanan.www.common.RestBean;
import top.zwanan.www.entity.Tag;
import top.zwanan.www.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    @GetMapping
    public RestBean<List<Tag>> getAll() {
        return RestBean.success(tagService.getAll());
    }
}
