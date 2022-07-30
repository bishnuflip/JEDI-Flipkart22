package com.flipkart.dao;

import com.flipkart.bean.User;

public interface UserDaoInterface {

    /**
     * method to check login credentials
     * @param userId
     * @param password
     * @return User object containing userId and type
     */
    int login(String userId, String password);

    /**
     *
     */

}
