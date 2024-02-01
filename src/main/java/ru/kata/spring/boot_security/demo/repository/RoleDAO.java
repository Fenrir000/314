package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> findAllRole();

    void saveRole(Role role);

    List<Role> findRole(String roleUser);

    Role findRoleById(Long id);

    Role findRoleByRoleName(String roleName);
}
