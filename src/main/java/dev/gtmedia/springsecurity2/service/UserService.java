package dev.gtmedia.springsecurity2.service;

import dev.gtmedia.springsecurity2.entity.User;
import dev.gtmedia.springsecurity2.entity.UserPrincipal;
import dev.gtmedia.springsecurity2.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s)
                .map(UserPrincipal::new)
                .orElseThrow(()->new UsernameNotFoundException("Username/Password is incorrect"));
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
}
