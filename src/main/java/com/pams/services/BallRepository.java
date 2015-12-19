package com.pams.services;

import com.pams.entities.Ball;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MattBrown on 12/19/15.
 */
public interface BallRepository extends CrudRepository <Ball, Integer> {
    Ball findOneByPrice(String price);
}
