package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.logging.Loggable;
import com.lambdaschool.starthere.models.Pickup;
import com.lambdaschool.starthere.models.Role;
import com.lambdaschool.starthere.repository.PickupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Loggable
@Service(value = "pickupService")
public class PickupServiceImpl implements PickupService
{
    @Autowired
    PickupRepository pickupRepository;

    @Override
    public List<Pickup> findAll(Pageable pageable)
    {
        List<Pickup> list = new ArrayList<>();
        pickupRepository.findAll(pageable)
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Pickup save(Pickup pickup)
    {
        return pickupRepository.save(pickup);
    }
}
