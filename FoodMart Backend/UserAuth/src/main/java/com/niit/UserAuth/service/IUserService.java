package com.niit.UserAuth.service;

import com.niit.UserAuth.model.User;
import com.niit.UserAuth.model.UserSignUp;
import com.niit.UserAuth.exception.InvalidCredentialsException;
import com.niit.UserAuth.exception.UserAlreadyExistException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IUserService {

    User userRegistration(UserSignUp userSignUp) throws UserAlreadyExistException;

    User loginCheck(String email, String password) throws InvalidCredentialsException;

    List<User> getAllUser();

    int generateOTP();

     int sendOTP(String email);


    String uploadImage(String path, MultipartFile file) throws IOException;

    InputStream getImage(String path, String fileName) throws FileNotFoundException;
}
