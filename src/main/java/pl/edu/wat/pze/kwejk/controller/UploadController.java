package pl.edu.wat.pze.kwejk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wat.pze.kwejk.model.ModelAttributeEnum;
import pl.edu.wat.pze.kwejk.model.PicValidEnum;
import pl.edu.wat.pze.kwejk.model.Picture;
import pl.edu.wat.pze.kwejk.model.ViewEnum;
import pl.edu.wat.pze.kwejk.service.PictureService;
import pl.edu.wat.pze.kwejk.util.PictureValidator;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Date;

@Controller
@RequestMapping("/upload")
public class UploadController {

    private final PictureValidator pictureValidator;
    private final PictureService pictureService;
    private final String PATH = "src/main/resources/static/img/";

    @Autowired
    public UploadController(PictureValidator pictureValidator, PictureService pictureService) {
        this.pictureValidator = pictureValidator;
        this.pictureService = pictureService;
    }

    @PostMapping("")
    public String add(@RequestParam("file") MultipartFile file,
                      @Valid @ModelAttribute("picture") Picture picture,
                      BindingResult bindingResult,
                      Model model) {
        model.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.UPLOAD);
        if (bindingResult.hasErrors())
            return "index";
        PicValidEnum validateResult = pictureValidator.validateImageFile(file);
        if (validateResult == PicValidEnum.OK) {
            picture = preparePicture(picture, file);
            pictureService.save(picture);
            model.addAttribute("uploadedImagePath", picture.getPath());
        }
        model.addAttribute("resultMessage", getResultMessage(validateResult));

        return "index";
    }

    private String getResultMessage(PicValidEnum validateResult) {
        switch (validateResult) {
            case INCORRECT_FILE_TYPE:
                return "Niepoprawny plik. Obłsugiwane typy plików to .jpg .png .gif";
            case INCORRECT_WEIGHT:
                return "Plik waży za dużo, opanuj się";
            case INCORRECT_RESOLUTION:
                return "Za duży ten obrazek, opanuj się";
            case IOError:
                return "Coś się zepsuło, spróbuj jeszcze raz";
            case OK:
                return "Plik poprawnie dodany";
            default:
                return "";
        }
    }

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.UPLOAD);
        model.addAttribute("picture", new Picture());
        return "index";
    }

    private Picture preparePicture(Picture picture, MultipartFile file) {
        String fileName = String.valueOf(picture.hashCode()) + file.getContentType().replace("/", ".");
        String imagePath = PATH + fileName;
        File image = new File(imagePath);

        try {
            Files.write(image.toPath(), file.getBytes(), StandardOpenOption.CREATE);
            System.out.println("image name:" + image.getName());
        } catch (IOException e) {
            e.getMessage();
        }
        picture.setPath(fileName);
        picture.setDate(new Date());
        picture.setPoints(0);
        System.out.printf("picture:" + picture);
        return picture;
    }
}
