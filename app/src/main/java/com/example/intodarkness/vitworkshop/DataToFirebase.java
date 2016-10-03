package com.example.intodarkness.vitworkshop;

/**
 * Created by nandu on 24-04-2016.
 */
public class DataToFirebase {

    private String studName;
    private String rollNo;

    public DataToFirebase() {
    }

    public DataToFirebase(String studName, String rollNo) {

        this.studName = studName;
        this.rollNo = rollNo;
    }


    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
}
