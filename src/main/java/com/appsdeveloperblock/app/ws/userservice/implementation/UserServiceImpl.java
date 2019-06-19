package com.appsdeveloperblock.app.ws.userservice.implementation;

import com.appsdeveloperblock.app.ws.shared.Utils;
import com.appsdeveloperblock.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblock.app.ws.ui.model.response.UserRest;
import com.appsdeveloperblock.app.ws.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    Map<String,UserRest> users;
    Utils utils;

    public UserServiceImpl() {}

    @Autowired
    public UserServiceImpl(Utils utils){
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {

        UserRest returnValue = new UserRest(); // once a http request is sent to get user, the response is a json format.
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());

        String userId = utils.generateUserId(); // generating a random id for user
        returnValue.setUserId(userId); //adding that to return value

        if (users== null) users = new HashMap<>(); // a new hashmap is created if there are no users
        users.put(userId,returnValue);

        return returnValue;
    }
}
