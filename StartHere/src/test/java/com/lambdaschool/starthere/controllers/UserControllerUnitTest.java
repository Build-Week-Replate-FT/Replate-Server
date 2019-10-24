package com.lambdaschool.starthere.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.starthere.models.*;
import com.lambdaschool.starthere.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// mocking service to test controller
// Due to reliance on security, cannot test
//     getCurrentUserInfo
//     getCurrentUserName

//@RunWith(SpringRunner.class)
//@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerUnitTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private List<User> userList;

    @Before
    public void setUp() throws Exception
    {
        userList = new ArrayList<>();

        Role r1 = new Role("admin");
        r1.setRoleid(1);
        Role r2 = new Role("volunteer");
        r2.setRoleid(2);
        Role r3 = new Role("business");
        r3.setRoleid(3);


        // data, user
        ArrayList<UserRoles> business = new ArrayList<>();
        business.add(new UserRoles(new User(), r3));
        User u1 = new User("business", "business@lambdaschool.local",
                "password",
                business, "Charlie Brown", "1 Main st.", "Trenton", "NJ", "07222", "business.com");

        u1.setUserid(102);
        Business b1 = new Business(new ArrayList<Pickup>(),u1);
        u1.setBusiness(b1);
        userList.add(u1);
        Pickup p1 = new Pickup("Apples", 1, "Bushel", "1 Johnny ln.", "San Francisco", "CA", "10999", null,b1);
        Pickup p2 = new Pickup("Orange Juice", 3, "Quarts", "1 Johnny ln.", "San Francisco", "CA", "10999", null,b1);
        Pickup p3 = new Pickup("Steak", 2, "T-Bones", "1 Johnny ln.", "San Francisco", "CA", "10999", null,b1);
        List<Pickup> pickupList = new ArrayList<>();
        pickupList.add(p1);
        pickupList.add(p2);
        pickupList.add(p3);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));

        User u2 = new User("volunteer","lucy@lambdaschool.local",
                "1234567",users,"Lucy VanPelt");
        u2.setUserid(103);
        Volunteer v1 = new Volunteer(u2,new ArrayList<Pickup>());
        u2.setVolunteer(v1);
        p1.setVolunteer(v1);
        userList.add(u2);

        User u3 = new User("volunteer","linus@lambdaschool.local",
                "volunteer",users,"Linus VanPelt");
        u3.setUserid(104);
        Volunteer v2 = new Volunteer(u3,new ArrayList<Pickup>());
        u3.setVolunteer(v2);
        p2.setVolunteer(v2);
        userList.add(u3);

        System.out.println("\n*** Seed Data ***");
        for (User u : userList)
        {
            System.out.println(u);
        }
        for (Pickup p: pickupList)
        {
            System.out.println(p);
        }
        System.out.println("*** Seed Data ***\n");
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void listAllUsers() throws Exception
    {
        String apiUrl = "/users/users";

        Mockito.when(userService.findAll(any(Pageable.class))).thenReturn(userList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        // the following actually performs a real controller call
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList);

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List", er, tr);
    }
