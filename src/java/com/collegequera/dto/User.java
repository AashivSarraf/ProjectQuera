
package com.collegequera.dto;

public class User {
    private int userid;
    private String username;
    private String email;
    private String password;
    private String branch;
    private String type;
    private int otp;
    private boolean verifystatus;

    public User(int userid, String username, String email, String password, String branch, String type, int otp, boolean verifystatus) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.branch = branch;
        this.type = type;
        this.otp = otp;
        this.verifystatus = verifystatus;
    }

    public User(String username, String email, String password, String branch, String type) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.branch = branch;
        this.type = type;
    }

    public User(int userid, String username, String email, String branch, String type) {
        this.userid=userid;
        this.username = username;
        this.email = email;
        this.branch = branch;
        this.type = type;
    }

    public User() {
        
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public boolean isVerifystatus() {
        return verifystatus;
    }

    public void setVerifystatus(boolean verifystatus) {
        this.verifystatus = verifystatus;
    }
    
    
    
}
