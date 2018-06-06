package pl.edu.wat.pze.kwejk.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.pze.kwejk.service.PictureService;


@RestController
@RequestMapping("/api/picture")
@AllArgsConstructor
public class CommentsController {

    private PictureService pictureService;

    @PostMapping("/comments/{aPictureId}")
    public void addNewComment(@PathVariable Long aPictureId, @RequestParam(value = "text") String aCommentText) {
        pictureService.addCommentToPicture(aPictureId, aCommentText);
    }

}
