package com.pams.services;

import com.pams.entities.Pant;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MattBrown on 12/20/15.
 */
public interface PantRepository extends CrudRepository<Pant, Integer> {
    Pant findOneByMaker(String maker);
}
