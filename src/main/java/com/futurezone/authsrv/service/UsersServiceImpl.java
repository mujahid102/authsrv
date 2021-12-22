package com.futurezone.authsrv.service;

import com.futurezone.authsrv.model.CustomUser;
import com.futurezone.authsrv.model.Role;
import com.futurezone.authsrv.model.User;
import com.futurezone.authsrv.repository.RoleRepository;
import com.futurezone.authsrv.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsersServiceImpl implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    RoleRepository roleRepository;

    public void addUser(User user) {
        usersRepository.save(user);
    }

    public void updateUser(User user) {
        usersRepository.save(user);
    }

    public User getUser(Long id) {
        return usersRepository.getOne(id);
    }

    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    public void deleteUsers(User user) {
        usersRepository.delete(user);
    }

    public User findByUserName(String username) {
        return usersRepository.findByUserName(username);
    }


    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //Reading extra parameter from login page
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String companyId = request.getParameter("loginCompany");
        String dbName   = "suvastuapp";

        User user = findByUserName(userName);

        //Extend Spring security User object to adding extra field
        CustomUser customUser = null;
        //Long companyId = 1L;
        //System.out.println(user.getPassword());
        //System.out.println("users to string: " + user.toString());
        //System.out.println("roles to string: " + user.getRoles().toString());

        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName().toString()));
            role.getPermissions().forEach(permission -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
            });
        }
        customUser = new CustomUser(String.valueOf(user.getId()), user.getPassword(), true, true, true, true, grantedAuthorities
                , user.getFullName(), Long.parseLong(companyId), user.getEmail(), dbName);

        /* Same code as above code using functional programming
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getPermissions().forEach(permission -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
            });
        });*/
        //System.out.println("user detail service out 0");
        //return new org.springframework.security.core.userdetails.User(String.valueOf(user.getId()), user.getPassword(), grantedAuthorities);
        return customUser;
    }

    /* private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    } */

    /*Method Not used*/
    /*private List<SimpleGrantedAuthority> getAuthority(User user) {
        List<Role> roles = roleRepository.findRolesByUserList(user);
        List<String> roleList = new ArrayList<String>();

        for (Role role: roles){
            roleList.add(role.getName());
            //System.out.println(role.getName());
        }

        //System.out.println(roleList.toString());

        if (roleList != null){
            return Arrays.asList(new SimpleGrantedAuthority(roleList.toString()));
        } else {
          return null;
        }
    }*/
}
