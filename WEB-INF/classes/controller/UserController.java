/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import Utilities.PasswordUtil;
import business.Study;
import business.User;
import data.RecommendDB;
import data.StudyDB;
import data.UserDB;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SriSaiKumar
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action=request.getParameter("action");
        String uuid=request.getParameter("token");
        String userid=request.getParameter("userid");
        User user_ins;
        String url="/home.jsp";
        try{
        if(action.equals("create")){
                user_ins=UserDB.createUser(userid,uuid);
                UserDB.insert(user_ins);
        }   
        url="/login.jsp";        
        request.setAttribute("login_msg","Account successfully activated.");
        }catch(Exception e){}
        getServletContext().getRequestDispatcher(url).forward(request, response);        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url="/home.jsp";  // default home page for the application
        User newuser;
        final String utilemail="test050293@gmail.com";
        final String utilpass="nbadassignment";
        HttpSession session=request.getSession();
        String action=request.getParameter("action");  // loads the action from jsp form page to string named action
        if(action==null){ 
            action="join";  // to avoid null pointer exception
         }
        
        if(action.equals("join")){
        url="/home.jsp"; // maintain the browser in the same jsp page
        }
         
        else if(action.equals("signup")){
            String name=request.getParameter("name");
            String email=request.getParameter("email");
            String password=request.getParameter("password");
            String confirm_password=request.getParameter("confirm_password");
                int flag=0;
                User checkuser=new User();
                try{
                checkuser = UserDB.getUser(email);
                }catch(Exception e){};
                if(checkuser!=null){
                    String msg="Sign Up failed. Email already exists";
                    request.setAttribute("signup_msg",msg);
                    url="/signup.jsp";
                    flag=1;
                }
                if(flag==0&&password.equals(confirm_password)&&!PasswordUtil.checkPasswordStrength(password)){
                    String msg="Sign Up failed. Very weak password";
                    request.setAttribute("signup_msg",msg);
                    url="/signup.jsp";                    
                }
                if(flag==0&&!(password.equals(confirm_password))&&!PasswordUtil.checkPasswordStrength(password)){
                    String msg="Sign Up failed. Very weak password and passwords dont match";
                    request.setAttribute("signup_msg",msg);
                    url="/signup.jsp";                    
                }                
                else if(flag==0&&password.equals(confirm_password)&&PasswordUtil.checkPasswordStrength(password)){
                    if(password.equals(confirm_password)){
                     String type="participant";
                     String salt=PasswordUtil.getSalt();
                     String spass=password;
                        try {
                            spass = PasswordUtil.hashPassword(password+salt);
                            UserDB.insertHashAndSaltPassword(email,spass,salt);
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                     newuser = new User(email,spass,name,type,0,0,0,0);
                     String uuid=UUID.randomUUID().toString();
                     String userid=UUID.randomUUID().toString();
                     UserDB.insertTempUser(newuser,userid,uuid);
                     if(request.getParameter("rec_id")!=null&&request.getParameter("rec_token")!=null){
                        RecommendDB.updateStatus(request.getParameter("rec_id"),request.getParameter("rec_token"));
                    }
////////////////////////////////////////
                Properties prop=new Properties();
                prop.put("mail.smtp.host","smtp.gmail.com");
                prop.put("mail.smtp.port","587");
                prop.put("mail.smtp.auth","true");
                prop.put("mail.smtp.starttls.enable","true");
                
                Authenticator auth=new Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(utilemail, utilpass);
                    }                    
                };
                    String message="Hi "+name;
                    message+="\n\nPlease click the following link to activate your account\n\n";
                    message+="http://localhost:8080/Assignment-4/UserController?action=create&userid="+userid+"&token="+uuid+"\n\n";
//                    message+="https://nbad3-sskassignment1.rhcloud.com/Assignment-4/UserController?action=create&userid="+userid+"&token="+uuid+"\n\n";
                    message+="Regards,"+"\n"+"Researchers Exchange Participations Technical Team";
                    Session sess=Session.getDefaultInstance(prop,auth);
                    Message msg=new MimeMessage(sess);
                    try{
                    msg.setSubject("Email Activation Link: Researchers Exchange Participations");
                    msg.setText(message);
                    msg.setFrom(new InternetAddress(utilemail,"Researchers Exchange Participations"));
                    msg.setRecipient(Message.RecipientType.TO,new InternetAddress(email));
                    
                    Transport.send(msg);
                     request.setAttribute("confirm_signup_msg","An account activation link is sent to your email.");
                     url="/confirmsignup.jsp";

                    }catch(MessagingException e){}
                
///////////////////////////////////////                    
                    }
                    else{
                    String signup_msg="Sign Up failed. Password and Confirm Passwords do not match";
                    request.setAttribute("signup_msg",signup_msg);
                    url="/signup.jsp";        
                }
                }
                    
        }
                
        else if(action.equals("login")){
                User user1;
                String sl="";
                String pas="";
                try{
                sl=PasswordUtil.getSalt();
                pas=PasswordUtil.hashPassword("nbadassignment"+sl);
                UserDB.insertHashAndSaltPassword("srisaikumarr@gmail.com", pas, sl);
                }catch(Exception e){}
                user1=new User("srisaikumarr@gmail.com",pas,"SSK","participant",0,0,0,0);
                UserDB.insert(user1);
                User user2;
                try{
                sl=PasswordUtil.getSalt();
                pas=PasswordUtil.hashPassword("ssk"+sl);
                UserDB.insertHashAndSaltPassword("sravipa1@uncc.edu", pas, sl);
                }catch(Exception e){}
                user2=new User("sravipa1@uncc.edu",pas,"Sri Sai Kumar","admin",0,0,0,0);
                UserDB.insert(user2);
                Study study1;
                ArrayList<String> answerchoice1=new ArrayList<>();
                answerchoice1.add("3");
                answerchoice1.add("4");
                answerchoice1.add("5");                
                study1=new Study("GUI","1","05/02/2016","srisaikumarr@gmail.com", "I enjoy outdoor activities", 10, 0, "About GUI", "images/small_tree.jpg", "start", answerchoice1, null);
                StudyDB.addStudy(study1);
                Study study2;
                ArrayList<String> answerchoice2=new ArrayList<String>();
                answerchoice2.add("3");
                answerchoice2.add("4");
                answerchoice2.add("5");                
                study2=new Study("Sec","2","19/03/2016","srisaikumarr@gmail.com", "I use computers on daily basis", 5, 0, "About Computers", "images/computer.jpg", "stop", answerchoice2, null);
                StudyDB.addStudy(study2);
            
             String email=request.getParameter("email");
             String password=request.getParameter("password");
             try{
             password=PasswordUtil.hashPassword(password+UserDB.getUserSalt(email));
             }catch(Exception e){}
             newuser=new User();
             try{
             newuser=UserDB.getUser(email);}catch(Exception e){};
             String pwd=null;
             
             try{
                pwd=newuser.getPassword();
             }
             catch(Exception e){
                 String login_msg="Error in login. Email or Password mismatch";
                 request.setAttribute("login_msg",login_msg);
                 url="/login.jsp";                 
             }
             if(pwd!=null && pwd.equals(password)){
                 String typeuser=newuser.getType();
                 log("TYPE_________________________>"+typeuser);
                 if(typeuser.equals("participant")){
                    session=request.getSession();
                    session.setAttribute("theUser", newuser);
                    session.setAttribute("numOfCoins",newuser.getNumCoins());
                    session.setAttribute("numOfParticipations",newuser.getNumParticipation());
                    session.setAttribute("numOfPostedStudies",newuser.getNumPostedStudies());

                    url="/main.jsp";
                    }
                 else{
                    session=request.getSession();
                    session.setAttribute("theAdmin", newuser);
                    url="/admin.jsp";
                 }
             }
             else
             {
                 String login_msg="Error in login. Email or Password mismatch";
                 request.setAttribute("login_msg",login_msg);
                 url="/login.jsp";
             }
        }
        
 
        
        else if(action.equals("how")){
         User userexists=(User)session.getAttribute("theUser");
         if(userexists==null){
             url="/how.jsp";
         }else{
             url="/main.jsp";
         }
        }
        
         
        else if(action.equals("about")){
            User userexists=(User)session.getAttribute("theUser");
         if(userexists==null){
             url="/aboutl.jsp";
         }else{
             url="/about.jsp";
         }
        }
         
                
                 
        else if(action.equals("home")){
            User userexists=(User)session.getAttribute("theUser");
         if(userexists==null){
             url="/home.jsp";
         }else{
             url="/main.jsp";
         }
     }

        
                
        else if(action.equals("main")){
            User userexists=(User)session.getAttribute("theUser");
         if(userexists==null){
             url="/login.jsp";
         }else{
             url="/main.jsp";
         }
     } 
                
                
                
               
        else if(action.equals("logout")){
            User foruserexists=(User)session.getAttribute("theUser");
            User foradminexists=(User)session.getAttribute("theAdmin");
            if(foruserexists==null && foradminexists==null){
                  url="/home.jsp";
            } 
            else {
             session.invalidate();
            }
            url="/home.jsp";
      }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }


}
