package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();

    void saveRole(Role role);

    List<Role> findRoleByName(String roleName);

    Role findRoleById(Long id);

    Role findRoleByRoleName(String roleName);
}