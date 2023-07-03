package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String roleName) {
        TypedQuery<Role> roleTypedQuery = (entityManager.createQuery("SELECT r FROM Role r " +
                "WHERE r.roleName= :roleName", Role.class));
        roleTypedQuery.setParameter("roleName", roleName);
        return roleTypedQuery.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public Set<Role> getListRoleById(List<Long> roles) {
        TypedQuery<Role> roleTypedQuery = entityManager.createQuery
                ("select r from Role r  where r.id in:role", Role.class);
        roleTypedQuery.setParameter("role", roles);
        return new HashSet<>(roleTypedQuery.getResultList());
    }

    @Override
    public Role getRoleById(Long id) {
        return (Role) entityManager.createQuery("SELECT r FROM Role r WHERE r.id = :id")
                .setParameter("id", id).getResultList().get(0);

    }


    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList());
    }

    @Override
    public void addRole(Role role) {
        entityManager.merge(role);

    }


}
