/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.Answer;
import business.Reported;
import business.Study;
import business.User;
import data.AnswerDB;
import data.RecommendDB;
import data.ReportDB;
import data.StudyDB;
import data.UserDB;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
/**
 *
 * @author SriSaiKumar
 */

@WebServlet(name = "StudyController", urlPatterns = {"/StudyController"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class StudyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
                String url="/home.jsp";  // default home page for the application
        Study newstudy;
        HttpSession session=request.getSession();
        String action=request.getParameter("action");  // loads the action from jsp form page to string named action
        
        log("actually i came here at least:"+action);
        String studycode=request.getParameter("StudyCode");
        if(action==null){ 
            action="join";  // to avoid null pointer exception
         }
        
        //over
        if(action.equals("join")){
            User userexists=(User) session.getAttribute("theUser");
            if(userexists==null){
                if(session.getAttribute("theAdmin")==null){
                    url="/home.jsp";
                }
                else{
                    url="/admin.jsp";
                }
            }
            else{
                url="/main.jsp";
            }
        }
        
        //over
        else if(action.equals("participate")){
            User userexists=(User)session.getAttribute("theUser");
            if(userexists==null){
                url="/login.jsp";
            }
            else{
                String scode=request.getParameter("StudyCode");
                log("Study code------------------>>>>>>"+scode);
                if(scode==null){
                    ArrayList<Study>study_array;
                    study_array=StudyDB.getOpenStudies();
                    request.setAttribute("OpenStudies",study_array);
                    url="/participate.jsp";
                }
                else{
                    Study studyq=StudyDB.getStudy(scode);
                    request.setAttribute("StudyQuestion", studyq);
                    url="/question.jsp";
                }
                
            }
        }
        
        
        //over
        else if(action.equals("edit")){
            User userexists=(User)session.getAttribute("theUser");
            if(userexists==null)
                url="/login.jsp";
            else{
                String scode=request.getParameter("StudyCode");
                String email=userexists.getEmail();
                Study studyrecord=StudyDB.editStudy(scode);
                request.setAttribute("EditStudy",studyrecord);
                url="/editstudy.jsp";
            }
        }
        
        
        //over
        else if(action.equals("report")){
            User userexists=(User)session.getAttribute("theUser");
            if(userexists==null){
                url="/login.jsp";
            }
            else{
                String scode=request.getParameter("StudyCode");
                if(scode==null){
                    ArrayList<ArrayList<String>> user_report=new ArrayList<ArrayList<String>>();
                    user_report=ReportDB.getReportByUser(userexists.getEmail());
                    request.setAttribute("ReportHistory", user_report);
                    url="/reporth.jsp";
                }
                else{
                    ArrayList<String> user_report_add=new ArrayList<String>();
                    user_report_add.add(userexists.getEmail());
                    Date dNow = new Date();
                    SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy");
                    String d=ft.format(dNow);
                    user_report_add.add(d);
                    String question=StudyDB.getQuestion(scode);
                    user_report_add.add(question);
                    user_report_add.add("Pending");
                    user_report_add.add(scode);
                    ReportDB.addReport(user_report_add);
                    log("Got it------------------------------------"+user_report_add.get(1));
                    url="/confirmrep.jsp";
                }
            }
        }
        
        
        //over
        else if(action.equals("adminreportcheck")){
            User userexists=(User)session.getAttribute("theAdmin");
            if(userexists==null){
                url="/login.jsp";
            }
            else{
                ArrayList<Reported> reportcheck=new ArrayList<>();
                reportcheck=ReportDB.getAllOpenReports();
                request.setAttribute("AdminReportCheck",reportcheck);
                url="/reportques.jsp";
            }
        }
        
        
       
        else if(action.equals("approve")){
            User userexists=(User)session.getAttribute("theAdmin");
            if(userexists==null){
                url="/login.jsp";
            }
            else{
                String scode=request.getParameter("StudyCode");
                ArrayList<Reported> report_user=new ArrayList<>();
                report_user=ReportDB.updateReportStatus(scode,"approve");
                request.setAttribute("AdminReportCheck",report_user);
                url="/reportques.jsp";
            }
        }
        
        
        else if(action.equals("disapprove")){
            User userexists=(User)session.getAttribute("theAdmin");
            if(userexists==null){
                url="/login.jsp";
            }
            else{
                String scode=request.getParameter("StudyCode");
                ArrayList<Reported> report_user=new ArrayList<>();
                report_user=ReportDB.updateReportStatus(scode,"disapprove");
                request.setAttribute("AdminReportCheck",report_user);
                url="/reportques.jsp";
            }
        }
        
        
        //over
        else if(action.equals("update")){
            User userexists=(User)session.getAttribute("theUser");
            if(userexists==null){
                url="/login.jsp";
            }
            else{
                String scode=request.getParameter("StudyCode");
                Study updatestudy = new Study();
                updatestudy.setStudyCode(scode);
                updatestudy.setStudyName(request.getParameter("study_name"));
                updatestudy.setDateCreated(request.getParameter("datecreated"));
                updatestudy.setEmailOfCreator(userexists.getEmail());
                updatestudy.setQuestion(request.getParameter("question_text"));
/*                Part filePart=request.getPart("file"); 
                Path p = Paths.get(filePart.getSubmittedFileName());
                String fileName = p.getFileName().toString();
                InputStream is = filePart.getInputStream();
                ServletConfig conf = getServletConfig();
                ServletContext context = conf.getServletContext();
                String path = context.getRealPath("images\\"+fileName);
                OutputStream os = new FileOutputStream("D:\\nbad\\assignment-4\\images\\");
                if(is!=null){
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while((bytesRead = is.read(buffer)) !=-1){
                         os.write(buffer, 0, bytesRead);
                    }
                    is.close();
                    os.flush();
                    os.close();
                    updatestudy.setImageURL("images\\"+fileName);
                }
                else{
                    updatestudy.setImageURL(request.getParameter("imageURL"));
                }*/
                updatestudy.setImageURL(request.getParameter("imageURL"));
                updatestudy.setRequestedParticipants(Integer.parseInt(request.getParameter("participants")));
                updatestudy.setDescription(request.getParameter("description"));
                updatestudy.setStatus(request.getParameter("studystatus"));
                int noOfAnswers=Integer.parseInt(request.getParameter("answers"));
                ArrayList<String> answerobj=new ArrayList<>();
                        for(int i=0;i<noOfAnswers;i++){
                            Integer i1=i;
                            String temp="DynamicTextBox"+i1.toString();
                            String temp1=request.getParameter(temp);
                            answerobj.add(temp1);
                        }
                updatestudy.setAnswerChoice(answerobj);
                ArrayList<Study> study_array=new ArrayList<>();
                StudyDB.updateStudy(scode, updatestudy);
                study_array=StudyDB.getStudies(userexists.getEmail());
                request.setAttribute("MyStudies",study_array);
                url="/studies.jsp";
            }
        }
        
        
        
        //over
        else if(action.equals("add")){
            User userexists=(User)session.getAttribute("theUser");
            if(userexists==null){
                url="/login.jsp";
            }
            else{
                String scode=StudyDB.getStudyCode();
                Study addstudy = new Study();
                addstudy.setStudyCode(scode);
                addstudy.setEmailOfCreator(userexists.getEmail());
                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy");
                String d=ft.format(dNow);  
                addstudy.setDateCreated(d);
                addstudy.setStatus("start");
                addstudy.setStudyName(request.getParameter("study_name"));
                addstudy.setQuestion(request.getParameter("question_text"));
                /*Part filePart=request.getPart("file"); 
                Path p = Paths.get(filePart.getSubmittedFileName());
                String fileName = p.getFileName().toString();
                InputStream is = filePart.getInputStream();
                OutputStream os = new FileOutputStream("D:\\nbad\\Assignment-4\\images\\"+fileName);
                if(is!=null){
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while((bytesRead = is.read(buffer)) !=-1){
                         os.write(buffer, 0, bytesRead);
                    }
                    is.close();
                    os.flush();
                    os.close();
                    addstudy.setImageURL("images\\"+fileName);
                }
                else{
                    addstudy.setImageURL(request.getParameter("imageURL"));
                }*/
                addstudy.setImageURL(request.getParameter("imageURL"));
                addstudy.setRequestedParticipants(Integer.parseInt(request.getParameter("participants")));
                addstudy.setDescription(request.getParameter("description"));
                int noOfAnswers=Integer.parseInt(request.getParameter("answers"));
                ArrayList<String> answerobj=new ArrayList<>();
                        for(int i=0;i<noOfAnswers;i++){
                            Integer i1=i;
                            String temp="DynamicTextBox"+i1.toString();
                            String temp1=request.getParameter(temp);
                            answerobj.add(temp1);
                        }
                addstudy.setAnswerChoice(answerobj);
                userexists.setNumPostedStudies(userexists.getNumPostedStudies()+1);
                StudyDB.addStudy(addstudy);
                ArrayList<Study> study_array=new ArrayList<>();
                study_array=StudyDB.getStudies(userexists.getEmail());
                request.setAttribute("MyStudies",study_array);
                        url="/studies.jsp";                
            }
        }
        
        
        //over
        else if(action.equals("stop")){
            User userexists=(User) session.getAttribute("theUser");
            if(userexists==null){
                url="/login.jsp";
            }
            else{
                String scode=request.getParameter("StudyCode");
                Study study_temp=StudyDB.getStudy(studycode);
                study_temp.setStatus("start");
                StudyDB.updateStudy(scode, study_temp);
                ArrayList<Study> study_array=new ArrayList<Study>();
                study_array=StudyDB.getStudies(userexists.getEmail());
                request.setAttribute("MyStudies",study_array);
                url="/studies.jsp";
            }
        }

        //over
//start functions as stop and vice versa
        else if(action.equals("start")){
            User userexists=(User) session.getAttribute("theUser");
            if(userexists==null){
                url="/login.jsp";
            }
            else{
                String scode=request.getParameter("StudyCode");
                Study study_temp=StudyDB.getStudy(studycode);
                study_temp.setStatus("stop");
                StudyDB.updateStudy(scode, study_temp);
                ArrayList<Study> study_array=new ArrayList<Study>();
                study_array=StudyDB.getStudies(userexists.getEmail());
                request.setAttribute("MyStudies",study_array);
                url="/studies.jsp";
            }
        }
        
        //over
        else if(action.equals("answer")){
            User userexists=(User) session.getAttribute("theUser");
            if(userexists==null){
                url="/login.jsp";
            }
            else{
                String scode=request.getParameter("StudyCode");
                String choice=request.getParameter("choice");
                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy");
                String d=ft.format(dNow);
                int stat=AnswerDB.addAnswer(scode,choice,userexists.getEmail(),d);
                if(stat==1){
                    int participants=userexists.getNumOfParticipants()+1;
                    String email1=StudyDB.getEmailOfCreator(scode);
                    UserDB.updateUserNumOfParticipants(email1, participants);
                    Integer coins=userexists.getNumCoins()+1;
                    Integer participations=userexists.getNumParticipation()+1;
                    userexists.setNumCoins(coins);
                    userexists.setNumParticipation(participations);
                    UserDB.updateUserCP(userexists.getEmail(), coins, participations);
                    Integer c=coins;
                    Integer p=participations;
                    Integer p1=participants;
                    if(userexists.getEmail().equals(email1)){
                        userexists.setNumOfParticipants(participants);                        
                        session.setAttribute("numOfParticipants",p1.toString());
                    }
                    session.setAttribute("theUser",userexists);
                    session.setAttribute("numOfCoins",c.toString());
                    session.setAttribute("numOfParticipations",p.toString());
                }    
                ArrayList<Study>study_array;
                study_array=StudyDB.getOpenStudies();
                request.setAttribute("OpenStudies",study_array);
                RecommendDB.updateUserCoins(userexists.getEmail());
                url="/participate.jsp";                
            }    
        }
        
        //over
        else if(action.equals("studies")){
            User userexists=(User)session.getAttribute("theUser");
            if(userexists==null){
                url="/login.jsp";
            }
            else{
                ArrayList<Study> study_array=StudyDB.getStudies(userexists.getEmail());
                request.setAttribute("MyStudies", study_array);
                url="/studies.jsp";
            }
        }
        
        //over
        else if(action.equals("getanswer")){
            //User userexists=(User)session.getAttribute("theUser");
            String scode=request.getParameter("StudyCode");
            ArrayList<Answer> getAnswer=new ArrayList<>();
            getAnswer=AnswerDB.getAllAnswersByStudyCode(scode);
            request.setAttribute("AllAnswers",getAnswer);
            url="/allanswers.jsp";
        }
        
        
        
        
       getServletContext().getRequestDispatcher(url).forward(request, response);

    }

 
}
