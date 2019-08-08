package com.india.nec.ubertrucks.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private ObjectId _id;
    private String srcLoc;
    private String destLoc;
    private Date startDate;
    private Integer load;
    private String category;
    private List<ObjectId> bids = null;
    private boolean finalizedOrder=false;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getSrcLoc() {
        return srcLoc;
    }

    public void setSrcLoc(String srcLoc) {
        this.srcLoc = srcLoc;
    }

    public String getDestLoc() {
        return destLoc;
    }

    public void setDestLoc(String destLoc) {
        this.destLoc = destLoc;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(String date) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.startDate = d;
    }

    public Integer getLoad() {
        return load;
    }

    public void setLoad(Integer load) {
        this.load = load;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ObjectId> getBids() {
        return bids;
    }

    public void setBids(List<ObjectId> bids) {
        this.bids = bids;
    }

    public boolean isFinalizedOrder() {
        return finalizedOrder;
    }

    public void setFinalizedOrder(boolean finalizedOrder) {
        this.finalizedOrder = finalizedOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "_id=" + _id +
                ", srcLoc='" + srcLoc + '\'' +
                ", destLoc='" + destLoc + '\'' +
                ", startDate=" + startDate +
                ", load=" + load +
                ", category='" + category + '\'' +
                ", bids=" + bids +
                ", finalizedOrder=" + finalizedOrder +
                '}';
    }
}
