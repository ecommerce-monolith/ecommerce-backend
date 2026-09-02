package com.huyhn.ecommerce_backend.config.auditing;

import com.huyhn.ecommerce_backend.shared.constants.AuthoritiesConstants;
import com.huyhn.ecommerce_backend.shared.utils.SecurityUtils;
import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaAuditListener implements AuditorAware<String> {

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtils.getCurrentUserLogin().orElse(AuthoritiesConstants.SYSTEM));
    }
}
