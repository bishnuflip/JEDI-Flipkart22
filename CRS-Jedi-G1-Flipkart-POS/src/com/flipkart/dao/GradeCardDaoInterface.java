package com.flipkart.dao;

import com.flipkart.bean.GradeCard;

public interface GradeCardDaoInterface {

    /**
     * method to view grade card of student
     * @param studID
     * @param role
     * @return
     */
    public GradeCard viewGradeCard(String studID, int role);

    /**
     * method to activate the grade card for viewing
     * after checking if all courses have been graded
     * @param studID
     * @return 0 if activation fails, 1 if successful
     */
    public int activateGradeCard(String studID);

}
