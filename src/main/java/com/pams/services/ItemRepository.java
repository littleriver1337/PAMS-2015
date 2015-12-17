package com.pams.services;

import com.pams.entities.Club;
import com.pams.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MattBrown on 12/9/15.
 */
public interface ItemRepository extends CrudRepository<Club, Integer> {
    Club findOneBySerialNumber(int serialNumber);
    Club findOneByMaker(String maker);
}
