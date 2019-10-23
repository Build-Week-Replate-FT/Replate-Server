package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.logging.Loggable;
import com.lambdaschool.starthere.models.Business;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.services.BusinessService;
import com.lambdaschool.starthere.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Loggable
@RestController
@RequestMapping("/business")
public class BusinessController
{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    BusinessService businessService;

    @Autowired
    UserService userService;

    //VOLUNTEER LINKS

    @GetMapping(value = "/businesses",
            produces = {"application/json"})
    public ResponseEntity<?> listAllBusinesses(HttpServletRequest request,
                                          @PageableDefault(page = 0,
                                                  size = 5)
                                                  Pageable pageable)
    {
        List<User> myBusinesses = userService.finAllByUsertpe(pageable,"business");
        return new ResponseEntity<>(myBusinesses,
                HttpStatus.OK);
    }

//        @GetMapping(value = "/pickups/{id}",
//            produces = {"application/json"})
//    public ResponseEntity<?> listPickupsByBusiness()
//    {
//
//        List<Business> myBusinesses = businessService.findAll(pageable);
//        return new ResponseEntity<>(myBusinesses,
//                HttpStatus.OK);
//    }
}
