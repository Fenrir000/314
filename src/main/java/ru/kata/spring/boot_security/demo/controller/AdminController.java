package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final RoleService roleService;

    public AdminController(UserService userService, PasswordEncoder encoder, RoleService roleService) {
        this.userService = userService;
        this.encoder = encoder;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin-all";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id).get());
        model.addAttribute("allRoles", roleService.findAllRoles());
        return "admin-edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("rolesCheckBox") List<String> selectedRoles) {
        Set<Role> roles = selectedRoles.stream()
                .map(s -> roleService.findRoleByRoleName("ROLE_" + s))
                .collect(Collectors.toSet());

//        if (selectedRoles.contains("ADMIN")) {
//            roles.add(roleService.findRoleByRoleName("ROLE_USER"));
//        }

        user.setRoles(roles);

        userService.updateUser(user);
        return "admin-show";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String formCreateUser(@ModelAttribute("user") User user, Model model) {

        model.addAttribute(new User());
        model.addAttribute("allRoles", roleService.findAllRoles());
        return "admin-new";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("rolesCheckBox") List<String> selectedRoles) {
        Set<Role> roles = selectedRoles.stream()
                .map(s -> roleService.findRoleByRoleName("ROLE_" + s))
                .collect(Collectors.toSet());

//        if (selectedRoles.contains("ADMIN")) {
//            roles.add(roleService.findRoleByRoleName("ROLE_USER"));
//        }

        user.setRoles(roles);


        userService.save(user);
        return "redirect:/admin";
    }


}
