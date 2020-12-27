package com.vernum.gateway.security;


import com.vernum.gateway.Entity.User;
import com.vernum.gateway.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("vernum")
public class VernumUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public VernumUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() ->
                new UsernameNotFoundException(String.format("User %s could not be found",username)));

        return user.map(VernumUserDetails::new).get();
    }
}
