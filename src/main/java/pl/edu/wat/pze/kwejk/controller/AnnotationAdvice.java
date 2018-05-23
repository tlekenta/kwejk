package pl.edu.wat.pze.kwejk.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AnnotationAdvice {

    @ModelAttribute("authentication")
    public Authentication getAuthentication() {
        System.out.println("Działą dodawanie globalne");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }
}
