package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Pickup;
import com.lambdaschool.starthere.models.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PickupService
{
    List<Pickup> findAll(Pageable pageable);

    Pickup save(Pickup pickup);
}
