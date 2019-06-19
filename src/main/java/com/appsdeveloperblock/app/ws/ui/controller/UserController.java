package com.appsdeveloperblock.app.ws.ui.controller;


import com.appsdeveloperblock.app.ws.exceptions.UserServiceException;
import com.appsdeveloperblock.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.appsdeveloperblock.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblock.app.ws.ui.model.response.UserRest;
import com.appsdeveloperblock.app.ws.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users") // needs a path  http://localhost:8080/users maps the controller to the users resource, when http request is sent, it is sent to a url, and that url contains a path which
// is responsible all interactions with users. when any one of the methods is invoked in the user controller, it is sent to the above url.
public class UserController {
    // will be responsible to control all user interactions such as update, get details etc.


    Map <String,UserRest> users; // temporarily stores data in memory. removes itself when the program is compiled gain or run again.

    @Autowired
    UserService userService;

    @GetMapping
    public String getUsers(@RequestParam(value ="page" , defaultValue = "1") int page , @RequestParam(value ="limit", defaultValue = "50") int limit, @RequestParam(value ="sort", defaultValue = "desc",required = false) String sort)
    //since there is no path parameter provided, this is the only function that matches the request. also the page request parameter
    // is made available to the method. the parameters can also be made optional by having default value , required value works better with string
    // when the required value is false, java throws a null exception, hence provide a default value for the parameter
    {

        return "get user was called with page = " + page + "limit =" +limit + "sort=" + sort;
    }

    @GetMapping(path = "/{userId}" , produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    // annotation that binds the method to http get request. when a request had users/userId, then it will map to this method since a path is provided.

    public ResponseEntity <UserRest>getUser(@PathVariable String userId)
    //to return a response status code use Response entity.
    //public UserRest getUser(@PathVariable String userId)
    // takes a user id to query the database which then returns a json payload.
    // the argument inside the get user method will read the user id in the http get request.

    {
        //user rest returns an object.
        /*
        UserRest returnValue = new UserRest(); // once a http request is sent to get user, the response is a json format.
        returnValue.setEmail("vandana51232@gmail.com");
        returnValue.setFirstName("Vandana");
        returnValue.setLastName("Sridhar"); */

        // ******************************** //
        //exception handling
        /*
        String firstName = null;
        int firstNameLength = firstName.length(); */

        if(true) throw new UserServiceException("A user service exception is thrown");

        if(users.containsKey(userId))
        {
            return new ResponseEntity<>(users.get(userId),HttpStatus.OK); // if the users resource contains that userid, get that id from the resource, else return no content error
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // returns body and status
        }

    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails)
    {
        //@Valid - validate java bean fields
         // key-value pair.

        UserRest returnValue = userService.createUser(userDetails);

        return new ResponseEntity<UserRest>(returnValue,HttpStatus.OK);


    }

    @PutMapping(path = "/{userId}",consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRest updateUser(@PathVariable String userId , @Valid @RequestBody UpdateUserDetailsRequestModel userDetails ) //needs to include userid as a path parameter
    {
        UserRest storedUserDetails = users.get(userId); // use the hashmap to get the userID and put it in stored user details
        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());


        users.put(userId,storedUserDetails);

        return storedUserDetails;
    }


    @DeleteMapping(path = "/{id}") //path variable has an id
    public ResponseEntity deleteUser(@PathVariable String id)
    {
        users.remove(id); // removing the user based on the id
        return ResponseEntity.noContent().build(); // returning empty / no content
    }
}
