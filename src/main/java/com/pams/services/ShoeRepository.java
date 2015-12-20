package com.pams.services;

import com.pams.entities.Shoe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MattBrown on 12/20/15.
 */
public interface ShoeRepository extends CrudRepository<Shoe, Integer> {
    Shoe findOneByMaker(String maker);
}
