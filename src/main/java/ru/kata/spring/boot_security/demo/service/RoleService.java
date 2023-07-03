package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Role getRoleById(Long id);

    Set<Role> getAllRoles();

    Set<Role> getListRoleById(List<Long> roles);

    void addRole(Role role);

    Role getRoleByName(String roleName);

}
