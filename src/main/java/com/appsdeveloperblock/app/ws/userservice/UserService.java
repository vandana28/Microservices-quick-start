package com.appsdeveloperblock.app.ws.userservice;

import com.appsdeveloperblock.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblock.app.ws.ui.model.response.UserRest;



public interface UserService {

    UserRest createUser(UserDetailsRequestModel userDetails);
}
