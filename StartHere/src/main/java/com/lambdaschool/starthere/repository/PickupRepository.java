package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Pickup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PickupRepository extends PagingAndSortingRepository<Pickup,Long>
{
}
