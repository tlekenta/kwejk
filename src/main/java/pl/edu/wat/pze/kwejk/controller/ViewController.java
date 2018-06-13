
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
import pl.edu.wat.pze.kwejk.service.PictureService;

@Controller
@RequestMapping("/view")
public class ViewController {

    private final PictureService pictureService;

    @Autowired
    public ViewController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{pictureId}")
    public String getArticle(@PathVariable Long pictureId, Model aModel) {

        Picture p = pictureService.getPictureById(pictureId);
        System.out.println(p.getPath());
        aModel.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.VIEW);
        aModel.addAttribute(ModelAttributeEnum.CURRENT_PICTURE.toString(), pictureService.getPictureById(pictureId));

        return "index";
    }

}