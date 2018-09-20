package com.mariontardiveau.applicationnumber1.model;

/**
 * @author M.Tardiveau @version: 1.0
 */
public class User {
    private String mFirstname;

//________________________get/setters_____________________________________________________________
    public String getFirstname() {
        return mFirstname;
    }

    public void setFirstname(String firstname) {
        mFirstname = firstname;
    }

//________________________toString________________________________________________________________
    @Override
    public String toString() {
        return "User{" +
                "mFirstname='" + mFirstname + '\'' +
                '}';
    }
}
