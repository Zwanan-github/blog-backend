package link.zwanan.zwananblog.controller;

import link.zwanan.zwananblog.common.RestBean;
import link.zwanan.zwananblog.entity.Tag;
import link.zwanan.zwananblog.service.TagService;
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
