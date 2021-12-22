package com.futurezone.authsrv.service;

import com.futurezone.authsrv.model.Role;
import com.futurezone.authsrv.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void updateRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRole(Integer id) {
        return roleRepository.getOne(id);
    }

    /*@Override
    public List<Role> getRoleByUser(User user) {
        return roleRepository.findRolesByUserList(user);
    }*/
}
