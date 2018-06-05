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

    @PostMapping("/points")
    public int setPoints(@RequestParam(value = "action") String action) {
        String[] pAttributes = action.split("_");
        Long pPictureId = new Long(pAttributes[1]);
        Picture pPicture = pictureService.getPictureById(pPictureId);
        switch(pAttributes[0]) {
            case "plus":
                pPicture.setPoints(pPicture.getPoints() + 1);
                break;
            case "minus":
                pPicture.setPoints(pPicture.getPoints() - 1);
                break;
        }

        pictureService.save(pPicture);

        return pPicture.getPoints();

//        Picture pic = pictureService.getPictureById(pictureId);
//        switch (action) {
//            case "plus":
//                pic.setPoints(pic.getPoints() + 1);
//                break;
//            case "minus":
//                pic.setPoints(pic.getPoints() - 1);
//                break;
//            default:
//                //System.err.println("zły argument");
//        }
//        pictureService.save(pic);
//        return pictureService.getPictureById(pictureId).getPoints();
    }
}