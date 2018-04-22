package pl.edu.wat.pze.kwejk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.edu.wat.pze.kwejk.model.ViewEnum;
import pl.edu.wat.pze.kwejk.services.PaginationService;
import pl.edu.wat.pze.kwejk.services.PictureService;

@Controller
public class GalleryController {

    private PictureService pictureService;
    private PaginationService paginationService;

    public GalleryController(PictureService pictureService, PaginationService paginationService) {
        this.pictureService = pictureService;
        this.paginationService = paginationService;
    }

    @GetMapping(value = {"/", "/{aPageNumber}"})
    public String getGallery(@PathVariable(required = false) Integer aPageNumber, Model aModel) {
        if(aPageNumber == null)
            aPageNumber = 1;
        aModel.addAttribute("activeView", ViewEnum.GALLERY);
        aModel.addAttribute("actualPageNumber", aPageNumber);
        aModel.addAttribute("lastPageNumber", PaginationService.MAX_PAGE);
        aModel.addAttribute("picturesList", pictureService.getPicturesForPage(aPageNumber));
        aModel.addAttribute("pagesNumbersList", paginationService.getPagesList(aPageNumber));
        return "index.html";
    }

}
