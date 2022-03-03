
package com.collegequera.dao;

import com.collegequera.dto.Answer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnswerDao implements BaseDao<Answer>{

    @Override
    public boolean save(Answer ob) {
        try(Connection cnn=DBConnection.getConnection()) {
            String query="insert into answer(ans,faculty,question) value(?,?,?)";
            PreparedStatement ps=cnn.prepareStatement(query);
            
            ps.setString(1, ob.getAns());
            ps.setInt(2, ob.getFaculty());
            ps.setInt(3, ob.getQuestion());
            
            int i=ps.executeUpdate();
            if(i>0)
                return true;
            else
                return false;
            
        }
        catch(Exception ex){
            System.out.println("AnswerDao, save:"+ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Answer ob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Answer ob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Answer> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Answer get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Answer> listByQuestion(int qid){
        
        ArrayList<Answer> list=new ArrayList<>();
        
        try (Connection cnn=DBConnection.getConnection()){
            String query="select ans,ans_date,user_name from answer,user where question=? and "+
                    "answer.faculty=user.userid order by ans_date DESC";
            PreparedStatement ps=cnn.prepareStatement(query);
            ps.setInt(1, qid);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String ans=rs.getString("ans");
                Date ansDate=rs.getDate("ans_date");
                String username=rs.getString("user_name");
                
                Answer answer=new Answer();
                answer.setAns(ans);
                answer.setAnsDate(ansDate);
                answer.setFacultyName(username);
                
                list.add(answer);
            }
        } 
        catch(Exception ex){
            System.out.println("AnswerDao, listByQuestion:"+ex.getMessage());
            ex.printStackTrace();
        }
        
        return list;
        
    }
    
}
