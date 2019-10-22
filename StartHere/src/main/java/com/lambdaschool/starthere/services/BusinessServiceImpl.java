package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.logging.Loggable;
import com.lambdaschool.starthere.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Loggable
@Service(value = "businessService")
public class BusinessServiceImpl
{
    @Autowired
    BusinessRepository businessRepository;

}
