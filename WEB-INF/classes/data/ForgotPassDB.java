/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Utilities.ConnectionPool;
import Utilities.DBUtil;
import Utilities.PasswordUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author saiku
 */
public class ForgotPassDB {
    
    public static String getUsersName(String email){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        String name=null;
        String query
                = "SELECT Name from User where email=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs=ps.executeQuery();
            while(rs.next()){
                name=rs.getString("Name");
            }
            return name;
            }catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
      return null;        
    }
    
    
    
    
    public static void insert(String email, String uuid){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String timestamp=dateFormat.format(date);
        String query
                = "INSERT into ForgotPassword values(?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, uuid);
            ps.setString(3, timestamp);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }        
    }

    
    public static int checkExpired(String email, String uuid){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        int flag=0;
        String query
                = "SELECT * from ForgotPassword where email=? and token=? and TIMESTAMPDIFF(second,initime,NOW())>300";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, uuid);
            rs=ps.executeQuery();
            while(rs.next()){
                flag=1;
            }
            return flag;
            }catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
      return flag;        
    }
    
    
    
    public static int userExistsByEmail(String email){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        int flag=0;
        String query
                = "DELETE from ForgotPassword where email=? and TIMESTAMPDIFF(second,initime,NOW())>300";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.executeUpdate();
            
            query
                = "SELECT * from ForgotPassword where email=? and TIMESTAMPDIFF(second,initime,NOW())<300";
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs=ps.executeQuery();
            while(rs.next()){
                flag=1;
            }
            return flag;           
            }catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }        
      return flag;                
    }
    

    public static int userExistsByToken(String uuid){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        int flag=0;
        String query
                = "DELETE from ForgotPassword where token=? and  TIMESTAMPDIFF(second,initime,NOW())>300";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, uuid);
            ps.executeUpdate();
            
            query
                = "SELECT * from ForgotPassword where token=? and TIMESTAMPDIFF(second,initime,NOW())<300";
            ps = connection.prepareStatement(query);
            ps.setString(1, uuid);
            rs=ps.executeQuery();
            while(rs.next()){
                flag=1;
            }
            return flag;           
            }catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }        
      return flag;                
    }

    
    public static int updatePassword(String uuid, String password){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String salt=PasswordUtil.getSalt();
        String hashpass=password;
            String email=null;
        ResultSet rs=null;
        try{
        hashpass=PasswordUtil.hashPassword(password+salt);
        }catch(Exception e){}
        int flag=0;
        String query
                = "UPDATE ForgotPassword, User set User.password=? where ForgotPassword.token=? and ForgotPassword.email=User.email";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, hashpass);
            ps.setString(2, uuid);            
            flag= ps.executeUpdate();
            query="SELECT Email from ForgotPassword where Token=?";
            ps.setString(1, uuid);
            rs=ps.executeQuery();
            while(rs.next()){
                email=rs.getString("Email");
                break;
            }
            query
                = "UPDATE  UserHSPassword set HashAndSaltPassword=? and Salt=? where Email=?";
            ps = connection.prepareStatement(query);
            ps.setString(1, hashpass);
            ps.setString(2, salt);
            ps.setString(3,email);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return flag;
    }
    
    
    
}
