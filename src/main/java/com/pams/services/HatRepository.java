package com.pams.services;

import com.pams.entities.Hat;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MattBrown on 12/19/15.
 */
public interface HatRepository extends CrudRepository<Hat, Integer> {
    Hat findOneByPrice(String price);
}
