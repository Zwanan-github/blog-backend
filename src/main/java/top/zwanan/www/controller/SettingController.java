package top.zwanan.www.controller;

import top.zwanan.www.common.RestBean;
import top.zwanan.www.service.SettingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/settings")
public class SettingController {

    final SettingService settingService;

    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping
    public RestBean<Map<String, String>> getSettings() {
        return RestBean.success(settingService.getSettings());
    }

}
