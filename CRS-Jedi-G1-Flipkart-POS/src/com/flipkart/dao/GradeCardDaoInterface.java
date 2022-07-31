package com.flipkart.dao;

import com.flipkart.bean.GradeCard;

public interface GradeCardDaoInterface {

    /**
     * method to view grade card of student
     * @param studID
     * @return
     */
    public GradeCard viewGradeCard(String studID);

    /**
     * method to activate the grade card for viewing
     * @param studID
     */
    public void activateGradeCard(String studID);

}
