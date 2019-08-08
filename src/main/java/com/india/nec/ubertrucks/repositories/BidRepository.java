package com.india.nec.ubertrucks.repositories;

import com.india.nec.ubertrucks.models.Bid;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BidRepository extends MongoRepository<Bid, ObjectId> {
}
