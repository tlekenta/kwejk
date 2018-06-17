package pl.edu.wat.pze.kwejk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wat.pze.kwejk.model.ModelAttributeEnum;
import pl.edu.wat.pze.kwejk.model.Picture;
import pl.edu.wat.pze.kwejk.model.ViewEnum;
import pl.edu.wat.pze.kwejk.service.PaginationService;
import pl.edu.wat.pze.kwejk.service.PictureService;

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

//    @GetMapping({"/{keyWord}", "/{keyWord}/{pageNumber}"})
//    public String search(@PathVariable String keyWord, @PathVariable(required = false) Integer pageNumber, Model model) {
//
//        pageNumber = pageNumber == null ? 1 : pageNumber;
//
//        model.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.SEARCH);
//        List<Picture> result = pictureService.findPicturesByKeyWord(pageNumber, keyWord);
//
//        if (result.isEmpty()) {
//            model.addAttribute(ModelAttributeEnum.HAS_PICTURES.toString(), false);
//        } else {
//            model.addAttribute(ModelAttributeEnum.HAS_PICTURES.toString(), true);
//            model.addAttribute(ModelAttributeEnum.ACTIVE_ENDPOINT.toString(), "/search/" + keyWord + "/");
//            model.addAttribute(ModelAttributeEnum.ACTUAL_PAGE_NUMBER.toString(), pageNumber);
//            model.addAttribute(ModelAttributeEnum.LAST_PAGE_NUMBER.toString(), pictureService.getMaxPageForKeyWord(keyWord));
//            model.addAttribute(ModelAttributeEnum.PICTURES_LIST.toString(), result);
//            model.addAttribute(ModelAttributeEnum.PAGES_NUMBERS_LIST.toString(), paginationService.getPagesListForSearchResult(pageNumber, keyWord));
//        }
//
//        return "index";
//    }

    @GetMapping("/result")
    public String search(@RequestParam String keyWord, @RequestParam Integer pageNumber, Model model) {
        pageNumber = pageNumber == null ? 1 : pageNumber;
        model.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.SEARCH);
        List<Picture> result = pictureService.findPicturesByKeyWord(pageNumber, keyWord);

        if (result.isEmpty()) {
            model.addAttribute(ModelAttributeEnum.HAS_PICTURES.toString(), false);
            model.addAttribute(ModelAttributeEnum.NO_RESULT.toString(), "Brak wyniku dla frazy '" + keyWord + "'");
        } else {
            model.addAttribute(ModelAttributeEnum.KEY_WORD.toString(), keyWord);
            model.addAttribute(ModelAttributeEnum.HAS_PICTURES.toString(), true);
            model.addAttribute(ModelAttributeEnum.ACTIVE_ENDPOINT.toString(), "/search/result?keyWord=" + keyWord + "&pageNumber=");
            model.addAttribute(ModelAttributeEnum.ACTUAL_PAGE_NUMBER.toString(), pageNumber);
            model.addAttribute(ModelAttributeEnum.LAST_PAGE_NUMBER.toString(), pictureService.getMaxPageForKeyWord(keyWord));
            model.addAttribute(ModelAttributeEnum.PICTURES_LIST.toString(), result);
            model.addAttribute(ModelAttributeEnum.PAGES_NUMBERS_LIST.toString(), paginationService.getPagesListForSearchResult(pageNumber, keyWord));
        }
        return "index";
    }

    @GetMapping("")
    public String searchForm(Model model) {
        model.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.SEARCH);
        model.addAttribute(ModelAttributeEnum.HAS_PICTURES.toString(), false);
        return "index";
    }
}
