package com.example.multimodule.core.api.listener;

import com.example.multimodule.core.domain.domain.user.AdminUserGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartListener implements ApplicationListener<ApplicationStartedEvent> {
    private final String adminEmail;
    private final String adminPassword;
    private final String adminName;

    private final AdminUserGenerator adminUserGenerator;

    public ApplicationStartListener(
            @Value("${admin.email}") String adminEmail,
            @Value("${admin.password}") String adminPassword,
            @Value("${admin.name}") String adminName,
            AdminUserGenerator adminUserGenerator
    ) {
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.adminName = adminName;
        this.adminUserGenerator = adminUserGenerator;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        adminUserGenerator.generate(adminEmail, adminPassword, adminName);
    }
}
