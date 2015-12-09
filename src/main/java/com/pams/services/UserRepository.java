package com.pams.services;

import com.pams.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MattBrown on 12/8/15.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findOneByUsername(String username);
}
