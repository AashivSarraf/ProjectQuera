
package com.collegequera.controller;

import com.collegequera.dao.UserDao;
import com.collegequera.dto.User;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "verify",urlPatterns = {"/verify"})
public class VerifyServlet extends HttpServlet{

    private UserDao userDao;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        userDao=new UserDao();
        super.init(config); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession sess=req.getSession();
//        User user=(User) sess.getAttribute("user");
          //No Need
        
        String email=req.getParameter("email");
        String otp=req.getParameter("otp");
        
        boolean status=userDao.verifyUser(email,otp);
        
        if(status){
            resp.sendRedirect("login.jsp");
        }
        else{
            resp.sendRedirect("verify.jsp?err=1");
        }
        
    }
    
    
}
