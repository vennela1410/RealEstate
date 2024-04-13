package com.softwareverification.realestate.controller;

import com.softwareverification.realestate.entity.Agent;
import com.softwareverification.realestate.entity.UserEntity;
import com.softwareverification.realestate.models.response.ResponseStatus;
import com.softwareverification.realestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/realEstate/register", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseStatus> registerUser(@RequestBody UserEntity user, @RequestParam(name = "userId") boolean userId) {

        try {
            String resp = userService.registerUser(user, userId);
            if(resp.equalsIgnoreCase("Success")) {
                return new ResponseEntity<>(new ResponseStatus("Success",200,0), HttpStatus.OK);
            }
            throw new Exception(resp);
        }catch(Exception e) {
            return new ResponseEntity<>(new ResponseStatus(e.getLocalizedMessage(),500,0),HttpStatus.OK);
        }
    }

    @PostMapping(value = "/realEstate/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseStatus> loginUser(@RequestBody UserEntity user) {

        try {
            String resp = userService.loginUser(user);
            if(resp.equalsIgnoreCase("Success")) {
                return new ResponseEntity<>(new ResponseStatus("Success",200,0), HttpStatus.OK);
            }
            throw new Exception(resp);
        }catch(Exception e) {
            return new ResponseEntity<>(new ResponseStatus(e.getLocalizedMessage(),500,0),HttpStatus.EXPECTATION_FAILED);
        }
    }


}
