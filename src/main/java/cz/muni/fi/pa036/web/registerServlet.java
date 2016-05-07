/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.web;

import cz.muni.fi.pa036.facade.AccountFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author akaren
 */
@WebServlet(name = "registerServlet", urlPatterns = {"/register.jsp"})
public class registerServlet extends HttpServlet {

     
    @Autowired
    private AccountFacade myFacade;
    
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
        
       String name = (String) request.getAttribute("username");
       String password = (String) request.getAttribute("password");
       Boolean employee =  Boolean.parseBoolean(request.getAttribute("employee").toString());
       
       if(myFacade.register(name, password, employee)) {
           request.getSession().setAttribute("username", name);
           RequestDispatcher rd=request.getRequestDispatcher("/outline.jsp");
           rd.forward(request, response);
       }
       
       else {
           //TODO error
       }
    }

 

}
