/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import Utilities.ConnectionPool;
import Utilities.DBUtil;
import business.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author SriSaiKumar
 */
public class UserDB {
    static ArrayList<User> nuser = new ArrayList<>();
    
    
    
    public static User getUser(String email){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs= null;
        String query
                = "Select * from User where email=?";
        try {
            ps =connection.prepareStatement(query);
            ps.setString(1, email);
            rs=ps.executeQuery();
            User user_temp=null;
            while(rs.next()){
                user_temp=new User();
                user_temp.setEmail(rs.getString("Email"));
                user_temp.setPassword(rs.getString("Password"));
                user_temp.setName(rs.getString("Name"));
                user_temp.setType(rs.getString("Type"));
                user_temp.setNumCoins(rs.getInt("Coins"));
                user_temp.setNumOfParticipants(rs.getInt("Participants"));
                user_temp.setNumPostedStudies(rs.getInt("Studies"));
                user_temp.setNumParticipation(rs.getInt("Participation"));
                break;
            }
            return user_temp;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return null;
        
        /*Iterator<User> iterator = nuser.iterator();
          while(iterator.hasNext()){
              User user_temp = (User) iterator.next();
              if(user_temp.getEmail().equals(email)){
                  return user_temp;
              }
          }
          return null;*/
    }
    
    public static ArrayList<User> getUsers(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs= null;
        String query
                = "Select * from User";
        try {
            ps =connection.prepareStatement(query);
            rs=ps.executeQuery();
            ArrayList<User> user_temp_array=new ArrayList<>();
            User user_temp =new User();
            while(rs.next()){
                user_temp.setEmail(rs.getString("Email"));
                user_temp.setPassword(rs.getString("Password"));
                user_temp.setName(rs.getString("Name"));
                user_temp.setType(rs.getString("Type"));
                user_temp.setNumCoins(rs.getInt("Coins"));
                user_temp.setNumOfParticipants(rs.getInt("Participants"));
                user_temp.setNumPostedStudies(rs.getInt("Studies"));
                user_temp.setNumParticipation(rs.getInt("Participation"));
                user_temp_array.add(user_temp);
            }
            return user_temp_array;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }        
        return null;
        
        
        /*return nuser;*/
    }
    


    public static void insertTempUser(User user_ins,String userid, String uuid){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO TempUser (Email, UserID, Password , Name, Type, Token) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user_ins.getEmail());
            ps.setString(2, userid);
            ps.setString(3, user_ins.getPassword());
            ps.setString(4, user_ins.getName());
            ps.setString(5, user_ins.getType());
            ps.setString(6, uuid);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }        
    }
    
    public static User createUser(String userid, String uuid){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs= null;
        User user_temp=null;
        String query
                = "Select Email, Password, Name, Type from TempUser where UserID=? and Token=?";
        try {
            ps =connection.prepareStatement(query);
            ps.setString(1, userid);
            ps.setString(2, uuid);
            rs=ps.executeQuery();
            while(rs.next()){
                user_temp=new User();
                user_temp.setEmail(rs.getString("Email"));
                user_temp.setPassword(rs.getString("Password"));
                user_temp.setName(rs.getString("Name"));
                user_temp.setType(rs.getString("Type"));
                user_temp.setNumCoins(0);
                user_temp.setNumOfParticipants(0);
                user_temp.setNumPostedStudies(0);
                user_temp.setNumParticipation(0);
                break;
            }
            query="Delete from TempUser where UserID=?";
            ps =connection.prepareStatement(query);
            ps.setString(1, userid);
            ps.executeUpdate();
            return user_temp;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }     
        return user_temp;
    }
    
    
    public static void insert(User user_ins){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO User (Email, Password , Name, Type, Studies, Participation, Coins, Participants) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user_ins.getEmail());
            ps.setString(2, user_ins.getPassword());
            ps.setString(3, user_ins.getName());
            ps.setString(4, user_ins.getType());
            ps.setInt(5, user_ins.getNumPostedStudies());
            ps.setInt(6, user_ins.getNumParticipation());            
            ps.setInt(7, user_ins.getNumCoins());
            ps.setInt(8, user_ins.getNumOfParticipants());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
       /* int i=0;
        int flag=0;
        for(User user_temp : nuser){
            if(user_temp.getEmail().equals(user_ins.getEmail())){
                flag=1;
            }
            i++;
        }
        if(flag==0)
            nuser.add(user_ins);*/
    }

    public static void updateUserCP(String email, int coins, int participation){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "Update User set Coins=?, Participation=? where Email=?";
        try {
            ps =connection.prepareStatement(query);
            ps.setInt(1, coins);
            ps.setInt(2, participation);
            ps.setString(3, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
        
        
        /*
        int i=0;
        for(User user_temp : nuser){
            if(user_temp.getEmail().equals(email)){
                user_temp.setNumCoins(coins);
                user_temp.setNumParticipation(participation);
                nuser.set(i,user_temp);
            }
            i++;
        } */ 
    }

    public static void updateUserNumOfParticipants(String email, int participants){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "Update User set coins=coins+1, Participants=? where Email=?";
        try {
            ps =connection.prepareStatement(query);
            ps.setInt(1, participants);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
        /*
        int i=0;
        for(User user_temp : nuser){
            if(user_temp.getEmail().equals(email)){
                user_temp.setNumOfParticipants(participants);
                user_temp.setNumCoins(user_temp.getNumCoins()+1);
                nuser.set(i,user_temp);
            }
            i++;
        } */ 
    }
    
    public static void insertHashAndSaltPassword(String email, String spass, String salt){
        ConnectionPool pool=ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO UserHSPassword (Email, HashAndSaltPassword, Salt) "
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, spass);
            ps.setString(3, salt);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }        
    }
    
    public static String getUserSalt(String email){
        ConnectionPool pool=ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        String pass=null;
        String query
                = "SELECT Salt from UserHSPassword WHERE email=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs=ps.executeQuery();
            while(rs.next()){
                pass=rs.getString("Salt");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return pass;
    }
    
    
        
           

}
