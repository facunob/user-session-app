package io.facuf.userservice.service;

import io.facuf.userservice.domain.User;
import io.facuf.userservice.repository.UserRepository;
import io.facuf.userservice.security.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class UserSerivce implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUSers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(s)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private AuthUser createSpringSecurityUser(User user) {
        List<GrantedAuthority> grantedAuthorities = Stream
                .concat(user.getPermissions().stream(), Arrays.asList(user.getRole().name()).stream())
                .map(authorityStr -> new SimpleGrantedAuthority(authorityStr))
                .collect(Collectors.toList());

        return new AuthUser(user.getUsername(), user.getPassword(), user.getId(), grantedAuthorities);
    }

}
