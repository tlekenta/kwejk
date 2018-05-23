package pl.edu.wat.pze.kwejk.controller;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.pze.kwejk.model.ModelAttributeEnum;
import pl.edu.wat.pze.kwejk.model.User;
import pl.edu.wat.pze.kwejk.model.ViewEnum;
import pl.edu.wat.pze.kwejk.service.UserService;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginView(Model aModel, @RequestParam(value = "fail", required = false, defaultValue = "false") Boolean fail) {
        aModel.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.LOGIN);
        aModel.addAttribute("fail", fail);
        return "index.html";
    }

    @GetMapping("/register")
    public String registerView(Model aModel) {
        aModel.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.REGISTER);
        return "index.html";
    }

    @PostMapping("/register")
    @ResponseBody
    public User registerUser(@RequestBody User user) {
        user = userService.create(user);
        System.out.println(user);

        return user;
    }

    @GetMapping("/user")
    @ResponseBody
    public Authentication userDetails() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/user/exist")
    @ResponseBody
    public String isUserExist(@RequestParam(name = "username") String username) {
        JsonObject object = new JsonObject();
        object.addProperty("username", username);
        object.addProperty("exist", userService.isUsernameExist(username));
        return object.toString();
    }
}
