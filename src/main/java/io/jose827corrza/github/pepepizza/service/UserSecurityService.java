package io.jose827corrza.github.pepepizza.service;

import io.jose827corrza.github.pepepizza.persistence.entity.UserEntity;
import io.jose827corrza.github.pepepizza.persistence.entity.UserRoleEntity;
import io.jose827corrza.github.pepepizza.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User: "+ username + "was not found"));

        String[] roles = user.getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new);

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(this.grantedAuthorities(roles)) // v3, to allow specific access to specific action
//                .roles(roles) // v2
//                .roles("ADMIN") // v1
                .accountLocked(user.getLocked())
                .disabled(user.getDisabled())
                .build();
    }

    private String[] getAuthorities(String role) {
        if("ADMIN".equals(role) || "CUSTOMER".equals(role)){
            return new String[] {"SUMMARY_ORDER"};
        }
        return new String[] {};
    }

    private List<GrantedAuthority> grantedAuthorities(String[] roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

        for (String role: roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

            for (String authority: this.getAuthorities(role)
                 ) {
                authorities.add(new SimpleGrantedAuthority(authority));
            }
            
        }

        return authorities;
    }
}
