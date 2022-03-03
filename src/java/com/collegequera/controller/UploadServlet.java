
package com.collegequera.controller;

import com.collegequera.dao.UserDao;
import com.collegequera.dto.User;
import com.collegequera.service.FileUploadService;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "upload",urlPatterns = {"/faculty/upload"})
public class UploadServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user=(User) req.getSession().getAttribute("user");
        int userid=user.getUserid();
        ServletContext ctx = getServletContext();
        FileUploadService fileUpload = new FileUploadService();
        String fileName = fileUpload.uploadFile(ctx, req);
        System.out.println("File Name : " +  fileName);
        
        if(fileName!=null)
            new UserDao().saveDocument(userid, fileName);
        resp.sendRedirect(req.getContextPath()+"/faculty/uploaddocs.jsp");
    }
    
}
