/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import service.WelcomeService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 *
 * @author User
 */
public class WelcomeController extends SimpleFormController {
    
    private WelcomeService welcomeService;
    
    public void setWelcomeService(WelcomeService welcomeService) {
        this.welcomeService = welcomeService;
    }
    
    public WelcomeController() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(Name.class);
        setCommandName("name");
        setSuccessView("welcomeView");
        setFormView("nameView");
    }
    

    //Use onSubmit instead of doSubmitAction 
    //when you need access to the Request, Response, or BindException objects
    @Override
    protected ModelAndView onSubmit(Object command) throws Exception {
        Name name = (Name) command;
        ModelAndView mv = new ModelAndView(getSuccessView());
        mv.addObject("fullname", name.getValue());
        mv.addObject("company", name.getCompany());
        mv.addObject("email", name.getEmail());
        return mv;
    }
}
