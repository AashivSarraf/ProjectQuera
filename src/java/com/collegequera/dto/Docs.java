
package com.collegequera.dto;

import java.util.Date;

public class Docs {
    private int facid;
    private String facName;
    private String fileName;
    private Date uploadDate;

    public Docs(int facid, String facName, String fileName, Date uploadDate) {
        this.facid = facid;
        this.facName = facName;
        this.fileName = fileName;
        this.uploadDate = uploadDate;
    }

    public int getFacid() {
        return facid;
    }

    public void setFacid(int facid) {
        this.facid = facid;
    }

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
    
    
    
}
