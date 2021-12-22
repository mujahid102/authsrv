package com.futurezone.authsrv.repository;

import com.futurezone.authsrv.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role, Integer> {

}
