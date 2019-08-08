package com.india.nec.ubertrucks.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private ObjectId _id;

    @Indexed(unique = true, direction = IndexDirection.ASCENDING,dropDups = true)
    private String email;

    private String name;

    private String password;

    private String address;

    private Long phoneNumber;

    private String organsiationName;

    private String accountType;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public String getOrgansiationName() {
        return organsiationName;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setOrgansiationName(String organsiationName) {
        this.organsiationName = organsiationName;
    }

    public void setAccountType(String accountType) {
        if(accountType.equalsIgnoreCase("customer")||accountType.equalsIgnoreCase("serviceprovider"))
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", organsiationName='" + organsiationName + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
