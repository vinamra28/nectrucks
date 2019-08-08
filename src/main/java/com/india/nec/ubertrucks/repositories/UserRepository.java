package com.india.nec.ubertrucks.repositories;

import com.india.nec.ubertrucks.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByEmail(String email);

    void deleteByEmail(String email);
}
