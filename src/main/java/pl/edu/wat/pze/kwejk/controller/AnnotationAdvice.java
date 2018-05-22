package pl.edu.wat.pze.kwejk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class AnnotationAdvice {

    @ModelAttribute("currentUser")
    public String getCurrentUser() {

        System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userDetails.getUsername();
    }
}
