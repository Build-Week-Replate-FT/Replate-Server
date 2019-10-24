package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Pickup;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.models.Useremail;
import com.lambdaschool.starthere.models.Volunteer;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PickupService
{
    List<Pickup> findAll(Pageable pageable);

    Pickup save(Pickup pickup);

    Pickup update(Pickup updatePickup,long id);

    void delete(long id);

//    void addVolunteer(Volunteer volunteer, Pickup pickup);

    Pickup updatePickupVolunteer(long pickupid, long volunteerid);
}
