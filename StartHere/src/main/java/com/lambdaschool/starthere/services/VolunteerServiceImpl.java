package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.logging.Loggable;
import com.lambdaschool.starthere.models.Volunteer;
import com.lambdaschool.starthere.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Loggable
@Service(value = "volunteerService")
public class VolunteerServiceImpl implements VolunteerService
{
    @Autowired
    VolunteerRepository volunteerRepository;

    @Override
    public Volunteer save(Volunteer volunteer)
    {
        return volunteerRepository.save(volunteer);
    }
}
