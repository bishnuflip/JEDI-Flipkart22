/**
 * 
 */
package com.flipkart.bean;

import java.util.Date;

/**
 * @author jain.harshil
 *
 */
public class Admin extends User {
    private String adminId;
    private Date dateOfJoining;

    /**
     * @return id of admin
     */
    public String getAdminId() {
        return adminId;
    }

    /**
     * @param str the admin ID to be set
     */
    public void setAdminId(String str) {
        this.adminId = str;
    }

    /**
     * @return date of joining of admin
     */
    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    /**
     * @param date of joining of admin to be set
     */
    public void setDateOfJoining(Date date) {
        this.dateOfJoining = date;
    }
}
