package link.zwanan.zwananblog.service.Impl;

import link.zwanan.zwananblog.entity.Setting;
import link.zwanan.zwananblog.repository.SettingRepository;
import link.zwanan.zwananblog.service.SettingService;
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
