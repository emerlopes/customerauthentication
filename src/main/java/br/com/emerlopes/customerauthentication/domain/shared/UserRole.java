package br.com.emerlopes.customerauthentication.domain.shared;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    GUEST("ROLE_GUEST");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public static UserRole fromRole(String role) {
        for (UserRole userRole : values()) {
            if (userRole.role.equals(role)) {
                return userRole;
            }
        }
        return null;
    }

}

