package com.india.nec.ubertrucks.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bids")
public class Bid {

    @Id
    private ObjectId _id;
    private ObjectId serviceProviderId;
    private ObjectId orderId;
    private Integer bidValue;
    private boolean bidStatus=false;//particular bid rejected or accepted by the customer

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public ObjectId getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(ObjectId serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public ObjectId getOrderId() {
        return orderId;
    }

    public void setOrderId(ObjectId orderId) {
        this.orderId = orderId;
    }

    public Integer getBidValue() {
        return bidValue;
    }

    public void setBidValue(Integer bidValue) {
        this.bidValue = bidValue;
    }

    public boolean isBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(boolean bidStatus) {
        this.bidStatus = bidStatus;
    }
}
