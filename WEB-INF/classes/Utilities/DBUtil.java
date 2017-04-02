/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author saiku
 */
public class DBUtil {
      
    public static void closePreparedStatement(PreparedStatement ps){
        try{
            if(ps!=null){
                ps.close();
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }

        public static void closeResultSet(ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    
}
