package com.pelosi.notes.config.audit;

import org.springframework.data.domain.AuditorAware;

import java.time.LocalDate;
import java.util.Optional;

public class AuditorConfig implements AuditorAware<LocalDate> {
    @Override
    public Optional<LocalDate> getCurrentAuditor() {
        return Optional.of(LocalDate.now());
    }
}
