package io.jose827corrza.github.pepepizza.persistence.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditUsername implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //It should never be able to CRUD a pizza without being auth but, in case of...
        if(authentication == null || !authentication.isAuthenticated()){
            return null;
        }
        String username = authentication.getPrincipal().toString();

        return Optional.of(username);
    }
}
