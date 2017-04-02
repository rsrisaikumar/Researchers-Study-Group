/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Utilities.ConnectionPool;
import Utilities.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author saiku
 */
public class RecommendDB {
  
    
    public static void insert(String from, String to, String recid, String uuid){
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT into Recommend values(?, ?, ?, ?,0)";
        try {
            ps =connection.prepareStatement(query);
            ps.setString(1, from);
            ps.setString(2, to);
            ps.setString(3, recid);
            ps.setString(4, uuid);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }       
    }
    
    
    public static void updateStatus(String recid, String uuid){
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "UPDATE Recommend set Status=1 where RecID=? and Token=?";
        try {
            ps =connection.prepareStatement(query);
            ps.setString(1, recid);
            ps.setString(2, uuid);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }       
    }
    
    
    public static void updateUserCoins(String email){
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "UPDATE Recommend, User set coins=coins+2 where User.email=Recommend.RecommenderEmail and RecommendedEmail=? and Status=1";
        try {
            ps =connection.prepareStatement(query);
            ps.setString(1, email);
            ps.executeUpdate();
            query
                = "DELETE from Recommend where RecommendedEmail=? ";
            ps =connection.prepareStatement(query);
            ps.setString(1, email);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }       
    }
        
}
