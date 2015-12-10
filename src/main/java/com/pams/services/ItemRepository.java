package com.pams.services;

import com.pams.entities.Item;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MattBrown on 12/9/15.
 */
public interface ItemRepository extends CrudRepository<Item, Integer> {
}
