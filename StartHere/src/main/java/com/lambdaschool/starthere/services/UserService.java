package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService
{
    UserDetails loadUserByUsername(String username);

    List<User> findAll(Pageable pageable);

    List<User> finAllByUsertpe(Pageable pageable,String userType);

    List<User> findByNameContaining(String username,
                                    Pageable pageable);

    User findUserById(long id);

    User findByName(String name);

    void delete(long id);

    User save(User user);

    User update(User user,
                long id,
                boolean isAdmin);

    void deleteUserRole(long userid,
                        long roleid);

    void addUserRole(long userid,
                     long roleid);

    User findMe();
}