/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.web;

import cz.muni.fi.pa036.dto.RestaurantDTO;
import cz.muni.fi.pa036.facade.AccountFacade;
import cz.muni.fi.pa036.facade.RestaurantFacade;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author akaren
 */
@Controller
@RequestMapping("/loginCheck.jsp")
public class RestaurantServlet {

    @Autowired
    private RestaurantFacade myFacade;

    @Autowired
    private AccountFacade loginFacade;

    public void setMyFacade(RestaurantFacade myFacade) {
        this.myFacade = myFacade;
    }

    public void setLoginFacade(AccountFacade loginFacade) {
        this.loginFacade = loginFacade;
    }

    public RestaurantFacade getMyFacade() {
        return myFacade;
    }

    public AccountFacade getLoginFacade() {
        return loginFacade;
    }


    @RequestMapping(method = RequestMethod.POST)
    protected String doPost(HttpServletRequest request){


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

            try {
                List<RestaurantDTO> list = myFacade.topRestaurants();

                request.getSession().setAttribute("restList", list);

                int logins =  loginFacade.numberOfLogin();
                request.getSession().setAttribute("logins", logins);

                return "/outline.jsp";

            } catch (Exception e) {
                //  response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
            return "/outline.jsp";
        }
        else {
            request.setAttribute("errorMessage", "Invalid user or password");
            return "/";
            // RequestDispatcher rd = request.getRequestDispatcher("/");
            // rd.forward(request, response);

        }


    }



}
