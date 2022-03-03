/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collegequera.service;

import com.collegequera.dto.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadService {
    public String uploadFile(ServletContext ctx,HttpServletRequest req)
    {
            String relativeWebPath = "/assets/docs";

            String buildPath = ctx.getRealPath(relativeWebPath);//java files jaha rakhi hai vo path
            String proPath =  buildPath.replace("build/"/* why */,"");//class ka path i.e. source ka path i.e jaha class files raakhi hai
            //ham do(2) path(bulid path aur propath) iliye le rahe hai kyoki source ou jaha java files hai dono mai apan
            //document ko upload kar rahge hai

//            System.out.println(buildPath);
//            System.out.println(proPath);
            
            HttpSession sess=req.getSession();
            User user=(User) sess.getAttribute("user");
            int userid=user.getUserid();


            boolean isMultipart = ServletFileUpload.isMultipartContent(req);
            if(isMultipart)
            {
              DiskFileItemFactory factory = new DiskFileItemFactory();               
              factory.setSizeThreshold(1000*1024);

              ServletFileUpload upload = new ServletFileUpload(factory);   
              // maximum file size to be uploaded.
              upload.setSizeMax(1000*1024);

                try {
                    List<FileItem> fileitems=upload.parseRequest(req);
                    
                    for(FileItem item:fileitems){
                        String fileName=item.getName();
                        InputStream is=item.getInputStream();//inputstream input(read) operations ke liye
                        
                        File f1=new File(buildPath,userid+"");// ????//might be got it//making folder of that faculty in our 
                                                              //system where java files are kept in our project
                        File f2=new File(proPath,userid+"");// ????//might be got it//making folder of that faculty in our 
                                                              //system where class files are kept in our project
                        
                        if(!f1.exists()){
                            f1.mkdir();
                        }
                        if(!f2.exists()){
                            f2.mkdir();
                        }
                        
                        long milli=System.currentTimeMillis();
                        String ext=fileName.substring(fileName.lastIndexOf("."));//shayad last index se//????//Okayyyyy
                        //lastindex se add karwana hai extension(milliseconds+ .png or .txt or .C)//Right done// because us 
                        // file ka extension to wahi rakhna padega na isliye
                        String fName =milli+ext;
                        
                        FileOutputStream fos1=new FileOutputStream(new File(f1, fName));//outputstream wite operations ke liye
                        FileOutputStream fos2=new FileOutputStream(new File(f2, fName));
                        
                        while(true){
                            int i=is.read();
                            if(i==-1){
                                break;
                            }
                            fos1.write(i);
                            fos2.write(i);
                        }
                        fos1.close();
                        fos2.close();
                        
                        return fName;
                    }
                } 
                catch (Exception ex) {
                    System.out.println("FileUploadService, uploadFile:"+ex.getMessage());
                    ex.printStackTrace();
                }

            }return null;
    }
}
