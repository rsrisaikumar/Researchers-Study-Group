/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.User;
import data.RecommendDB;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author saiku
 */
@WebServlet(name = "RCController", urlPatterns = {"/RCController"})
public class RCController extends HttpServlet {
    static String pass="nbadassignment";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String recid=request.getParameter("recid");
        String token=request.getParameter("token");
        String url="/signup.jsp";
        request.setAttribute("rec_id", recid);
        request.setAttribute("rec_token", token);
        getServletContext().getRequestDispatcher(url).forward(request, response);        
    }


    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String url="/home.jsp";
        String action=request.getParameter("action");
        HttpSession session=request.getSession();
        final User userexists=(User) session.getAttribute("theUser");
        
        if(action==null){ 
            action="join";  // to avoid null pointer exception
         }
        
        if(action.equals("join")){
        url="/home.jsp"; // maintain the browser in the same jsp page
        }
        
        
        else if(action.equals("joinc")){
            url="/contact.jsp";
        }

        else if(action.equals("contact")){
            if(userexists==null){
                url="/home.jsp";
            }
            else{

                Properties prop=new Properties();
                prop.put("mail.smtp.host","smtp.gmail.com");
                prop.put("mail.smtp.port","587");
                prop.put("mail.smtp.auth","true");
                prop.put("mail.smtp.starttls.enable","true");
                
                Authenticator auth=new Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("test050293@gmail.com","nbadassignment");
                    }                    
                };
                
                try{
                    log(userexists.getEmail()+"  "+userexists.getPassword());
                    Session sess=Session.getDefaultInstance(prop,auth);
                    Message msg=new MimeMessage(sess);
                    msg.setSubject("Contact from:"+userexists.getEmail());
                    msg.setText(request.getParameter("message"));
                    msg.setFrom(new InternetAddress("test050293@gmail.com","Contact from:"+userexists.getEmail()));
                    msg.setRecipient(Message.RecipientType.TO,new InternetAddress(request.getParameter("email")));
                    
                    Transport.send(msg);
                    
                    url="/confirmc.jsp";
                }catch(MessagingException|UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }
        }
        
        
        
        
        else if(action.equals("recommend")){
            if(userexists==null){
                url="/home.jsp";
            }
            else{
                if(request.getParameter("email").equals(request.getParameter("friend_email"))){
                    String rmsg="Recommender email cannot be the same as your email!";
                    request.setAttribute("rec_msg",rmsg);
                    url="/recommend.jsp";
                }
                else{ 
                Properties prop=new Properties();
                prop.put("mail.smtp.host","smtp.gmail.com");
                prop.put("mail.smtp.port","587");
                prop.put("mail.smtp.auth","true");
                prop.put("mail.smtp.starttls.enable","true");
                
                Authenticator auth=new Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("test050293@gmail.com","nbadassignment");
                    }                    
                };
                
                try{
                    Session sess=Session.getDefaultInstance(prop,auth);
                    Message msg=new MimeMessage(sess);
                    String message=request.getParameter("message");
                    String uuid = UUID.randomUUID().toString();
                    String recid=UUID.randomUUID().toString();
                    RecommendDB.insert(userexists.getEmail(), request.getParameter("friend_email"),recid,uuid);
                    message+="\n\nFollow the link the below to sign up:\n\n";
                   message+="http://localhost:8080/Assignment-4/RCController?recid="+recid+"&token="+uuid+"\n\n";                    
                 //   message+="https://nbad3-sskassignment1.rhcloud.com/Assignment-4/RCController?recid="+recid+"&token="+uuid+"\n\n";
                    message+="Regards,"+"\n"+"Researchers Exchange Participations Technical Team";
                    msg.setSubject("Research Exchange Participation Recommendation");
                    msg.setText(message);
                    msg.setFrom(new InternetAddress("test050293@gmail.com","Recommendation from:"+request.getParameter("email")));
                    msg.setRecipient(Message.RecipientType.TO,new InternetAddress(request.getParameter("friend_email")));
                    
                    Transport.send(msg);
                    
                    url="/confirmr.jsp";
                }catch(MessagingException|UnsupportedEncodingException e){
                    e.printStackTrace();
                }
                }
            }            
            
        }
         
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

}
