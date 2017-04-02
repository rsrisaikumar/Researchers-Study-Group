/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.Answer;
import business.Study;
import data.AnswerDB;
import data.StudyDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author saiku
 */
@WebServlet(name = "ShareStudyController", urlPatterns = {"/ShareStudyController"})
public class ShareStudyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action=request.getParameter("action");
        String studycode=request.getParameter("studyid");
        String url="/home.jsp";
        if(action.equals("active")){
                        ArrayList<Answer> getAnswer=new ArrayList<>();
            getAnswer=AnswerDB.getAllAnswersByStudyCode(studycode);
            Study study=StudyDB.getStudy(studycode);
            HashMap answers=new HashMap();
            for(int i=0;i<study.getAnswerChoice().size();i++){
                answers.put(study.getAnswerChoice().get(i),0);
            }
            for(int i=0;i<getAnswer.size();i++){
                int k=(Integer)answers.get(getAnswer.get(i).getChoice());
                answers.put(getAnswer.get(i).getChoice(),k+1);
            }
            request.setAttribute("ans",getAnswer);
            request.setAttribute("AllAnswers",answers);
            request.setAttribute("StudyStat",study);
            log(study.getQuestion());
            if(study!=null)
                url="/answerstats.jsp";
            if(getAnswer!=null)
                    request.setAttribute("isAnswer","1");
        }
               getServletContext().getRequestDispatcher(url).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
