/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Utilities.ConnectionPool;
import Utilities.DBUtil;
import business.Reported;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author saiku
 */
public class ReportDB {



    public static void addReport(ArrayList<String> user_report){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT into Reported values(?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user_report.get(0));
            ps.setString(2, user_report.get(1));
            ps.setString(3, user_report.get(2));
            ps.setString(4, user_report.get(3));
            ps.setInt(5, Integer.parseInt(user_report.get(4)));        
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
        
        
        /*
        int size=reportbyuser.size();
        ArrayList<String>temp=new ArrayList<String>();
        temp.add(user_report.get(0));
        temp.add(user_report.get(1));
        temp.add(user_report.get(2));
        temp.add(user_report.get(3));
        temp.add(user_report.get(4));        
        reportbyuser.add(temp);*/
    }
    
    
    
    public static ArrayList<Reported> getAllOpenReports(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        ArrayList<Reported>report_array=new ArrayList<Reported>();
        String query
                = "SELECT S.StudyID as StudyID, S.StudyName as StudyName, R.Question as Question, R.DateReported as DateReported, "
                + "R.Status as Status, count(*) as NumOfParticipants from Study as S join Reported as R"
                + " on S.studyID=R.studyID where R.Status like '%Disapproved%' or R.Status like '%Pending%' "
                + " Group by S.StudyID";
        try {
            ps = connection.prepareStatement(query);
            rs=ps.executeQuery();
            Reported temp=null;
            while(rs.next()){
                temp=new Reported();
                Integer s=rs.getInt("StudyID");
                temp.setStudyCode(s.toString());
                temp.setStudyName(rs.getString("StudyName"));
                temp.setQuestion(rs.getString("Question"));
                temp.setDateReported(rs.getString("DateReported"));
                temp.setNumOfParticipants(rs.getInt("NumOfParticipants"));
                temp.setStatus(rs.getString("Status"));
                report_array.add(temp);
                }
            return report_array;
            }catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
      return null;
                
       /* 
        ArrayList<ArrayList<String>> temp_oreport_array=new ArrayList<ArrayList<String>>();
        for(ArrayList<String> temp_report:reportbyuser){
            if(temp_report.get(3).equals("Disapproved")||temp_report.get(3).equals("Pending")){
                temp_oreport_array.add(temp_report);
            }
        }
        return temp_oreport_array;*/
    }
    

    public static ArrayList<Reported> updateReportStatus(String studycode, String status){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;        
        String query
                = "UPDATE Reported  set Status=? where StudyID=?";
        try {
            ps = connection.prepareStatement(query);
            if(status.equals("approve"))
                ps.setString(1, "Approved");
            else 
                ps.setString(1, "Disapproved");
            ps.setInt(2, Integer.parseInt(studycode));
            ps.executeUpdate();
            
            
            query
                = "SELECT S.StudyID as StudyID, S.StudyName as StudyName, R.Question as Question, R.DateReported as DateReported, "
                + "R.Status as Status, count(*) as NumOfParticipants from Study as S join Reported as R"
                + " on S.studyID=R.studyID where R.Status like '%Disapproved%' or R.Status like '%Pending%' "
                + " Group by S.StudyID";
            ps=connection.prepareStatement(query);
            rs=ps.executeQuery();
            ArrayList<Reported> temp_report=new ArrayList<>();
            Reported temp=null;
            while(rs.next()){
                    temp=new Reported();
                Integer s=rs.getInt("StudyID");
                temp.setStudyCode(s.toString());
                temp.setStudyName(rs.getString("StudyName"));
                temp.setQuestion(rs.getString("Question"));
                temp.setDateReported(rs.getString("DateReported"));
                temp.setNumOfParticipants(rs.getInt("NumOfParticipants"));
                temp.setStatus(rs.getString("Status"));
                temp_report.add(temp);
                }
            return temp_report;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }        
        return null;
        
        /*
        int i=0;
        int set=0;
        for(ArrayList report_temp : reportbyuser){
            if(report_temp.get(4).equals(studycode)){
                if(status=="approve")
                 reportbyuser.get(i).set(3,"Approved");
                else
                 reportbyuser.get(i).set(3,"Disapproved");                    
                 break;
            }
            i++;
        }
        return reportbyuser;
        */
    }

    
    public static ArrayList<ArrayList<String>> getReportByUser(String email){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        ArrayList<ArrayList<String>>report_array=new ArrayList<ArrayList<String>>();
        String query
                = "SELECT Email, DateReported, Question, Status, StudyID from Reported where email=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs=ps.executeQuery();
            ArrayList<String> temp=null;
            while(rs.next()){
                temp=new ArrayList<String>();
                temp.add(rs.getString("Email"));
                temp.add(rs.getString("DateReported"));
                temp.add(rs.getString("Question"));
                temp.add(rs.getString("Status"));
                Integer s=rs.getInt("StudyID");
                temp.add(s.toString());
                report_array.add(temp);
                }
            return report_array;
            }catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
      return null;
      
        

      /*
        //hardcoded values
        int i=0;
        ArrayList<ArrayList<String>> temp_report_array=new ArrayList<ArrayList<String>>();
        for(ArrayList<String> temp_report:reportbyuser){
            if(temp_report.get(0).equals(email)){
                temp_report_array.add(temp_report);
            }
            i++;
        }
        return temp_report_array;*/
    }
    
        
}
