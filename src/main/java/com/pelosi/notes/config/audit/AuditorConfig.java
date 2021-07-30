package com.pelosi.notes.config.audit;

import org.springframework.data.domain.AuditorAware;

import java.time.LocalDateTime;
import java.util.Optional;

public class AuditorConfig implements AuditorAware<LocalDateTime> {
    @Override
    public Optional<LocalDateTime> getCurrentAuditor() {
        return Optional.of(LocalDateTime.now());
    }
}
