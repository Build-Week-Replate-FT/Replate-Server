package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.logging.Loggable;
import com.lambdaschool.starthere.models.Pickup;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.services.PickupService;
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
@RequestMapping("/pickups")
public class PickupController
{
    @Autowired
    PickupService pickupService;

    @GetMapping(value = "/pickups",
            produces = {"application/json"})
    public ResponseEntity<?> listAllUsers(HttpServletRequest request,
                                          @PageableDefault(page = 0,
                                                  size = 5)
                                                  Pageable pageable)
    {

        List<Pickup> myPickups = pickupService.findAll(pageable);
        return new ResponseEntity<>(myPickups, HttpStatus.OK);

    }
}
