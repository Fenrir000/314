package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
public class AdmController {

    private final UserService userService;
    private final RoleService roleService;


    public AdmController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String showAllUsers(Model model, Principal principal, @ModelAttribute("newUser") User user) {
        model.addAttribute("localUser", userService.findByEmail(principal.getName()).get());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("allRoles", roleService.findAllRoles());
        return "admin-showAll";
    }


    @PostMapping("/new")
    public String createUser(@ModelAttribute("newUser")User user,@RequestParam("rolesCheckBox") List<String> selectedRoles) {
                Set<Role> roles = selectedRoles.stream()
                .map(s -> roleService.findRoleByRoleName("ROLE_" + s))
                .collect(Collectors.toSet());


        user.setRoles(roles);
        userService.save(user);
        return "redirect:/admin";
    }


    @PatchMapping("/update")
    public String updateUser(@ModelAttribute("oneUser") User user,@RequestParam("rolesCheckBox") List<String> selectedRoles) {
        System.out.println(user.getEmail());
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());

        Set<Role> roles = selectedRoles.stream()
                .map(s -> roleService.findRoleByRoleName("ROLE_" + s))
                .collect(Collectors.toSet());


        user.setRoles(roles);

        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}