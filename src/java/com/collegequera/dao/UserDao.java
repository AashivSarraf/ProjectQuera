
package com.collegequera.dao;

import com.collegequera.dto.Docs;
import com.collegequera.dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao implements BaseDao<User>{

    @Override
    public boolean save(User ob) {
        try( Connection cnn=DBConnection.getConnection())
        {
           
            String query="insert into user(user_name,email,password,branch,type)"+
                    "value(?,?,?,?,?)";
            PreparedStatement ps=cnn.prepareStatement(query);
            ps.setString(1, ob.getUsername());
            ps.setString(2, ob.getEmail());
            ps.setString(3, ob.getPassword());
            ps.setString(4, ob.getBranch());
            ps.setString(5, ob.getType());
            
            int i=ps.executeUpdate();
            
            if(i>0)
                return true;
            else
                return false;
            
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Register User, UserDao Save:"+ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(User ob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(User ob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User getUser(String email, String password) {
        try (Connection cnn=DBConnection.getConnection()){
            String query="select userid,user_name,email,branch,type,isverify from user"+
                    " where email=? and password=?"; 
            PreparedStatement ps=cnn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                int userid=rs.getInt("userid");
                String username=rs.getString("user_name");
                String useremail=rs.getString("email");
                String branch=rs.getString("branch");
                String type=rs.getString("type");
                boolean isverify=rs.getBoolean("isverify");
                
                User user=new User(userid,username,email,branch,type);
                user.setVerifystatus(isverify);
                return user;
            }else{
                return null;
            }
        } 
        catch (Exception ex) {
            System.out.println("Login User, getUser:"+ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<User> listStudents(String branch){
        ArrayList<User> list=new ArrayList<>();
        try (Connection cnn=DBConnection.getConnection()){
            String query="select user_name,email from user where type='student' and branch=?";
            PreparedStatement ps=cnn.prepareStatement(query);
            
            ps.setString(1, branch);
            
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String username=rs.getString("user_name");
                String email=rs.getString("email");
                
                //User user=new User(username,email);// OR
                User user=new User();
                user.setUsername(username);
                user.setEmail(email);
                
                list.add(user);
            }
        } 
        catch(Exception ex){
            System.out.println("list students, UserDao:"+ex.getMessage());
            ex.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<User> selfliststudents(String branch){//,String email){
        
        ArrayList<User> list=new ArrayList<>();
        
        try (Connection cnn=DBConnection.getConnection()){
            String query="select user_name,email from user where type='student' and branch=? ";
            //Now i want this line to be removed :
            //      " select user_name,email from user where type='student' and email=? and branch=?";
                    
            PreparedStatement ps=cnn.prepareStatement(query);
            ps.setString(1, branch);
//            ps.setString(2, email);
//            ps.setString(3, branch);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                String username=rs.getString("user_name");
                String email=rs.getString("email");
                
                User user=new User();
                user.setUsername(username);
                user.setEmail(email);
                
                list.add(user);
            }
            
        } 
        catch(Exception ex){
            System.out.println("selfliststudents, UserDao:"+ex.getMessage());
            ex.printStackTrace();
        }
        return list;
    }

    public boolean saveDocument(int facid, String fileName) {
        try (Connection cnn=DBConnection.getConnection()){
            String query="insert into documents(filepath,faculty) value(?,?)";
            PreparedStatement ps=cnn.prepareStatement(query);
            ps.setString(1, fileName);
            ps.setInt(2, facid);
            
            int i=ps.executeUpdate();
            if(i>0){
                return true;
            }else{
                return false;
            }
        } 
        catch(Exception ex){
            System.out.println("UserDao, saveDocument:"+ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Docs> listDocs(String branch){
        
        ArrayList<Docs> list=new ArrayList<>();
        
        try (Connection cnn=DBConnection.getConnection()){
            String query="select user_name,filepath,send_date,faculty from user,documents where faculty in "+
                    "(select userid from user where type='faculty' and branch=?) and "+
                    "user.userid=documents.faculty order by send_date DESC";
            
            PreparedStatement ps=cnn.prepareStatement(query);
            ps.setString(1, branch);
            
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                String facName=rs.getString("user_name");
                String fileName=rs.getString("filepath");
                Date uploadDate=rs.getDate("send_date");
                int facid=rs.getInt("faculty");
                
                Docs docs=new Docs(facid, facName, fileName, uploadDate);
                
                list.add(docs);
            }
                    
        }
        catch(Exception ex){
            System.out.println("UserDao, listDocs:"+ex.getMessage());
            ex.printStackTrace();
        }
        
        return list;
        
    }

    public boolean verifyUser(String email, String otp) {
        try(Connection cnn=DBConnection.getConnection()) {
            String query="update user set isverify='1' where email=? and otp=?";
            PreparedStatement ps=cnn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, otp);
            int i=ps.executeUpdate();
            
            if(i>0)
                return true;
            else
                return false;
            
        } 
        catch(Exception ex){
            System.out.println("UserDao, verifyUser:"+ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public void updateOTP(String email, int otp) {
        try(Connection cnn=DBConnection.getConnection()) {
            String query="update user set otp=? where email=?";
            PreparedStatement ps=cnn.prepareStatement(query);
            ps.setInt(1, otp);
            ps.setString(2, email);
            
            int i=ps.executeUpdate();
        }
        catch(Exception ex){
            System.out.println("UserDao, updateOtp:"+ex.getMessage());
            ex.printStackTrace();
        }
    }

}
    