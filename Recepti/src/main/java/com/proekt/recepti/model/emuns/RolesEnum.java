package com.proekt.recepti.model.emuns;

import org.springframework.security.core.GrantedAuthority;

public enum RolesEnum implements GrantedAuthority {
    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
