/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.myweb1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import pa036.sprava_uziv_profilov.nosql.entities.Restaurant;
import pa036.sprava_uziv_profilov.nosql.facade.AccountFacade;
import pa036.sprava_uziv_profilov.nosql.facade.RestaurantFacade;
import pa036.sprava_uziv_profilov.nosql.facade.RestaurantFacadeImpl;

/**
 *
 * @author akaren
 */
@WebServlet(urlPatterns = {"/loginCheck.jsp"})
public class RestaurantServlet extends HttpServlet {
    
    
    @Autowired
    private RestaurantFacade myFacade;
    
    @Autowired
    private AccountFacade loginFacade;
  
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       RequestDispatcher rd=request.getRequestDispatcher("/outline.jsp");
           rd.forward(request, response);
    }

       @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          
       String name = request.getAttribute("username").toString();
       String password =request.getAttribute("password").toString();
    
     /*  
        response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<h1>Text</h1><pre>generated directly from servlet code");
            out.println("serverInfo=" + getServletContext().getServerInfo());
            out.println("parameters: "+ name + " " + password);
        */ 
       
      if(loginFacade.login(name, password)) {
           request.getSession().setAttribute("username", name);
           RequestDispatcher rd=request.getRequestDispatcher("/outline.jsp");
           rd.forward(request, response);
       }
       
       else {
           //TODO error
       }     
     
            
       /*
        try {
           List<Restaurant> list = myFacade.topRestaurants();
           request.getSession().setAttribute("restList", list);
           RequestDispatcher rd=request.getRequestDispatcher("/outline.jsp");
           rd.forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
         */      
    }

 
   
}
