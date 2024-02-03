package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.repository.RoleDAO;

import java.util.List;

@Service

public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;


    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAllRoles() {
        return roleDAO.findAllRole();
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleDAO.saveRole(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findRoleByName(String roleName) {
        return roleDAO.findRole(roleName);
    }

    @Override
    @Transactional(readOnly = true)
    public Role findRoleById(Long id) {
        return roleDAO.findRoleById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Role findRoleByRoleName(String roleName) {
        return roleDAO.findRoleByRoleName(roleName);
    }
}
