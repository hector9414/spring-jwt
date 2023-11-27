package com.example.sprinsecurityjwt.controllers;

import com.example.sprinsecurityjwt.controllers.dto.UserDto;
import com.example.sprinsecurityjwt.dao.UserDao;
import com.example.sprinsecurityjwt.models.Erole;
import com.example.sprinsecurityjwt.models.Roles;
import com.example.sprinsecurityjwt.models.Users;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World not Secured";
    }

    @GetMapping("/hellosecured")
    public String helloSecured(){
        return "Hello World Secured";
    }

    @GetMapping("/users")
    public List<Users> getUsers(){
        return (List<Users>) userDao.findAll();
    }

    @PostMapping("/createuser")
    public ResponseEntity createUser(@Valid @RequestBody UserDto userDto){

        Set<Roles> roles = userDto.getRoles()
                .stream()
                .map(role ->Roles.builder()
                        .name(Erole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        Users user = Users.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .roles(roles)
                .build();

        userDao.save(user);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/deleteuser")
    public String deleteUser(@RequestParam String id){

        userDao.deleteById(Long.parseLong(id));
        return "se ha borrado el user con id ".concat(id);
    }

}
