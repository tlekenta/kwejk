package pl.edu.wat.pze.kwejk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wat.pze.kwejk.model.ModelAttributeEnum;
import pl.edu.wat.pze.kwejk.model.ViewEnum;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String loginView(Model aModel) {
        aModel.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.LOGIN);
        return "index.html";
    }

    @GetMapping("/logout")
    public String logoutView(Model aModel) {
        aModel.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.LOGOUT);
        return "index.html";
    }

}
