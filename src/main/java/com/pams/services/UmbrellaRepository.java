package com.pams.services;

import com.pams.entities.Umbrella;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MattBrown on 12/20/15.
 */
public interface UmbrellaRepository extends CrudRepository<Umbrella, Integer> {
    Umbrella findOneByMaker(String maker);
}
