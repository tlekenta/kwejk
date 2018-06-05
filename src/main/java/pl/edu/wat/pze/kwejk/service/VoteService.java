package pl.edu.wat.pze.kwejk.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.pze.kwejk.model.Picture;
import pl.edu.wat.pze.kwejk.model.User;
import pl.edu.wat.pze.kwejk.model.Vote;
import pl.edu.wat.pze.kwejk.model.VoteType;
import pl.edu.wat.pze.kwejk.repository.VoteRepository;

import static pl.edu.wat.pze.kwejk.model.VoteType.PLUS;

@Service
@AllArgsConstructor
public class VoteService {

    private enum PointsChange {PLUS_2, PLUS_1, MINUS_1, MINUS_2}
    private PictureService pictureService;
    private VoteRepository voteRepository;

    /**
     * Jeżeli użytkownik nie jeszcze nie głosował na dany obrazek - tworzy nowy głos i zapisuje go do bazy.
     * Jeżeli już głosował i typ głosu jest taki sam - usuwa głos
     * Jeżeli już głosował i typ głosu jest przeciwny - zmiania typ głosu i zapisuje go do bazy
     * W każdym przypadku aktualizuje liczbę punktów obrazku, aby nie trzeba było jej wyliczać za każdym razem
     * @param aUser użytkownik oddający głos
     * @param aPictureId identyfikator obrazka na który oddwany jest głos
     * @param aVoteType typ oddawanego głosu
     */
    public void vote(User aUser, Long aPictureId, VoteType aVoteType){
        Picture pPicture = pictureService.getPictureById(aPictureId);
        Vote pVote = voteRepository.getVoteByUserAndPicture(aUser, pPicture);

        PointsChange pAction;
        if(pVote == null) {
            pVote = new Vote(null, pPicture, aUser, aVoteType);
            voteRepository.save(pVote);
            if(aVoteType == PLUS) {
                pAction = PointsChange.PLUS_1;
            } else {
                pAction = PointsChange.MINUS_1;
            }
        } else if(aVoteType == pVote.getVoteType()) {
            //usuń głos
            voteRepository.delete(pVote);
            if(aVoteType == PLUS) {
                pAction = PointsChange.MINUS_1;
            } else {
                pAction = PointsChange.PLUS_1;
            }
        } else {
            //zmień typ głosu
            if(pVote.getVoteType() == PLUS) {
                pVote.setVoteType(VoteType.MINUS);
            } else {
                pVote.setVoteType(PLUS);
            }
            if(aVoteType == PLUS) {
                pAction = PointsChange.PLUS_2;
            } else {
                pAction = PointsChange.MINUS_2;
            }
        }

        updatePicturePoints(pPicture, pAction);
    }

    private void updatePicturePoints(Picture aPicture, PointsChange aPointsChange) {
        switch(aPointsChange) {
            case PLUS_2:
                aPicture.setPoints(aPicture.getPoints() + 2);
                break;
            case PLUS_1:
                aPicture.setPoints(aPicture.getPoints() + 1);
                break;
            case MINUS_1:
                aPicture.setPoints(aPicture.getPoints() - 1);
                break;
            case MINUS_2:
                aPicture.setPoints(aPicture.getPoints() - 2);
                break;
        }
        pictureService.save(aPicture);
    }

}
