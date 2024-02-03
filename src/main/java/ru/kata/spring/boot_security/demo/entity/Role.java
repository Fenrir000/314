package ru.kata.spring.boot_security.demo.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String role;
    @ManyToMany(mappedBy = "roles")
    private Set<User> setUser;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(int id, String role, Set<User> setUser) {
        this.id = id;
        this.role = role;
        this.setUser = setUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getSetUser() {
        return setUser;
    }

    public void setSetUser(Set<User> setUser) {
        this.setUser = setUser;
    }

    @Override
    public String getAuthority() {
        return getRole();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return id == role.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
