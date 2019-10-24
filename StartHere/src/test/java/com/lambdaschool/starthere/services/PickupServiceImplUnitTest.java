package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.StartHereApplication;
import com.lambdaschool.starthere.models.Business;
import com.lambdaschool.starthere.models.Pickup;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.models.UserRoles;
import net.bytebuddy.asm.Advice;
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
public class PickupServiceImplUnitTest
{
    @Autowired
    PickupService pickupService;

    @Autowired
    VolunteerService volunteerService;

    @Autowired
    BusinessService businessService;

    @Autowired
    UserService userService;

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
        assertEquals(3, pickupService.findAll(Pageable.unpaged()).size());
    }

    @Test
    public void B_save()
    {
        ArrayList<UserRoles> roles = new ArrayList<>();

        User u1 = new User("business", "tiger@school.lambda",
            "ILuvMath!",
                roles, "Tiger Mitchell", "1 Main st.", "Trenton", "NJ", "07222", "business.com");
        u1=userService.save(u1);

        Business b1 = new Business(new ArrayList<Pickup>(),u1);
        b1.setUserid(u1.getUserid());
        b1=businessService.save(b1);

        Pickup p1 = new Pickup("Marshmallows", 1, "Bushel", "10 Pumpkinpatch ln.", "Columbus", "OH", "45678", null,b1);
        Pickup saveP1 = pickupService.save(p1);

        assertEquals("Marshmallows", saveP1.getFoodtype());
    }

    @Test
    public void C_update()
    {

    }

    @Test
    public void D_delete()
    {

    }

    @Test
    public void E_updatePickupVolunteer()
    {
    }
}