//
//    @Test
//    public void listReallyAllUsers() throws Exception
//    {
//        String apiUrl = "/users/users/all";
//
//        Mockito.when(userService.findAll(Pageable.unpaged())).thenReturn(userList);
//
//        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
//
//        // the following actually performs a real controller call
//        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
//        String tr = r.getResponse().getContentAsString();
//
//        ObjectMapper mapper = new ObjectMapper();
//        String er = mapper.writeValueAsString(userList);
//
//        System.out.println("Expect: " + er);
//        System.out.println("Actual: " + tr);
//
//        assertEquals("Rest API Returns List", er, tr);
//    }
//
//    @Test
//    public void listUsersNameContaining() throws Exception
//    {
//        String apiUrl = "/users/user/name/like/cin";
//
//        Mockito.when(userService.findByNameContaining(any(String.class), any(Pageable.class))).thenReturn(userList);
//
//        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
//
//        // the following actually performs a real controller call
//        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
//        String tr = r.getResponse().getContentAsString();
//
//        ObjectMapper mapper = new ObjectMapper();
//        String er = mapper.writeValueAsString(userList);
//
//        System.out.println("Expect: " + er);
//        System.out.println("Actual: " + tr);
//
//        assertEquals("Rest API Returns List", er, tr);
//    }
//
//    @Test
//    public void getUserById() throws Exception
//    {
//        String apiUrl = "/users/user/12";
//
//        Mockito.when(userService.findUserById(12)).thenReturn(userList.get(1));
//
//        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
//        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
//        String tr = r.getResponse().getContentAsString();
//
//        ObjectMapper mapper = new ObjectMapper();
//        String er = mapper.writeValueAsString(userList.get(1));
//
//        System.out.println("Expect: " + er);
//        System.out.println("Actual: " + tr);
//
//        assertEquals("Rest API Returns List", er, tr);
//    }
//
//    @Test
//    public void getUserByIdNotFound() throws Exception
//    {
//        String apiUrl = "/users/user/77";
//
//        Mockito.when(userService.findUserById(77)).thenReturn(null);
//
//        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
//        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
//        String tr = r.getResponse().getContentAsString();
//
//        String er = "";
//
//        System.out.println("Expect: " + er);
//        System.out.println("Actual: " + tr);
//
//        assertEquals("Rest API Returns List", er, tr);
//    }
//
//    @Test
//    public void getUserByName() throws Exception
//    {
//        String apiUrl = "/users/user/name/testing";
//
//        Mockito.when(userService.findByName("testing")).thenReturn(userList.get(0));
//
//        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
//        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
//        String tr = r.getResponse().getContentAsString();
//
//        ObjectMapper mapper = new ObjectMapper();
//        String er = mapper.writeValueAsString(userList.get(0));
//
//        System.out.println("Expect: " + er);
//        System.out.println("Actual: " + tr);
//
//        assertEquals("Rest API Returns List", er, tr);
//    }
//
//    @Test
//    public void getCurrentUserName() throws Exception
//    {
//        // requires security which we have turned off for unit test
//        // refer to integration testing for test of this method
//    }
//
//    @Test
//    public void addNewUser() throws Exception
//    {
//        String apiUrl = "/users/user";
//
//        // build a user
//        ArrayList<UserRoles> thisRole = new ArrayList<>();
//        ArrayList<Useremail> thisEmail = new ArrayList<>();
//        User u1 = new User();
//        u1.setUserid(100);
//        u1.setUsername("tiger");
//        u1.setPassword("ILuvM4th!");
//        u1.setPrimaryemail("tiger@home.local");
//        u1.setUserroles(thisRole);
//        u1.setUseremails(thisEmail);
//
//        ObjectMapper mapper = new ObjectMapper();
//        String userString = mapper.writeValueAsString(u1);
//
//        Mockito.when(userService.save(any(User.class))).thenReturn(u1);
//
//        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
//                                                  .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
//                                                  .content(userString);
//
//        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void updateUser() throws Exception
//    {
//        String apiUrl = "/users/user/{userid}";
//
//        // build a user
//        User u1 = new User();
//        u1.setUserid(100);
////        u1.setUsername("tigerUpdated");
////        u1.setPrimaryemail("home@local.home");
//        u1.setPassword("ILuvM4th!");
//
//        Mockito.when(userService.update(u1, 100L, true)).thenReturn(userList.get(0));
//
//        ObjectMapper mapper = new ObjectMapper();
//        String userString = mapper.writeValueAsString(u1);
//
//        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl, 100L)
//                                                  .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
//                                                  .content(userString);
//
//        mockMvc.perform(rb).andExpect(status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void deleteUserById()throws Exception
//    {
//        String apiUrl = "/users/user/{userid}";
//
//        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "3")
//                                                  .contentType(MediaType.APPLICATION_JSON)
//                                                  .accept(MediaType.APPLICATION_JSON);
//        mockMvc.perform(rb)
//               .andExpect(status().is2xxSuccessful())
//               .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void deleteUserRoleByIds() throws Exception
//    {
//        String apiUrl = "/users/user/{userid}/role/{roleid}";
//
//        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, 3, 2);
//
//        mockMvc.perform(rb)
//               .andExpect(status().is2xxSuccessful())
//               .andDo(MockMvcResultHandlers.print());
//    }
//
//    // @PostMapping("/user/{userid}/role/{roleid}")
//    // userService.addUserRole(userid, roleid);
//    @Test
//    public void postUserRoleByIds() throws Exception
//    {
//        String apiUrl = "/users/user/{userid}/role/{roleid}";
//
//        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl, 3, 2);
//
//        mockMvc.perform(rb)
//               .andExpect(status().is2xxSuccessful())
//               .andDo(MockMvcResultHandlers.print());
//    }
}