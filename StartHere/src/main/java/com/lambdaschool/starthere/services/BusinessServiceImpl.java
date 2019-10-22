package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.logging.Loggable;
import com.lambdaschool.starthere.models.Business;
import com.lambdaschool.starthere.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Loggable
@Service(value = "businessService")
public class BusinessServiceImpl implements BusinessService
{
    @Autowired
    BusinessRepository businessRepository;

    @Override
    public Business save(Business business)
    {
        return businessRepository.save(business);
    }
}
