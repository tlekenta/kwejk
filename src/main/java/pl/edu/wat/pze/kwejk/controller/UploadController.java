package pl.edu.wat.pze.kwejk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wat.pze.kwejk.model.PicValidEnum;
import pl.edu.wat.pze.kwejk.model.Picture;
import pl.edu.wat.pze.kwejk.util.PictureValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/upload")
public class UploadController {

    private PictureValidator pictureValidator;

    public String add(@RequestParam("file") MultipartFile file,
                      @Valid @ModelAttribute("picture") Picture picture,
                      BindingResult result,
                      Model model) {
        //do zrobienia
        PicValidEnum validationResult = pictureValidator.validateImage(file);
        return null;
    }
}
