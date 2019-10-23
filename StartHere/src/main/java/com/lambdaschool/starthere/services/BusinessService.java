package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Business;
import com.lambdaschool.starthere.models.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BusinessService
{
    List<Business> findAll(Pageable pageable);

    Business save(Business business);
}
