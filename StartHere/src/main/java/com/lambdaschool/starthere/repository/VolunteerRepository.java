package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Pickup;
import com.lambdaschool.starthere.models.Volunteer;
import org.springframework.data.repository.CrudRepository;

public interface VolunteerRepository extends CrudRepository<Volunteer,Long>
{
    Volunteer findById(long id);
}
