package pl.edu.wat.pze.kwejk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wat.pze.kwejk.model.ModelAttributeEnum;
import pl.edu.wat.pze.kwejk.model.Picture;
import pl.edu.wat.pze.kwejk.model.ViewEnum;
import pl.edu.wat.pze.kwejk.service.PaginationService;
import pl.edu.wat.pze.kwejk.service.PictureService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final PictureService pictureService;
    private final PaginationService paginationService;

    @Autowired
    public SearchController(PictureService pictureService, PaginationService paginationService) {
        this.pictureService = pictureService;
        this.paginationService = paginationService;
    }

    @GetMapping({"/{keyWord}", "/{keyWord}/{pageNumber}"})
    public String search(@PathVariable String keyWord, @PathVariable(required = false) Integer pageNumber, Model model) {

        pageNumber = pageNumber==null ? 1 : pageNumber;

        List<Picture> l;
        l = pictureService.findPicturesByKeyWord(pageNumber,keyWord);

        model.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.GALLERY);
        model.addAttribute(ModelAttributeEnum.ACTIVE_ENDPOINT.toString(), "/search/" + keyWord + "/");
        model.addAttribute(ModelAttributeEnum.ACTUAL_PAGE_NUMBER.toString(), pageNumber);
        if(!l.isEmpty()) {
            model.addAttribute(ModelAttributeEnum.LAST_PAGE_NUMBER.toString(), pictureService.getMaxPageForKeyWord(keyWord));
            model.addAttribute(ModelAttributeEnum.PICTURES_LIST.toString(), l);
            model.addAttribute(ModelAttributeEnum.PAGES_NUMBERS_LIST.toString(), paginationService.getPagesListForSearchResult(pageNumber, keyWord));
        } else
            System.out.println("isEmpty=true");
        return "index";
    }

    @GetMapping("")
    public String searchForm(Model model) {
        model.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.SEARCH);

        return "index";
    }
}
