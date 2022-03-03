
package com.collegequera.controller;

import com.collegequera.dao.QuestionDao;
import com.collegequera.dto.Question;
import com.collegequera.dto.User;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "askqus",urlPatterns = {"/student/askqus"})
public class AskQuestionServlet extends HttpServlet{
    
    QuestionDao qDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        qDao=new QuestionDao();
        super.init(config); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sess=req.getSession();
        User user=(User)sess.getAttribute("user");
        
        String  qus=req.getParameter("qus");
        int userid=user.getUserid();
        Question question=new Question(qus,userid);
        qDao.save(question);
        
        String url=req.getContextPath()+"/student/question.jsp";
        resp.sendRedirect(url);
    }
    
    
}
