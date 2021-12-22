package com.futurezone.authsrv.service;

import com.futurezone.authsrv.model.Role;

public interface RoleService {

    public void addRole(Role role);
    public void updateRole(Role role);
    public Role getRole(Integer id);
    /*public List<Role> getRoleByUser(User user);*/
}
