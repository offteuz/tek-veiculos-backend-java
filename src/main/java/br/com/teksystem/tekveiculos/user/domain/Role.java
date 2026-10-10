package br.com.teksystem.tekveiculos.user.domain;

public enum Role {
    ADMIN("Administrador"),
    MANAGER("Gerente"),
    USER("Usuário");

    private final String description;

    public static Role fromDescription(String description) {
        for (Role role : Role.values()) {
            if (role.description.equals(description)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Role unkown: " + description);
    }

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
