package com.india.nec.ubertrucks.repositories;

import com.india.nec.ubertrucks.models.Customer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRespository extends MongoRepository<Customer, ObjectId> {

    Optional<Customer> findByUserId(ObjectId id);

}
