package com.lambdaschool.starthere;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.lambdaschool.starthere.models.*;
import com.lambdaschool.starthere.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    BusinessService businessService;

    @Autowired
    VolunteerService volunteerService;

    @Autowired
    PickupService pickupService;

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("volunteer");
        Role r3 = new Role("business");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);


        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(),
                r1));
        admins.add(new UserRoles(new User(),
                r2));
        admins.add(new UserRoles(new User(),
                r3));
        // data
        ArrayList<UserRoles> business = new ArrayList<>();
        business.add(new UserRoles(new User(),
                r3));
//        datas.add(new UserRoles(new User(),
//                r2));
        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                r2));

        User u1 = new User("business", "business@lambdaschool.local",
                "password",
                business, "Charlie Brown", "1 Main st.", "Trenton", "NJ", "07222", "business.com");
//        u1.getUseremails()
//          .add(new Useremail(u1,
//                             "admin@email.local"));

        u1=userService.save(u1);
        Business b1 = new Business(new ArrayList<Pickup>(),u1);
        b1=businessService.save(b1);

        Pickup p1 = new Pickup("Apples", 1, "Bushel", "1 Johnny ln.", "San Francisco", "CA", "10999", null,b1);
        Pickup p2 = new Pickup("Orange Juice", 3, "Quarts", "1 Johnny ln.", "San Francisco", "CA", "10999", null,b1);
        Pickup p3 = new Pickup("Steak", 2, "T-Bones", "1 Johnny ln.", "San Francisco", "CA", "10999", null,b1);
        p1=pickupService.save(p1);
        p2=pickupService.save(p2);
        p3=pickupService.save(p3);

        User u2 = new User("volunteer","lucy@lambdaschool.local",
                "1234567",users,"Lucy VanPelt");
        u2=userService.save(u2);
        Volunteer v1 = new Volunteer(u2,new ArrayList<Pickup>());
        v1=volunteerService.save(v1);
        p1.setVolunteer(v1);

        User u3 = new User("volunteer","linus@lambdaschool.local",
                "volunteer",users,"Linus VanPelt");
        u3=userService.save(u3);
        Volunteer v2 = new Volunteer(u3,new ArrayList<Pickup>());
        v2=volunteerService.save(v2);
        p2.setVolunteer(v2);

//        users = new ArrayList<>();
//        users.add(new UserRoles(new User(),
//                                r2));
//        User u4 = new User("puttat",
//                           "password",
//                           "puttat@school.lambda",
//                           users);
        //        u4.getUseremails()
//          .add(new Useremail(u4,
//                             "barnbarn@email.local"));
//        userService.save(u4);
//
//        users = new ArrayList<>();
//        users.add(new UserRoles(new User(),
//                                r2));
//        User u5 = new User("misskitty",
//                           "password",
//                           "misskitty@school.lambda",
//                           users);
//        userService.save(u5);


        // using JavaFaker create a bunch of regular users
        // https://www.baeldung.com/java-faker
        // https://www.baeldung.com/regular-expressions-java

//        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
//                                                                    new RandomService());
//        Faker nameFaker = new Faker(new Locale("en-US"));
//
//        for (int i = 0; i < 100; i++)
//        {
//            new User();
//            User fakeUser;
//
//            users = new ArrayList<>();
//            users.add(new UserRoles(new User(),
//                                    r2));
//            fakeUser = new User(nameFaker.name()
//                                         .username(),
//                                "password",
//                                nameFaker.internet()
//                                         .emailAddress(),
//                                users);
//            fakeUser.getUseremails()
//                    .add(new Useremail(fakeUser,
//                                       fakeValuesService.bothify("????##@gmail.com")));
//            userService.save(fakeUser);
//        }
    }
}