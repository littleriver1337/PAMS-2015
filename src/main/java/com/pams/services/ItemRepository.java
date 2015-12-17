package com.pams.services;

import com.pams.entities.Club;
import com.pams.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by MattBrown on 12/9/15.
 */
public interface ItemRepository extends CrudRepository<Club, Integer> {
    Club findOneBySerialNumber(int serialNumber);
    Club findOneByMaker(String maker);
    List<Club> findAllByIsAuthentic(boolean isAuthentic);
    List<Club> findAllByClubType (String clubType);
    List<Club> findAllByMaker (String maker);
    List<Club> findAllByYear (int year);
    List<Club> findAllByLieAngle(String lieAngle);
}
