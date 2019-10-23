package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long>
{
    User findByEmail(String username);

    List<User> findByEmailContainingIgnoreCase(String email,
                                                  Pageable pageable);

    List<User> findAllByUserType(Pageable pageable, String userType);

}
