/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Utilities.ConnectionPool;
import Utilities.DBUtil;
import business.Answer;
import static data.StudyDB.flag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author saiku
 */
public class AnswerDB {
      public static int addAnswer(String studycode, String choice, String email, String dt){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        String query
                = "SELECT * from Answer where StudyID=? and Choice=? and Email=? and DateSubmitted=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(studycode));
            ps.setString(2, choice);
            ps.setString(3, email);
            ps.setString(4, dt);
            rs=ps.executeQuery();
            int flag=1;
            while(rs.next()){
                flag--;break;
            }
            if(flag==1){
                query
                    = "INSERT INTO Answer values(?, ?, ?, ?)";
                ps=connection.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(studycode));
                ps.setString(2, email);
                ps.setString(3, choice);
                ps.setString(4, dt);
                ps.executeUpdate();
        
        
                query
                    = "Update Study SET ActParticipants=ActParticipants+1 where StudyID=?";
                ps=connection.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(studycode));
                ps.executeUpdate();
            }
            return flag;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        
       /* Answer answer=new Answer(email,dt,choice);
        int i=0;int flag=1;
        for(Study study_temp : nstudy){
            if(study_temp.getStudyCode().equals(studycode)){
                ArrayList<Answer> ans=new ArrayList<Answer>();
                if(study_temp.getAnswer()!=null){
                    ans=study_temp.getAnswer();
                    for(Answer ans_temp:ans){
                        if(ans_temp.getEmailOfParticipant().equals(email))
                            flag=0;
                    }
                }
                if(flag==1){
                ans.add(answer);
                study_temp.setAnswer(ans);
                int x=study_temp.getNumOfParticipants()+1;
                study_temp.setNumOfParticipants(x);
                nstudy.set(i, study_temp);
                }
                else
                    break;
            }
            i++;
        }   
        return flag;*/
       return flag;
    }  
      
    public static ArrayList<Answer> getAllAnswersByStudyCode(String scode){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        ArrayList<Answer> getAnswer=new ArrayList<>();
        Answer temp=null;
        String query
                = "SELECT  A.email as EmailOfParticipant, A.DateSubmitted as DateSubmitted,"
                + " A.choice as Choice from Answer as A join Study as S on A.StudyID=S.StudyID where A.StudyID=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(scode));
            rs=ps.executeQuery();
            while(rs.next()){
                temp=new Answer();
                temp.setEmailOfParticipant(rs.getString("EmailOfParticipant"));
                temp.setSubmissionDate(rs.getString("DateSubmitted"));
                temp.setChoice(rs.getString("Choice"));
                getAnswer.add(temp);
            }
            return getAnswer;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }        
        
        return null;
    }
    

    public static ArrayList<Answer> getAllAnswersByEmail(String email){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        ArrayList<Answer> getAnswer=new ArrayList<Answer>();
        String query
                = "SELECT  A.email as EmailOfParticipant, A.DateSubmitted as DateSubmitted,"
                + " A.choice as Choice from Study as S join Answer as A on S.email=A.email where S.email=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(email));
            rs=ps.executeQuery();
            Answer temp=new Answer();
            while(rs.next()){
                temp.setEmailOfParticipant(rs.getString("EmailOfParticipant"));
                temp.setSubmissionDate(rs.getString("DateSubmitted"));
                temp.setChoice(rs.getString("Choice"));
                getAnswer.add(temp);
            }
            return getAnswer;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }        
        
        return null;
    }
          
}
