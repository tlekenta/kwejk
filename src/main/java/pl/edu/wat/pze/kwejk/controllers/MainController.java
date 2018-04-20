package pl.edu.wat.pze.kwejk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.edu.wat.pze.kwejk.services.PictureService;

@Controller
public class MainController {

    private PictureService pictureService;

    public MainController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{aPageNumber}")
    public String gallery(@PathVariable int aPageNumber, Model aModel) {
        aModel.addAttribute("name", aPageNumber);
        pictureService.updatePictures(aModel, aPageNumber);
        return "index.html";
    }

}
