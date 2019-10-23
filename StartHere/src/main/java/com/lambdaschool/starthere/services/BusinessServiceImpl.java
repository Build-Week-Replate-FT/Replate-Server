package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.logging.Loggable;
import com.lambdaschool.starthere.models.Business;
import com.lambdaschool.starthere.models.Role;
import com.lambdaschool.starthere.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Loggable
@Service(value = "businessService")
public class BusinessServiceImpl implements BusinessService
{
    @Autowired
    BusinessRepository businessRepository;

    @Override
    public List<Business> findAll(Pageable pageable)
    {
        List<Business> list = new ArrayList<>();
        businessRepository.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Business save(Business business)
    {
        return businessRepository.save(business);
    }
}
