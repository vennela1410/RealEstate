package com.softwareverification.realestate.service;

import com.softwareverification.realestate.entity.UserEntity;
import com.softwareverification.realestate.repository.UserRepository;
import com.softwareverification.realestate.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserServiceImpl serv;

    @Mock
    UserRepository userRepo;

    UserEntity ent;

    UserEntity entity;
    @BeforeEach
    void setUp(){
        ent = new UserEntity();
        ent.setUserId("test123");
        ent.setEmailId("testemail@gmail.com");
        ent.setPassword("#$#%^&*(&^%");
        ent.setFirstName("Test");
        ent.setLastName("Test123");

        entity =  new UserEntity();
        entity.setUserId("test123");
        entity.setPassword("#$#%^&*(&^%");
    }

    // Test for Succesfull new User Registration - happy flow
     @Test
    void valid_userRegistration(){
        String expectedResp = "Success";
        Mockito.when(userRepo.findByUserId(any(String.class))).thenReturn(null);
        Mockito.when(userRepo.save(any(UserEntity.class))).thenReturn(ent);
        String resp = serv.registerUser(ent, false);
        assertNotNull(resp);
        assertEquals(expectedResp, resp);
    }

    // Test for Validating User registration with existing UserId
    @Test
    void inValid_userRegistration(){
        String expectedResp = "UserID exists, please try another userId";
        Mockito.when(userRepo.findByUserId(any(String.class))).thenReturn(ent);
        String resp = serv.registerUser(ent, true);
        assertNotNull(resp);
        assertEquals(expectedResp, resp);
    }

    // Test for not passing all mandatory fields for new user registration
    @Test
    void inValid_userDetails_userRegistration(){
        String expectedResp = "user details are invalid or empty. Please enter valid details";
        String resp = serv.registerUser(new UserEntity(), false);
        assertNotNull(resp);
        assertEquals(expectedResp, resp);
    }

    // User Registration Failure and test for error message on Failure
    @Test
    void userRegistration_failure(){
        String expectedResp = "Error occured while registering the Account. Please try again later";
        Mockito.when(userRepo.findByUserId(any(String.class))).thenReturn(null);
        Mockito.when(userRepo.save(any(UserEntity.class))).thenReturn(null);
        String resp = serv.registerUser(ent, false);
        assertNotNull(resp);
        assertEquals(expectedResp, resp);
    }

    // Test for Succesfull User login - Happy path
    @Test
    void valid_userLogin(){
        String expectedResp = "Success";
        Mockito.when(userRepo.findByUserIdAndPassword(any(String.class), any(String.class))).thenReturn(ent);
        String resp = serv.loginUser(entity);
        assertNotNull(resp);
        assertEquals(expectedResp, resp);
    }

    @Test
    void inValid_userLogin(){
        String expectedResp = "Incorrect User Name or Password.";
        Mockito.when(userRepo.findByUserIdAndPassword(any(String.class), any(String.class))).thenReturn(null);
        String resp = serv.loginUser(entity);
        assertNotNull(resp);
        assertEquals(expectedResp, resp);
    }

    @Test
    void inValid_userCred_userLogin(){
        String expectedResp = "Invalid Credentials";
        String resp = serv.loginUser(new UserEntity());
        assertNotNull(resp);
        assertEquals(expectedResp, resp);
    }
}
