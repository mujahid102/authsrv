package com.futurezone.authsrv.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {

    private static final long serialVersionUID = -3531439484732724601L;

    private final String fullName;
    private final Long companyId;
    private final String email;
    private final String dbName;

    /*public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }*/

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities
            , String fullName, Long companyId, String email, String dbName) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.companyId = companyId;
        this.fullName = fullName;
        this.email = email;
        this.dbName = dbName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getDbName() {
        return dbName;
    }
}
