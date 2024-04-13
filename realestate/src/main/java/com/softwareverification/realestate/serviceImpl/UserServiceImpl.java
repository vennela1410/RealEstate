package com.softwareverification.realestate.serviceImpl;

import com.softwareverification.realestate.entity.UserEntity;
import com.softwareverification.realestate.repository.UserRepository;
import com.softwareverification.realestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepo;

    @Override
    public String registerUser(UserEntity user, boolean userId) {
        // Check for mandatory fields for registering new User
        if(userId){
            UserEntity userDet = userRepo.findByUserId(user.getUserId());
            if(userDet != null && userDet.getUserId() != null){
                return "UserID exists, please try another userId";
            }else{
                return "Success";
            }
        }
        if(validateUserObject(user)){
            // check if user exists
            UserEntity userDet = userRepo.findByUserId(user.getUserId());
            if(userDet != null && userDet.getUserId() != null){
                return "UserID exists, please try another userId";
            }else{
                UserEntity userDet1 = userRepo.save(user);
                // check if Saved User entity is returned or not!
                if(userDet1 != null){
                    return "Success";
                }
                // return respective error message if saved object is empty.
                return "Error occured while registering the Account. Please try again later";
            }
        }// if new user creation request is not valid, sent respective error message.
        else{
            return "user details are invalid or empty. Please enter valid details";
        }
    }

    @Override
    public String loginUser(UserEntity user) {
        // Verify if UserId and Password are Valid and not empty
        if(user != null && user.getUserId() != null && user.getPassword() != null){
            UserEntity userDet = userRepo.findByUserIdAndPassword(user.getUserId(), user.getPassword());
            // If user record matches with the User credentials, return success
            if(userDet!= null){
                return "Success";
            }
            // if no user records existing matching user credentials
            return "Incorrect User Name or Password.";
        }

        // If User request is invalid
        return "Invalid Credentials";
    }

    private boolean validateUserObject(UserEntity entity){
        if(entity != null && !ObjectUtils.isEmpty(entity.getUserId()) && !ObjectUtils.isEmpty(entity.getPassword()) &&
                !ObjectUtils.isEmpty(entity.getFirstName()) && !ObjectUtils.isEmpty(entity.getLastName()) && !ObjectUtils.isEmpty(entity.getUserId())){
            return true;
        }
        return false;
    }
}
