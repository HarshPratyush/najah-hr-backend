package in.co.najah.najahhr.entity.audit;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl  implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return "anynomus";
//        }
//
//        return ((UserDetails) authentication.getPrincipal()).getUser();

        return Optional.of("anonymous");
    }
}