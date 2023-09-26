package top.zwanan.www.service.Impl;

import top.zwanan.www.entity.Setting;
import top.zwanan.www.repository.SettingRepository;
import top.zwanan.www.service.SettingService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SettingServiceImpl implements SettingService {

    final SettingRepository settingRepository;

    public SettingServiceImpl(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Override
    public Map<String, String> getSettings() {
        return settingRepository
                .findAll().stream()
                .collect(Collectors.toMap(Setting::getName, Setting::getValue));
    }
}
