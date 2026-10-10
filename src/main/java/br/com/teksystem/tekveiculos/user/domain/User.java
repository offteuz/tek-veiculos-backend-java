package br.com.teksystem.tekveiculos.user.domain;

import br.com.teksystem.tekveiculos.shared.domain.contracts.Activatable;

import java.util.Objects;

public class User implements Activatable {
    private Long id;

    private Name name;

    private Email email;

    private Password password;

    private Role role;

    private Boolean active;

    // Constructor
    public User() {
    }

    public User(Long id, Name name, Email email, Password password, Role role, Boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public Boolean getActive() {
        return active;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }

    // Methods
    @Override
    public Boolean active() {
        return this.active;
    }

    @Override
    public void ensureIsActive() {
        Activatable.super.ensureIsActive();
    }

    public void changeName(Name newName) {
        ensureIsActive();

        this.name = newName;
    }

    public void changeEmail(Email newEmail) {
        ensureIsActive();

        this.email = newEmail;
    }

    public void changePassword(Password newPassword) {
        ensureIsActive();

        this.password = newPassword;
    }

    public void changeRole(Role newRole) {
        ensureIsActive();

        this.role = newRole;
    }

    public void changeActive(Boolean newActive) {
        ensureIsActive();

        this.active = newActive;
    }
}
