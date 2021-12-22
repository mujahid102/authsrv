package com.futurezone.authsrv.repository;


import com.futurezone.authsrv.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    //User getUserByUserName(String userName);
}
