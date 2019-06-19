package com.appsdeveloperblock.app.ws.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailsRequestModel {
    @NotNull(message = "First name cannot be null")
    @Size(min=2,message="First Name must be greater than two chars")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min=2,message="Last Name must be greater than two chars")
    private String lastName;

    @NotNull(message = "email name cannot be null")
    @Email
    private String email;

    @NotNull(message = "password name cannot be null")
    @Size(min=8,max=16,message="password must be >=8 char and <=16 char")
    private String password;

    //need to validate the http post request body. validation for the text fields. to do so, @Valid should be in the controller and individual validation constraints on the fields

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
