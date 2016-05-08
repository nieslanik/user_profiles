package cz.muni.fi.pa036.web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import cz.muni.fi.pa036.facade.RestaurantFacade;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author akaren
 */
@Controller
@RequestMapping("/addReview.jsp")
public class addReviewServlet{

    @Autowired
    private RestaurantFacade myFacade;

    public RestaurantFacade getMyFacade() {
        return myFacade;
    }

    public void setMyFacade(RestaurantFacade myFacade) {
        this.myFacade = myFacade;
    }

    /**
     * Delete review
     * @param request
     * @param reviewId
     * @param restId
     * @return 
     */
     @RequestMapping(value = "delete/{reviewId}/{restId}", method = RequestMethod.GET)
      public String delete(HttpServletRequest request, @PathVariable String reviewId,@PathVariable String restId ) {
            myFacade.removeReview(reviewId, restId);
             request.setAttribute("chyba", "Cannot delete review.");
            return "/reviewList.jsp";
      }

      /**
     * Add review
     * @param request
     * @param restId
     * @return 
     */
     @RequestMapping(value = "addReview.jsp/add/{restId}", method = RequestMethod.POST)
      public String add(HttpServletRequest request, @PathVariable String restId) {
           String review = (String) request.getAttribute("review"); 
            int score = Integer.parseInt(request.getAttribute("score").toString()); 
            String id = (String) request.getSession().getAttribute("username");
            if(!myFacade.addReview(review, score, restId, id)) {
                 request.setAttribute("chyba", "Cannot add new review.");
            }     
            return "/reviewList.jsp";
      }
    }