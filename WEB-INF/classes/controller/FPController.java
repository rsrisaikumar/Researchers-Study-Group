/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.User;
import data.ForgotPassDB;
import data.RecommendDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
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
 * @author saiku
 */
@WebServlet(name = "FPController", urlPatterns = {"/FPController"})
public class FPController extends HttpServlet {
    
    
    static String username="test050293@gmail.com";
    static String password="nbadassignment";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String url="/recoverpass.jsp";                                          
       request.setAttribute("token",request.getParameter("token"));
       try{
       if(ForgotPassDB.userExistsByToken(request.getParameter("token"))==0){
            request.setAttribute("fp_msg","There is no password recovery or it has expired");
            url="/forgotpass.jsp";           
       }   
       }catch(Exception e){}
       getServletContext().getRequestDispatcher(url).forward(request, response);      
    }


    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        log("i came here");
        String url="/home.jsp";
        String action=request.getParameter("action");        
        if(action==null){ 
            action="join";  // to avoid null pointer exception
         }
        
        if(action.equals("join")){
        url="/home.jsp"; // maintain the browser in the same jsp page
        }
        
       
        else if(action.equals("forgotpassword")){
            if(ForgotPassDB.getUsersName(request.getParameter("email"))==null){
                request.setAttribute("fp_msg","This user is not registered!");
                url="/forgotpass.jsp";
            }
            
            else if(ForgotPassDB.userExistsByEmail(request.getParameter("email"))==1){
                request.setAttribute("fp_msg","A password recovery email is already sent to your email(expires in 5 min");
                url="/forgotpass.jsp";
            }
            
            else{
            String name=ForgotPassDB.getUsersName(request.getParameter("email"));
            Properties prop=new Properties();
            prop.put("mail.smtp.host","smtp.gmail.com");
            prop.put("mail.smtp.port","587");
            prop.put("mail.smtp.auth","true");
            prop.put("mail.smtp.starttls.enable","true");
                
            Authenticator auth=new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username,password);
                }                    
            };
                
                try{
                    String message="Hi "+name+","+"\n";
                    message+="\t"+"No worries if you have forgotten your password. Please click on the following link to reset your password"+"\n\n";
                    String uuid = UUID.randomUUID().toString();
                    ForgotPassDB.insert(request.getParameter("email"),uuid);
                    message+="http://localhost:8080/Assignment-4/FPController?token="+uuid+"\n\n";                    
                  //  message+="https://nbad3-sskassignment1.rhcloud.com/Assignment-4/FPController?token="+uuid+"\n\n";
                    message+="Regards,"+"\n"+"Researchers Exchange Participations Technical Team";
                    Session sess=Session.getDefaultInstance(prop,auth);
                    Message msg=new MimeMessage(sess);
                    msg.setSubject("Recover your Password:Researchers Exchange Participations");
                    msg.setText(message);
                    msg.setFrom(new InternetAddress(username));
                    msg.setRecipient(Message.RecipientType.TO,new InternetAddress(request.getParameter("email")));
                    
                    Transport.send(msg);
                    request.setAttribute("confirm_msg","A password recovery email is sent to your account which will expire in 5 minutes!");
                    url="/confirmpass.jsp";
                }catch(MessagingException e){
                    e.printStackTrace();
                }
            }      
        }
        
        
        else if(action.equals("recoverpassword")){
            if(!(request.getParameter("password").equals(request.getParameter("confirm_password")))){
                request.setAttribute("rp_msg","Password and Confirm Passwords do not match");
                url="/recoverpass.jsp";                
            }
            else if(request.getParameter("token")==null){
                url="/forgotpass.jsp";
            }
            else{
                log(request.getParameter("token"));
                if(ForgotPassDB.userExistsByToken(request.getParameter("token"))==0){
                    request.setAttribute("fp_msg","The token has expired");
                    url="/forgotpass.jsp";
                }
                else{
                    int flag=ForgotPassDB.updatePassword(request.getParameter("token"),request.getParameter("password"));
                    if(flag>0)
                        request.setAttribute("confirm_msg","Your Password Reset was successful");
                    else
                        request.setAttribute("confirm_msg","Your Password Reset failed for some reason");                        
                    url="/confirmpass.jsp";
                }
            }
        }
        
        
        
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

}
