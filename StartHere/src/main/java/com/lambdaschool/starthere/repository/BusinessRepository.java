package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Business;
import com.lambdaschool.starthere.models.Pickup;
import org.springframework.data.repository.CrudRepository;

public interface BusinessRepository extends CrudRepository<Business,Long>
{
}
