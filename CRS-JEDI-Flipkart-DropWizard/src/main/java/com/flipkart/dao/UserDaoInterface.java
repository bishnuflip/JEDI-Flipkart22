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
     * method to check is ID exists in database
     * @param userID
     * @return
     */
    public boolean checkIDAvailable(String userID);

    /**
     * method to check if emailID exists in database
     * @param email
     * @return
     */
    public boolean checkEmailAvailable(String email);

    /**
     * method to change password of the given user
     * @param userID
     * @param password
     * @return
     */
    public boolean changePassword(String userID, String password);
}
