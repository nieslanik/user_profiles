/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.web;

import cz.muni.fi.pa036.dto.ReviewDTO;
import cz.muni.fi.pa036.facade.RestaurantFacade;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author akaren
 */
@Controller
@RequestMapping("/reviewList.jsp")
public class ReviewServlet {
    
    @Autowired
    private RestaurantFacade myFacade;

    public RestaurantFacade getMyFacade() {
        return myFacade;
    }

    public void setMyFacade(RestaurantFacade myFacade) {
        this.myFacade = myFacade;
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     */
    protected void doGet(HttpServletRequest request) {
       String name = (String) request.getAttribute("name");
       String score = (String) request.getAttribute("score"); 
       String id = (String) request.getAttribute("id"); 
       List<ReviewDTO> listReview = myFacade.getReviews(name);
       request.getSession().setAttribute("reviewList", listReview);    
       request.getSession().setAttribute("averageScore", myFacade.averageScore(name));
       
      
    }
 
}
