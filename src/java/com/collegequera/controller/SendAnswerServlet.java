
package com.collegequera.controller;

import com.collegequera.dao.AnswerDao;
import com.collegequera.dto.Answer;
import com.collegequera.dto.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "sendans",urlPatterns = {"/faculty/sendans"})
public class SendAnswerServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String ans=req.getParameter("ans");
        int qid=Integer.parseInt(req.getParameter("qusid"));
        
        HttpSession sess=req.getSession();
        User user=(User)sess.getAttribute("user");
        
        int userid=user.getUserid();
        
        Answer answer=new Answer(ans,qid,userid);
        
        AnswerDao aDao=new AnswerDao();
        aDao.save(answer);
        
        resp.getWriter().write("Done");// OR
        /*
            PrintWriter pw=resp.getWriter();
            pw.write("Done");
        */
        
    }
    
    
    
}
