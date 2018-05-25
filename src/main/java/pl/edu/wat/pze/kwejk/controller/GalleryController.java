package pl.edu.wat.pze.kwejk.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.edu.wat.pze.kwejk.model.ModelAttributeEnum;
import pl.edu.wat.pze.kwejk.model.ViewEnum;
import pl.edu.wat.pze.kwejk.service.PaginationService;
import pl.edu.wat.pze.kwejk.service.PictureService;

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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);
        if(aPageNumber == null)
            aPageNumber = 1;

        aModel.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.GALLERY);
        aModel.addAttribute(ModelAttributeEnum.ACTUAL_PAGE_NUMBER.toString(), aPageNumber);
        aModel.addAttribute(ModelAttributeEnum.LAST_PAGE_NUMBER.toString(), pictureService.getMaxPage());
        aModel.addAttribute(ModelAttributeEnum.PICTURES_LIST.toString(), pictureService.getPicturesForPage(aPageNumber));
        aModel.addAttribute(ModelAttributeEnum.PAGES_NUMBERS_LIST.toString(), paginationService.getPagesList(aPageNumber));
        return "index.html";
    }

}
