package com.india.nec.ubertrucks.models;

import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Document(collection = "customers")
public class Customer {

    @Id
    private ObjectId _id;

    @Indexed(unique = true, direction = IndexDirection.ASCENDING,dropDups = true)
    private ObjectId userId;

    private ObjectId currentOrder = null;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public ObjectId getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(ObjectId currentOrder) {
        this.currentOrder = currentOrder;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "_id=" + _id +
                ", userId=" + userId +
                ", currentOrder=" + currentOrder.toHexString() +
                '}';
    }
}
