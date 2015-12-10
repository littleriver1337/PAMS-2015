package com.pams.services;

import com.sun.tools.javac.jvm.Items;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MattBrown on 12/9/15.
 */
public interface ItemRepository extends CrudRepository<Items, Integer> {
}
