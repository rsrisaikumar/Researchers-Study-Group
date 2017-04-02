/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Utilities.ConnectionPool;
import Utilities.DBUtil;
import business.Study;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author SriSaiKumar
 */
public class StudyDB {
    
    static ArrayList<Study> nstudy = new ArrayList<>();
    static ArrayList<ArrayList<String>> reportbyuser = new ArrayList<>();
    static int flag=0;
    
    

    
    
    public static Study getStudy(String studyCode){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs= null;
        String query
                = "select S.studyid as StudyID, S.studyname as StudyName, S.datecreated as DateCreated, S.email as EmailOfCreator, "+
                  "Q.question as Question, S.reqparticipants as ReqParticipants, S.actparticipants as ActParticipants, S.description as Description,"+
                  "S.imageURL as ImageURL, S.sstatus as SStatus, Q.option1 as Option1,Q.option2 as Option2, Q.option3 as Option3, Q.option4 as Option4,"+
                  "Q.option5 as Option5 from Study as S join Question as Q on S.StudyID=Q.StudyID where S.studyID=?";
        try {
            ps =connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(studyCode));
            rs=ps.executeQuery();
            Study study_temp=null;
            ArrayList<String> answerchoice_temp=null;
            while(rs.next()){
                    study_temp=new Study();
                    Integer s=rs.getInt("StudyID");
                    study_temp.setStudyCode(s.toString());
                    study_temp.setStudyName(rs.getString("StudyName"));
                    study_temp.setDateCreated(rs.getString("DateCreated"));
                    study_temp.setEmailOfCreator(rs.getString("EmailOfCreator"));
                    study_temp.setRequestedParticipants(rs.getInt("ReqParticipants"));
                    study_temp.setNumOfParticipants(rs.getInt("ActParticipants"));
                    study_temp.setDescription(rs.getString("Description"));
                    study_temp.setStatus(rs.getString("SStatus"));
                    study_temp.setImageURL(rs.getString("ImageURL"));
                    study_temp.setQuestion(rs.getString("Question"));
                
                    answerchoice_temp=new ArrayList<>();
                    answerchoice_temp.add(rs.getString("Option1"));
                    answerchoice_temp.add(rs.getString("Option3"));
                    answerchoice_temp.add(rs.getString("Option2"));
                    if(rs.getString("Option4")!=null)
                        answerchoice_temp.add(rs.getString("Option4"));
                    if(rs.getString("Option5")!=null)
                        answerchoice_temp.add(rs.getString("Option5"));
                    study_temp.setAnswerChoice(answerchoice_temp); 
                    break;
            }
            return study_temp;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return null;
       
         /*Iterator<Study> iterator = nstudy.iterator();
          while(iterator.hasNext()){
              Study study_temp = (Study) iterator.next();
              if(study_temp.getStudyCode().equals(studyCode)){
                  return study_temp;
              }
          }
          return null;*/
    }
    

    
    public static ArrayList<Study> getStudies(String email){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs= null;
        String query
                = "select S.studyid as StudyID, S.studyname as StudyName, S.datecreated as DateCreated, S.email as EmailOfCreator, "+
                  "Q.question as Question, S.reqparticipants as ReqParticipants, S.actparticipants as ActParticipants, S.description as Description,"+
                  "S.imageURL as ImageURL, S.sstatus as SStatus, Q.option1 as Option1,Q.option2 as Option2, Q.option3 as Option3, Q.option4 as Option4,"+
                  "Q.option5 as Option5 from Study as S join Question as Q on S.studyid=Q.studyid  where S.Email=?";
        try {
            ps =connection.prepareStatement(query);
            ps.setString(1, email);
            rs=ps.executeQuery();
            Study study_temp=null;
            ArrayList<Study> study_temp_array = new ArrayList<>();
            ArrayList<String> answerchoice_temp=null;
            while(rs.next()){
                    study_temp=new Study();
                    Integer s=rs.getInt("StudyID");
                    study_temp.setStudyCode(s.toString());
                    study_temp.setStudyName(rs.getString("StudyName"));
                    study_temp.setQuestion(rs.getString("Question"));
                    study_temp.setDateCreated(rs.getString("DateCreated"));
                    study_temp.setEmailOfCreator(rs.getString("EmailOfCreator"));
                    study_temp.setRequestedParticipants(rs.getInt("ReqParticipants"));
                    study_temp.setNumOfParticipants(rs.getInt("ActParticipants"));
                    study_temp.setDescription(rs.getString("Description"));
                    study_temp.setStatus(rs.getString("SStatus"));
                    study_temp.setImageURL(rs.getString("ImageURL"));
                
                    answerchoice_temp=new ArrayList<>();
                    answerchoice_temp.add(rs.getString("Option1"));
                    answerchoice_temp.add(rs.getString("Option2"));
                    answerchoice_temp.add(rs.getString("Option3"));
                    if(rs.getString("Option4")!=null)
                        answerchoice_temp.add(rs.getString("Option4"));
                    if(rs.getString("Option5")!=null)
                        answerchoice_temp.add(rs.getString("Option5"));
                    study_temp.setAnswerChoice(answerchoice_temp);  
                    study_temp_array.add(study_temp);
                }
            return study_temp_array;
            } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return null;

        /*ArrayList<Study> study_temp_array = new ArrayList<>();
        int c=0;
        for(Study study_temp:nstudy){
            if(study_temp.getEmailOfCreator().equals(email)){
                study_temp_array.add(study_temp);
            }
            c++;
        }
        if(c>0)
            return study_temp_array;
        return null;*/
    }
    
    
    public static void addStudy(Study study){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO Study (StudyID, StudyName, Description , Email, DateCreated, ImageURL, ReqParticipants, ActParticipants, SStatus) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(study.getStudyCode()));
            ps.setString(2, study.getStudyName());
            ps.setString(3, study.getDescription());
            ps.setString(4, study.getEmailOfCreator());
            ps.setString(5, study.getDateCreated());
            ps.setString(6, study.getImageURL());
            ps.setInt(7, study.getRequestedParticipants());            
            ps.setInt(8, study.getNumOfParticipants());
            ps.setString(9, study.getStatus());
            ps.executeUpdate();
            
            
            query
                = "INSERT INTO Question(StudyID, Question, Option1, Option2, Option3, Option4, Option5) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(study.getStudyCode()));
            ps.setString(2, study.getQuestion());
            ps.setString(3, study.getAnswerChoice().get(0));
            ps.setString(4, study.getAnswerChoice().get(1));
            ps.setString(5, study.getAnswerChoice().get(2));
            if(study.getAnswerChoice().size()>=4)
                ps.setString(6, study.getAnswerChoice().get(3));
            else
                ps.setString(6, null);
            if(study.getAnswerChoice().size()>4)
                ps.setString(7, study.getAnswerChoice().get(4));
            else
                ps.setString(7, null);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
        /*
        if(nstudy.size()==0)
            nstudy.add(study);
        else{
            int flag=0;
        for(Study study_temp:nstudy){
            if(study_temp.getStudyCode().equals(study.getStudyCode()))
                flag=1;
        }
        if(flag==0)
        nstudy.add(study);
        }*/
    }
    
    
    public static void updateStudy(String SCode, Study study){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "UPDATE Study Set StudyName=? , Description=? , Email=?, DateCreated=?, ReqParticipants=?, SStatus=?, ImageURL=? "+
                  "Where StudyID=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, study.getStudyName());
            ps.setString(2, study.getDescription());
            ps.setString(3, study.getEmailOfCreator());
            ps.setString(4, study.getDateCreated());
            ps.setInt(5, study.getRequestedParticipants());            
            ps.setString(6, study.getStatus());
            ps.setString(7, study.getImageURL());
            ps.setInt(8, Integer.parseInt(study.getStudyCode()));
            ps.executeUpdate();

        query
                = "UPDATE Question set Question=?, Option1=?, Option2=?, Option3=?, Option4=?, Option5=? "
                + "WHERE StudyID=?";
            ps = connection.prepareStatement(query);
            ps.setString(1, study.getQuestion());
            ps.setString(2, study.getAnswerChoice().get(0));
            ps.setString(3, study.getAnswerChoice().get(1));
            ps.setString(4, study.getAnswerChoice().get(2));
            if(study.getAnswerChoice().size()>=4)
                ps.setString(5, study.getAnswerChoice().get(3));            
            if(study.getAnswerChoice().size()>4)
                ps.setString(6, study.getAnswerChoice().get(4));
            ps.setInt(7, Integer.parseInt(study.getStudyCode()));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        /*
        int i=0;
        ArrayList<Study> newstudy=new ArrayList<>();
        for(Study study_temp : nstudy){
            if(study_temp.getStudyCode().equals(SCode)){
                newstudy.add(study);
            }
            else
            {
                newstudy.add(study_temp);
            }
            i++;
        }  
        nstudy=newstudy;*/
    }
    
    
    public static Study editStudy(String SCode){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs= null;
        String query
                = "select S.studyid as StudyID, S.studyname as StudyName, S.datecreated as DateCreated, S.email as EmailOfCreator, "+
                  "Q.question as Question, S.reqparticipants as ReqParticipants, S.actparticipants as ActParticipants, S.description as Description,"+
                  "S.imageURL as ImageURL, S.sstatus as SStatus, Q.option1 as Option1,Q.option2 as Option2, Q.option3 as Option3, Q.option4 as Option4,"+
                  "Q.option5 as Option5 from Study as S join Question as Q on S.studyid=Q.studyid  where S.studyID=?";
        try {
            ps =connection.prepareStatement(query);
            ps.setString(1, SCode);
            rs=ps.executeQuery();
            Study study_temp=null;
            ArrayList<String> answerchoice_temp=null;
            while(rs.next()){
                    study_temp=new Study();
                    Integer s=rs.getInt("StudyID");
                    study_temp.setStudyCode(s.toString());
                    study_temp.setStudyName(rs.getString("StudyName"));
                    study_temp.setQuestion(rs.getString("Question"));
                    study_temp.setDateCreated(rs.getString("DateCreated"));
                    study_temp.setEmailOfCreator(rs.getString("EmailOfCreator"));
                    study_temp.setRequestedParticipants(rs.getInt("ReqParticipants"));
                    study_temp.setNumOfParticipants(rs.getInt("ActParticipants"));
                    study_temp.setDescription(rs.getString("Description"));
                    study_temp.setImageURL(rs.getString("ImageURL"));
                    study_temp.setStatus(rs.getString("SStatus"));
                
                    answerchoice_temp=new ArrayList<>();
                    answerchoice_temp.add(rs.getString("Option1"));
                    answerchoice_temp.add(rs.getString("Option3"));
                    answerchoice_temp.add(rs.getString("Option2"));
                    if(rs.getString("Option4")!=null)
                        answerchoice_temp.add(rs.getString("Option4"));
                    if(rs.getString("Option5")!=null)
                        answerchoice_temp.add(rs.getString("Option5"));
                    study_temp.setAnswerChoice(answerchoice_temp);
                    break;
            }
            return study_temp;            
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return null;        
        
        /*Iterator<Study> iterator = nstudy.iterator();
        while(iterator.hasNext()){
              Study study_temp = (Study) iterator.next();
              if(study_temp.getStudyCode().equals(SCode)){
                   return study_temp;
              }
         }
        return null;*/        
    }
    
    
    public static ArrayList<Study> getOpenStudies(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs= null;
        String query
                = "select S.studyid as StudyID, S.studyname as StudyName, S.datecreated as DateCreated, "
                + "S.email as EmailOfCreator, Q.question as Question, S.reqparticipants as ReqParticipants, S.actparticipants as ActParticipants, "
                + "S.description as Description, S.imageURL as ImageURL, S.sstatus as SStatus, Q.option1 as Option1,Q.option2 as Option2, Q.option3 as Option3, "
                + "Q.option4 as Option4,Q.option5 as Option5 from Study as S join Question as Q on S.StudyID=Q.StudyID where S.sstatus like '%start%';";
        try {
            ps =connection.prepareStatement(query);
            rs=ps.executeQuery();
            Study study_temp=null;
            ArrayList<Study> study_temp_array = new ArrayList<>();
            ArrayList<String> answerchoice_temp=null;
            while(rs.next()){
                    study_temp=new Study();
                    Integer s=rs.getInt("StudyID");
                    study_temp.setStudyCode(s.toString());
                    study_temp.setQuestion(rs.getString("Question"));
                    study_temp.setStudyName(rs.getString("StudyName"));
                    study_temp.setDateCreated(rs.getString("DateCreated"));
                    study_temp.setEmailOfCreator(rs.getString("EmailOfCreator"));
                    study_temp.setRequestedParticipants(rs.getInt("ReqParticipants"));
                    study_temp.setNumOfParticipants(rs.getInt("ActParticipants"));
                    study_temp.setDescription(rs.getString("Description"));
                    study_temp.setImageURL(rs.getString("ImageURL"));
                    study_temp.setStatus(rs.getString("SStatus"));
                
                    answerchoice_temp=new ArrayList<>();
                    answerchoice_temp.add(rs.getString("Option1"));
                    answerchoice_temp.add(rs.getString("Option3"));
                    answerchoice_temp.add(rs.getString("Option2"));
                    if(rs.getString("Option4")!=null)
                        answerchoice_temp.add(rs.getString("Option4"));
                    if(rs.getString("Option5")!=null)
                        answerchoice_temp.add(rs.getString("Option5"));
                    study_temp.setAnswerChoice(answerchoice_temp); 
                    study_temp_array.add(study_temp);                    
            }
            System.out.println(study_temp_array);
            return study_temp_array;
            } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return null;
        /*
        String status="start";
        ArrayList<Study> study_temp_array = new ArrayList();
        Iterator<Study> iterator = nstudy.iterator();
        int c=0;
        while(iterator.hasNext()){
              Study study_temp = (Study) iterator.next();
              if(study_temp.getStatus().equals(status)){
                   study_temp_array.add(study_temp);
              }
              c++;
         }
        if(c>0)
            return study_temp_array;
        return null;   */    
    }
  

    public static String getStudyCode(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs= null;
        String query
                = "SELECT StudyID from Study order by StudyID desc limit 1";
        try {
            ps =connection.prepareStatement(query);
            rs=ps.executeQuery();
            Integer s=1;
            while(rs.next()){
                s=rs.getInt("StudyID")+1;
                break;
            }
            return s.toString();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
     
        return "1";

        /*
        Integer code=nstudy.size()+1;
        String c=code.toString();
        return c;*/
    }
    
    
    public static String getEmailOfCreator(String scode){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        String query
                = "SELECT Email from Study where StudyID=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(scode));
            rs=ps.executeQuery();
            while(rs.next()){
                return rs.getString("Email");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        
        
       /* 
        for(Study s:nstudy){
            if(s.getStudyCode().equals(scode)){
                return s.getEmailOfCreator();
            }
        }*/
        return null;
    }
    

    
    public static String getQuestion(String studycode){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        String query
                = "SELECT Question from Question where StudyID=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(studycode));
            rs=ps.executeQuery();
            while(rs.next()){
                return rs.getString("Question");
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return null;
        

        /*
        Iterator<Study> iterator = nstudy.iterator();
        while(iterator.hasNext()){
              Study study_temp = (Study) iterator.next();
              if(study_temp.getStudyCode().equals(studycode)){
                   return study_temp.getQuestion();
              }
         }
        return null;*/                
    }
    
    


}
