package com.peaksost.springcrudrest.service.impl;

import com.peaksost.springcrudrest.entity.Role;
import com.peaksost.springcrudrest.entity.User;
import com.peaksost.springcrudrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class ServiceSecurity implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public ServiceSecurity(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByEmail(s);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
for (Role role: user.getRoles()){
grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
}
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), grantedAuthorities);
    }
}
