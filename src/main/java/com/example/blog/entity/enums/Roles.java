package com.example.blog.entity.enums;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.List;

public enum Roles implements GrantedAuthority {
    USER,
    ADMIN,
    DIRECTOR;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
