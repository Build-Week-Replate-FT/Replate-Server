package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.logging.Loggable;
import com.lambdaschool.starthere.models.Pickup;
import com.lambdaschool.starthere.repository.PickupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Loggable
@Service(value = "pickupService")
public class PickupServiceImpl implements PickupService
{
    @Autowired
    PickupRepository pickupRepository;

    @Override
    public Pickup save(Pickup pickup)
    {
        return pickupRepository.save(pickup);
    }
}
