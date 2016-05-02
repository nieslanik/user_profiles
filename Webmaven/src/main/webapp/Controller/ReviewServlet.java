/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author akaren
 */
@WebServlet(urlPatterns = {"/reviewList"})
public class ReviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showReviews(request, response);
        response.sendRedirect("reviewList"); 
    }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //aby fungovala čestina z formuláře
        request.setCharacterEncoding("utf-8");
        response.sendRedirect("reviewList"); 
        //akce podle přípony v URL
        String action = request.getPathInfo();
        switch (action) {
            case "/add":
                //načtení POST parametrů z formuláře
                String name = request.getParameter("name");
                String score = request.getParameter("score");
                
                
                if (name == null) {
                    request.setAttribute("chyba", "Je nutné vyplnit jméno!");
                    showReviews(request, response);
                    return;
                }
                //zpracování dat - vytvoření záznamu v databázi
                try {
                 /*   
                    Review rev = new Review( name, score);
                    getMediumManager().createMedium(rev); 
                 */  
                    return;
                } catch (Exception e) {
                   
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
            case "/delete":
                 
                try {
                    /*
                    Long id = Long.valueOf(request.getParameter("id"));
                    getMediumManager().removeReview(getMediumManager().findReviewById(id));
                    */
                    return;
                } catch (Exception e) {
                    
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
           
            default:
                
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown action " + action);
        }
    }

    private void showReviews(HttpServletRequest request, HttpServletResponse response) throws IOException {
          try {
              String name =  request.getParameter("name");
       //    request.setAttribute("reviewList", getReviews(name));
          
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }

    
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
