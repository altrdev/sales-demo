package com.sales.taxes.demo.repository;

import com.sales.taxes.demo.entity.Basket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
@Repository
public interface BasketRepository extends MongoRepository<Basket, String> {

    Optional<Basket> findOneByBasketId(String basketId);

}
