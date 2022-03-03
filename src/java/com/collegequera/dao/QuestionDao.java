
package com.collegequera.dao;

import com.collegequera.dto.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuestionDao implements BaseDao<Question>{

    @Override
    public boolean save(Question ob) {
        try(Connection cnn=DBConnection.getConnection()) {
            String query="insert into question(qus,student) value(?,?)";
            PreparedStatement ps=cnn.prepareStatement(query);
            
            ps.setString(1, ob.getQus());
            ps.setInt(2, ob.getStudent());
            
            int i=ps.executeUpdate();
            if(i>0)
                return true;
            else
                return false;
        } 
        catch(Exception ex){
            System.out.println("QuestionDao, save:"+ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Question ob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Question ob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Question> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Question get(int id) {
        try (Connection cnn=DBConnection.getConnection()){
            String query="select * from question where qid=?";
            PreparedStatement ps=cnn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                int qid=rs.getInt("qid");
                String qus=rs.getString("qus");
                Date askDate=rs.getDate("ask_date");
                
                Question question=new Question(qid,qus,askDate,0);
                return question;
            }
            else{
                return null;
            }
        }
        catch(Exception ex){
            System.out.println("QuestionDao, get:"+ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        // OR OR OR OR
        /*
            try{
            Connection cnn = DBConnection.getConnection();
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery("select * from question where qid="+id);
            if(rs.next())
            {
                int qid = rs.getInt("qid");
                String qus = rs.getString("qus");
                Date askDate = rs.getDate("ask_date");
                
                Question question = new Question(qid, qus, askDate, 0);
                return question;
            }else{return null;}
            }catch(Exception ex){
                System.out.println("Qus Get : "+ex.getMessage());
                return null;
            }
        */
    }
    
    public ArrayList<Question> listByStudent(int id){
        
        ArrayList<Question> list=new ArrayList<>();
        
        try(Connection cnn=DBConnection.getConnection()) {
            String query="select * from question where student=?";
            PreparedStatement ps=cnn.prepareStatement(query);
            ps.setInt(1,id);
            
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                int qid=rs.getInt("qid");
                String qus=rs.getString("qus");
                Date askDate=rs.getDate("ask_date");
                
                Question question=new Question(qid, qus, askDate, 0);
                
                list.add(question);
            }
        } 
        catch(Exception ex){
            System.out.println("QuestionDao, listByStudent:"+ex.getMessage());
            ex.printStackTrace();
        }
        
        return list;
    }
    
    public ArrayList<Question> listByBranch(String branch){
        
        ArrayList<Question> list=new ArrayList<>();
        
        try(Connection cnn=DBConnection.getConnection()) {
            String query="select qid,qus,ask_date,user_name from question,user where student in"
                    +" (select userid from user where branch=? and type='student')"
                    +" and user.userid=question.student";
            
            PreparedStatement ps=cnn.prepareStatement(query);
            ps.setString(1, branch);
            
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                int qid=rs.getInt("qid");
                String qus=rs.getString("qus");
                Date askDate=rs.getDate("ask_date");
                String username=rs.getString("user_name");
                
                Question question=new Question(qid, qus, askDate, 0);
                question.setUsername(username);
                list.add(question);
            }
        } 
        catch(Exception ex){
            System.out.println("QuestionDao, listByBranch:"+ex.getMessage());
            ex.printStackTrace();
        }
        
        return list;
    }
    
}
