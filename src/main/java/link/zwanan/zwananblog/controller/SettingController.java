package link.zwanan.zwananblog.controller;

import link.zwanan.zwananblog.common.RestBean;
import link.zwanan.zwananblog.service.SettingService;
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
