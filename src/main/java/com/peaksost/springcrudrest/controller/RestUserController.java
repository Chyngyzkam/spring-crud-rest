package com.peaksost.springcrudrest.controller;

import com.peaksost.springcrudrest.entity.Role;
import com.peaksost.springcrudrest.entity.User;
import com.peaksost.springcrudrest.service.UserService;
import com.peaksost.springcrudrest.service.impl.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class RestUserController {
    private final UserService userService;
    private final RoleService roleService;

    public RestUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/user")
    public ResponseEntity<User> getByName(Principal principal) {
        System.out.println(principal.getName());
        try {
            return new ResponseEntity(userService.findByEmail(principal.getName()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<User> AddUser(@RequestBody User user) {
        try {
            Set<Role> roleBD = new HashSet<>();
            roleBD.add(roleService.getRoleByName("ROLE_USER"));

            user.setRoles(roleBD);


            return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PutMapping("/{byId}")
    public ResponseEntity<User> update(@PathVariable("byId") Long userId, @RequestBody User user) {
        try {
            Set<Role> roleBD = new HashSet<>();
            roleBD.add(roleService.getRoleByName("ROLE_USER"));

            user.setId(userId);
            user.setRoles(roleBD);

            return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{byId}")
    public ResponseEntity<User> delete(@PathVariable("byId") Long userId) {
        try {
            userService.deleteById(userId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
