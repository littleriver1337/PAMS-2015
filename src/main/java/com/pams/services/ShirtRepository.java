package com.pams.services;

import com.pams.entities.Shirt;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MattBrown on 12/19/15.
 */
public interface ShirtRepository extends CrudRepository<Shirt, Integer> {
    Shirt findOneByPrice(String price);
}
