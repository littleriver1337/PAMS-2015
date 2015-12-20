package com.pams.services;

import com.pams.entities.Bag;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MattBrown on 12/19/15.
 */
public interface BagRepository extends CrudRepository<Bag, Integer> {
}
