package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceFoundException;
import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.logging.Loggable;
import com.lambdaschool.starthere.models.Pickup;
import com.lambdaschool.starthere.models.Role;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.models.Volunteer;
import com.lambdaschool.starthere.repository.PickupRepository;
import com.lambdaschool.starthere.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Loggable
@Service(value = "pickupService")
public class PickupServiceImpl implements PickupService
{
    @Autowired
    PickupRepository pickupRepository;

    @Autowired
    VolunteerRepository volunteerRepository;

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

    @Override
    public Pickup update(Pickup updatePickup, long id)
    {

        Pickup newPickup = pickupRepository.findById(id);

        if (id == newPickup.getPickupid())
        {
            if (updatePickup.getFoodtype() != null)
            {
                newPickup.setFoodtype(updatePickup.getFoodtype());
            }
            if (updatePickup.getQuantity() > 0)
            {
                newPickup.setQuantity(updatePickup.getQuantity());
            }
            if (updatePickup.getQuantityunit() != null)
            {
                newPickup.setQuantityunit(updatePickup.getQuantityunit());
            }
            if (updatePickup.getDeliveryaddress() != null)
            {
                newPickup.setDeliveryaddress(updatePickup.getDeliveryaddress());
            }
            if (updatePickup.getDeliverycity() != null)
            {
                newPickup.setDeliverycity(updatePickup.getDeliverycity());
            }
            if (updatePickup.getDeliverystate() != null)
            {
                newPickup.setDeliverystate(updatePickup.getDeliverystate());
            }
            if (updatePickup.getZip() != null)
            {
                newPickup.setZip(updatePickup.getZip());
            }
            return pickupRepository.save(newPickup);
        } else
        {
            throw new ResourceNotFoundException(id + " Pickup Not Found");
        }
    }

    @Override
    public void delete(long id)
    {
        pickupRepository.deleteById(id);
    }

    @Override
    public Pickup updatePickupVolunteer(long pickupid, long volunteerid)
    {
        Pickup newPickup = pickupRepository.findById(pickupid);
        Volunteer newVolunteer = volunteerRepository.findById(volunteerid);
        newPickup.setVolunteer(newVolunteer);
        pickupRepository.save(newPickup);
        return newPickup;
    }
}
