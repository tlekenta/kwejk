package pl.edu.wat.pze.kwejk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.pze.kwejk.model.Picture;
import pl.edu.wat.pze.kwejk.service.PictureService;

@RestController
@RequestMapping("/api/picture")
public class PointsController {

    private final PictureService pictureService;

    @Autowired
    public PointsController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/points/{pictureId}")
    public int getPoints(@PathVariable Long pictureId) {
        return pictureService.getPictureById(pictureId).getPoints();
    }

    @PostMapping("/points/{pictureId}")
    public int setPoints(@PathVariable Long pictureId,
                         @RequestParam(value = "action", defaultValue = "plus") String action) {
        Picture pic = pictureService.getPictureById(pictureId);
        switch (action) {
            case "plus":
                pic.setPoints(pic.getPoints() + 1);
                break;
            case "minus":
                pic.setPoints(pic.getPoints() - 1);
                break;
            default:
                //System.err.println("zły argument");
        }
        pictureService.save(pic);
        return pictureService.getPictureById(pictureId).getPoints();
    }
}