package com.pams.services;

import com.pams.entities.Clubs;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MattBrown on 12/9/15.
 */
public interface ItemRepository extends CrudRepository<Clubs, Integer> {
}
