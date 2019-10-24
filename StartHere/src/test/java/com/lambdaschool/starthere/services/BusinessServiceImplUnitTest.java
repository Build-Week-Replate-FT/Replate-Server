package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.StartHereApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartHereApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BusinessServiceImplTest
{
    @Autowired
    BusinessService businessService;

    @Before
    public void setUp() throws Exception
    {
        
    }

    @After
    public void tearDown() throws Exception
    {
        
    }

    @Test
    public void A_findAllPageable()
    {
        System.out.println();
        assertEquals(1, businessService.findAll(Pageable.unpaged()).size());
    }
}