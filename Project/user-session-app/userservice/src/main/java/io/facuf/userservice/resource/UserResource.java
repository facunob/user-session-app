package io.facuf.userservice.resource;

import io.facuf.userservice.domain.User;
import io.facuf.userservice.security.Permission;
import io.facuf.userservice.security.Role;
import io.facuf.userservice.service.UserSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

    private final UserSerivce userSerivce;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userSerivce.getAllUSers());
    }

    @GetMapping("/asd")
    @PreAuthorize("hasAuthority(\"" + Permission.User.DELETE + "\")")
    public ResponseEntity<String> asd() {
        return ResponseEntity.ok("asd");
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setRole(Role.ROLE_USER);
        User savedUser = userSerivce.saveUser(user);
        return ResponseEntity.ok().body(savedUser);
    }

}
