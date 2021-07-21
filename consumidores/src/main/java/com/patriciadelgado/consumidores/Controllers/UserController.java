package com.patriciadelgado.consumidores.Controllers;

import java.util.Calendar;
import java.util.Date;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.patriciadelgado.consumidores.Models.Subs;
import com.patriciadelgado.consumidores.Models.User;
import com.patriciadelgado.consumidores.Services.SubsService;
import com.patriciadelgado.consumidores.Services.UserService;
import com.patriciadelgado.consumidores.Validator.UserValidator;

import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;
    private final SubsService subsService;

    
    public UserController(UserService userService, UserValidator userValidator, SubsService subsService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.subsService = subsService;
    }

    @GetMapping("/")
    public String registerForm(
     @ModelAttribute("user") User user) {
        return "registroLog.jsp";
    }

    @PostMapping("/")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "registroLog.jsp";
        }
        if (userService.emailExist(user.getEmail())) {
            FieldError error = new FieldError("email", "email",
                    "El email " + user.getEmail() + " ya se encuentra registrado");
            result.addError(error);
            return "registroLog.jsp";
        }
        if(userService.count() > 0) {
            user.setUserRol(2);
            Subs suscripcion = subsService.findSus("Basic");
            Calendar c= Calendar.getInstance();
            c.add(Calendar.DATE, 30);
            Date dia=c.getTime();
            user.setDate(dia);
            user.setSubss(suscripcion);
            userService.registerUser(user);
            session.setAttribute("userId", user.getId());
            return "redirect:/users/"+user.getId();
        }
            Subs suscripcion = new Subs();
            suscripcion.setName("Basic");
            suscripcion.setPrice(10.0);
            suscripcion.setEstado(true);
            subsService.createSub(suscripcion);
            user.setUserRol(1);
            userService.registerUser(user);
            session.setAttribute("userId", user.getId());
            return "redirect:/packages";
    }
    
        

  @PostMapping("/login")
    public String loginUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password, HttpSession session) {
        boolean isAuthenticated = userService.authenticateUser(email, password);
        if (isAuthenticated) {
            User users = userService.findByEmail(email);
            session.setAttribute("userId", users.getId());
            if (users.getUserRol() == 1) {
                return "redirect:/packages";

            } else {
                return "redirect:/users/" + users.getId();
            }
           
        } else {
            session.setAttribute("error", "Invalid Credentials. Please thy again.");
            return "redirect:/";
        }
    }
   
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";

    }
 }

    
    

