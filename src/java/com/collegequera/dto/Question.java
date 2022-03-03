
package com.collegequera.dto;

import java.util.Date;

public class Question {
    private int qid;
    private String qus;
    private Date askDate;
    private int student;

    public Question(int qid, String qus, Date askDate, int student) {
        this.qid = qid;
        this.qus = qus;
        this.askDate = askDate;
        this.student = student;
    }

    public Question(String qus, int userid) {
        this.qus=qus;
        this.student=userid;
    }

    public Question(int qid, String qus, Date askDate) {
        this.qid = qid;
        this.qus = qus;
        this.askDate = askDate;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getQus() {
        return qus;
    }

    public void setQus(String qus) {
        this.qus = qus;
    }

    public Date getAskDate() {
        return askDate;
    }

    public void setAskDate(Date askDate) {
        this.askDate = askDate;
    }

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }
    
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
