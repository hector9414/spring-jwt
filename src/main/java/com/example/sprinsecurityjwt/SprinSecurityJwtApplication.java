package com.example.sprinsecurityjwt;

import com.example.sprinsecurityjwt.dao.UserDao;
import com.example.sprinsecurityjwt.models.Erole;
import com.example.sprinsecurityjwt.models.Roles;
import com.example.sprinsecurityjwt.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class SprinSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprinSecurityJwtApplication.class, args);
    }

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;
//    @Bean
//    CommandLineRunner init(){
//        return args -> {
//            Users user = Users.builder()
//                    .email("mail@mail.com")
//                    .username("jedi")
//                    .password(passwordEncoder.encode("1234"))
//                    .roles(Set.of(Roles.builder()
//                            .name(Erole.valueOf(Erole.ADMIN.name())).build()))
//                    .build();
//
//            userDao.save(user);
//        };
//    }
}
