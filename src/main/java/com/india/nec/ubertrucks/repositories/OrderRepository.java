package com.india.nec.ubertrucks.repositories;

import com.india.nec.ubertrucks.models.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, ObjectId> {

    List<Order> findAllByFinalizedOrder(boolean status);

}
