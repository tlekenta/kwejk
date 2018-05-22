package pl.edu.wat.pze.kwejk.controller;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.wat.pze.kwejk.model.ModelAttributeEnum;
import pl.edu.wat.pze.kwejk.model.ViewEnum;
import pl.edu.wat.pze.kwejk.service.UserService;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginView(Model aModel) {
        aModel.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.LOGIN);
        return "index.html";
    }

    @GetMapping("/register")
    public String registerView(Model aModel) {
        aModel.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.REGISTER);
        return "index.html";
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
