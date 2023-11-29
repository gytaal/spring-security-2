package dev.gtmedia.springsecurity2.utils;

import dev.gtmedia.springsecurity2.entity.User;
import dev.gtmedia.springsecurity2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public DBInitializer(PasswordEncoder passwordEncoder, UserService userService) {
        logger.info("DBInitializer running");
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setUsername("gerald");
        user1.setPassword(passwordEncoder.encode("1234"));
        user1.setRoles("USER ADMIN");

        User user2 = new User();
        user2.setUsername("kenji");
        user2.setPassword(passwordEncoder.encode("1234"));
        user2.setRoles("USER");

        userService.addUser(user1);
        userService.addUser(user2);
    }
}
