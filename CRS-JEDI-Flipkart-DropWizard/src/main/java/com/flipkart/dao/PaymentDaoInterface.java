package com.flipkart.dao;

public interface PaymentDaoInterface {

    /**
     * method to make payment by student
     * @param studID
     */
    public void makePayment(String studID);

    /**
     * method to check payment status for student
     * @param studID
     * @return true if payment made, false otherwise
     */
    public boolean getPaymentStatus(String studID);
}
