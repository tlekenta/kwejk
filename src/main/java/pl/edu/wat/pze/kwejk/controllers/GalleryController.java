package pl.edu.wat.pze.kwejk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.edu.wat.pze.kwejk.services.PictureService;

@Controller
public class GalleryController {

    private PictureService pictureService;

    public GalleryController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping(value = {"/", "/{aPageNumber}"})
    public String getGallery(@PathVariable(required = false) Integer aPageNumber, Model aModel) {
        if(aPageNumber == null)
            aPageNumber = 1;
        aModel.addAttribute("name", aPageNumber);
        aModel.addAttribute("picturesList", pictureService.getPicturesForPage(aPageNumber));
        return "index.html";
    }

}
