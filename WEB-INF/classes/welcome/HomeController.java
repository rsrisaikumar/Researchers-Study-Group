/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package welcome;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SriSaiKumar
 */
@WebServlet(name = "HomeController", urlPatterns = {"/HomeController"})
public class HomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String hostname=request.getRemoteAddr();
         int pn=request.getRemotePort();
         Integer pp=pn;
         String portnumber=pp.toString();
         Cookie icookie=new Cookie("HostName",hostname);
         icookie.setMaxAge(6000);
         icookie.setPath("/");
         response.addCookie(icookie);
         Cookie pcookie=new Cookie("PortNumber",portnumber);
         pcookie.setMaxAge(6000);
         pcookie.setPath("/");
         response.addCookie(pcookie);
/*        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            out.println("<%@ include file=\'header.jsp\'%>");
            out.println("<section id=\'home_page\'>");
            out.println("<img src=\'images/home_image.png\' class=\'img-responsive center-block\' alt=\'Responsive image\' />");
            out.println("</section>");            
            out.println("<%@ include file=\'footer.jsp\' %>");
        } finally {
            out.close();
        }
         */
    response.sendRedirect("home.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
