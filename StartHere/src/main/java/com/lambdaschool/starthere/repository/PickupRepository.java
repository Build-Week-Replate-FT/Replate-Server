package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Pickup;
import org.springframework.data.repository.CrudRepository;

public interface PickupRepository extends CrudRepository<Pickup,Long>
{
}
