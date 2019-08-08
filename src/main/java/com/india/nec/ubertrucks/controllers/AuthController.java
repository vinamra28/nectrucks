package com.india.nec.ubertrucks.controllers;

import com.india.nec.ubertrucks.models.User;
import com.india.nec.ubertrucks.repositories.UserRepository;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JSONObject registerCustomer(@RequestBody User user){
        JSONObject jsonObject = new JSONObject();
        LOG.info(user.toString());
        User userExists = userRepository.findByEmail(user.getEmail());
        if(userExists!=null){
            jsonObject.put("code",200);
            jsonObject.put("status","unsuccessful");
            jsonObject.put("message","user already present");
            LOG.info("User present");
            return jsonObject;
        }else{
            User usr= userRepository.save(user);
            jsonObject.put("code",200);
            jsonObject.put("status","successful");
            jsonObject.put("message","user added");
            jsonObject.put("accountype",usr.getAccountType());
            jsonObject.put("userId",usr.get_id().toHexString());
            LOG.info("Saving user.");
            return jsonObject;
        }
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public JSONObject loginCustomer(@RequestBody User user){
        String email = user.getEmail();
        String password = user.getPassword();
        User res = userRepository.findByEmail(email);
        JSONObject jsonObject = new JSONObject();
        if(res!=null){
            LOG.info(res.toString());
            if(res.getPassword().equals(password)){
                jsonObject.put("code",200);
                jsonObject.put("status","successful");
                jsonObject.put("message","login success");
                jsonObject.put("accountype",res.getAccountType());
                jsonObject.put("userId",res.get_id().toHexString());
            }else{
                jsonObject.put("code",200);
                jsonObject.put("status","unsuccessful");
                jsonObject.put("message","Invalid username or password");
            }
        }else{
            jsonObject.put("code",200);
            jsonObject.put("status","unsuccessful");
            jsonObject.put("message","Invalid username or password");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/updateDetails",method = RequestMethod.POST)
    public JSONObject updateDetails(@RequestBody User user){
        String email = user.getEmail();
        User res = userRepository.findByEmail(email);
        JSONObject jsonObject = new JSONObject();
        if(res!=null){
            if(user.getName()!=null){
                res.setName(user.getName());
            }
            if(user.getOrgansiationName()!=null){
                res.setOrgansiationName(user.getOrgansiationName());
            }
            if(user.getAddress()!=null){
                res.setAddress(user.getAddress());
            }
            if(user.getPhoneNumber()!=null){
                res.setPhoneNumber(user.getPhoneNumber());
            }
            userRepository.deleteByEmail(user.getEmail());
            User res1 = userRepository.save(res);
            LOG.info(res1.toString());
            jsonObject.put("code",200);
            jsonObject.put("status","Successful");
            jsonObject.put("message","Updated");
        }else{
            jsonObject.put("code",404);
            jsonObject.put("status","UnSuccessful");
            jsonObject.put("message","not found");
        }
        return jsonObject;
    }

}
