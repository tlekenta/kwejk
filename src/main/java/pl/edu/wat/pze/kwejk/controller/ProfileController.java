package pl.edu.wat.pze.kwejk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wat.pze.kwejk.model.ModelAttributeEnum;
import pl.edu.wat.pze.kwejk.model.ViewEnum;
import pl.edu.wat.pze.kwejk.service.PaginationService;
import pl.edu.wat.pze.kwejk.service.PictureService;
import pl.edu.wat.pze.kwejk.service.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final PictureService pictureService;
    private final PaginationService paginationService;
    private final UserService userService;

    @Autowired
    public ProfileController(PictureService pictureService, PaginationService paginationService, UserService userService) {
        this.pictureService = pictureService;
        this.paginationService = paginationService;
        this.userService = userService;
    }

    @GetMapping(value = {"/{userName}", "/profile/{userName}/{aPageNumber}"})
    public String getProfileGallery(@PathVariable String userName,
                                    @PathVariable(required = false) Integer aPageNumber,
                                    Model aModel) {
        if (aPageNumber == null)
            aPageNumber = 1;

        aModel.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.PROFILE);
        aModel.addAttribute(ModelAttributeEnum.ACTIVE_ENDPOINT.toString(), "/profile/" + userName + "/");
        aModel.addAttribute(ModelAttributeEnum.HAS_PICTURES.toString(), false);
        if(userService.hasPictures(userName)) {
            aModel.addAttribute(ModelAttributeEnum.HAS_PICTURES.toString(), true);
            aModel.addAttribute(ModelAttributeEnum.ACTUAL_PAGE_NUMBER.toString(), aPageNumber);
            aModel.addAttribute(ModelAttributeEnum.LAST_PAGE_NUMBER.toString(), pictureService.getMaxPage(userName));
            aModel.addAttribute(ModelAttributeEnum.PICTURES_LIST.toString(), pictureService.getPicturesForPage(aPageNumber, userName));
            aModel.addAttribute(ModelAttributeEnum.PAGES_NUMBERS_LIST.toString(), paginationService.getPagesList(aPageNumber, userName));
        }
        return "index";
    }
}
