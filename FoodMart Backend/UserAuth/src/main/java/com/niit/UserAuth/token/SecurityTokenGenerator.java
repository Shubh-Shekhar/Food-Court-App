package com.niit.UserAuth.token;

import com.niit.UserAuth.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<String, String> generateToken(User user);

}
