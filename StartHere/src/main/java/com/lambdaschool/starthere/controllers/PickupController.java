package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.logging.Loggable;
import com.lambdaschool.starthere.models.Pickup;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.services.PickupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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

    @PostMapping(value = "/add",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewUser(HttpServletRequest request,
                                        @Valid
                                        @RequestBody
                                                Pickup newPickup) throws URISyntaxException
    {

        newPickup = pickupService.save(newPickup);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{pickupid}")
                .buildAndExpand(newPickup.getPickupid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updatePickup(HttpServletRequest request,
                                        @RequestBody
                                                Pickup updatePickup,
                                        @PathVariable
                                                long id)
    {
        pickupService.update(updatePickup,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //POST /pickups/{pickupid}/{volunteerid}
    @PostMapping(value = "/{pickupid}/{volunteerid}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> AssignBookToAuthor(@PathVariable long pickupid,
                                                @PathVariable long volunteerid)
    {
        Pickup newPickup=pickupService.updatePickupVolunteer(pickupid,volunteerid);

        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<>(newPickup,responseHeaders,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePickupById(HttpServletRequest request,
                                              @PathVariable
                                                      long id)
    {
        pickupService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
