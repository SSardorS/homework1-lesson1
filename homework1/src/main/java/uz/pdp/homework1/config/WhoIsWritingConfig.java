package uz.pdp.homework1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;

@Configuration
@EnableJpaAuditing
public class WhoIsWritingConfig {
    @Bean
    AuditorAware<Long> auditorAware(){
        return new AuditingListener();
    }
}
