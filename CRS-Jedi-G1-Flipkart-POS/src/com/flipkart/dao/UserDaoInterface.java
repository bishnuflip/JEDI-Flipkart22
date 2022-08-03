package com.flipkart.dao;

import com.flipkart.bean.User;

public interface UserDaoInterface {

    /**
     * method to check login credentials
     * @param userId
     * @param password
     * @return User object containing userId and type
     */
    User login(String userId, String password);

    /**
     * method to get last generated index
     * used to generate the next ID
     * @param role
     * @return
     */
    public int getLastIndex(int role);

    /**
     * method to check
     * @param userID
     * @return
     */
    public boolean checkIDAvailable(String userID);

    public boolean checkEmailAvailable(String email);

    public boolean changePassword(String userID, String password);
}
