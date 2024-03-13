package com.example.springBootProfile;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JavaConfig
{
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "true")
    @Bean
    public SystemProfile devProfile() {
        return new DevProfile();
    }
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "false")
    @Bean
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
