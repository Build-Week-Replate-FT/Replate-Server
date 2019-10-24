package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.StartHereApplication;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.models.UserRoles;
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

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartHereApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BusinessServiceImplUnitTest
{
    @Autowired
    UserService userService;

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

    @Test
    public void B_save()
    {
        ArrayList<UserRoles> datas = new ArrayList<>();
        User u1 = new User("business", "tiger@school.lambda",
                "ILuvMath!",
                datas, "Tiger Mitchell", "1 Main st.", "Trenton", "NJ", "07222", "business.com");
        User saveU2 = userService.save(u1);

        System.out.println("*** DATA ***");
        System.out.println(saveU2);
        System.out.println("*** DATA ***");

        assertEquals("tiger@school.lambda", saveU2.getEmail());
    }
}