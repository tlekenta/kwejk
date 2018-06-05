package pl.edu.wat.pze.kwejk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.pze.kwejk.auth.UserPrincipal;
import pl.edu.wat.pze.kwejk.model.User;
import pl.edu.wat.pze.kwejk.model.VoteType;
import pl.edu.wat.pze.kwejk.service.PictureService;
import pl.edu.wat.pze.kwejk.service.UserService;
import pl.edu.wat.pze.kwejk.service.VoteService;

@RestController
@RequestMapping("/api/picture")
public class PointsController {

    private final PictureService pictureService;
    private final VoteService voteService;
    private final UserService userService;

    @Autowired
    public PointsController(PictureService pictureService, VoteService voteService, UserService userService) {
        this.pictureService = pictureService;
        this.voteService = voteService;
        this.userService = userService;
    }


    @GetMapping("/points/{pictureId}")
    public int getPoints(@PathVariable Long pictureId) {
        return pictureService.getPictureById(pictureId).getPoints();
    }

    @PostMapping("/points")
    public void setPoints(@RequestParam(value = "action") String action) {
        String[] pAttributes = action.split("_");
        VoteType pVoteType = VoteType.valueOf(pAttributes[0].toUpperCase());
        Long pPictureId = new Long(pAttributes[1]);
        User pUser = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        voteService.vote(pUser, pPictureId, pVoteType);
    }
}