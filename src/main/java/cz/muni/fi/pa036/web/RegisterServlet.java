/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.web;

import cz.muni.fi.pa036.facade.AccountFacade;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author akaren
 */

@Controller
@RequestMapping("/register.jsp")
public class RegisterServlet  {
     
    private  AccountFacade myFacade;

    @Autowired
    public RegisterServlet(AccountFacade myFacade) {
        this.myFacade = myFacade;
    }
    
    public AccountFacade getMyFacade() {
        return myFacade;
    }

    public void setMyFacade(AccountFacade myFacade) {
        this.myFacade = myFacade;
    }  
    
  
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     */
    @RequestMapping(method = RequestMethod.POST, params={"username"})
    protected String doPost(HttpServletRequest request) {
        
        try{ 
            String name = (String) request.getAttribute("username"); 
            String password = (String) request.getAttribute("password");
            Boolean employee =  Boolean.parseBoolean(request.getAttribute("employee").toString());

            if(myFacade.register(name, password, employee)) {
                request.getSession().setAttribute("username", name);
                return "/outline.jsp";
            }
            else {
               request.setAttribute("errorMessage", "Invalid username or password");
               return "/";
            }
        }
        catch(Exception e){   
        }
        return "";
    }

 

}
