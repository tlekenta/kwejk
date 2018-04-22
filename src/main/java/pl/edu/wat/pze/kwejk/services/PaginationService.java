package pl.edu.wat.pze.kwejk.services;

import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

@Service
public class PaginationService {

    public static final int MAX_PAGE = 25;
    private final int NUMBER_OF_PAGES_TO_DISPLAY = 7;

    public List<Integer> getPagesList(Integer aActivePage) {
        if(aActivePage <= NUMBER_OF_PAGES_TO_DISPLAY / 2)
            return getFirstPages(aActivePage);
        else if(aActivePage > MAX_PAGE - NUMBER_OF_PAGES_TO_DISPLAY / 2)
            return getLastPages(aActivePage);
        else
            return getMiddlePages(aActivePage);
    }

    private List<Integer> getFirstPages(Integer aActivePage) {
        return getPagesFromRange(1, NUMBER_OF_PAGES_TO_DISPLAY, aActivePage);
    }

    private List<Integer> getLastPages(Integer aActivePage) {
        return getPagesFromRange(MAX_PAGE - NUMBER_OF_PAGES_TO_DISPLAY + 1,
                MAX_PAGE,
                aActivePage
        );
    }

    private List<Integer> getMiddlePages(Integer aActivePage) {
        return getPagesFromRange(aActivePage - NUMBER_OF_PAGES_TO_DISPLAY / 2,
                aActivePage + NUMBER_OF_PAGES_TO_DISPLAY / 2,
                aActivePage
        );
    }

    private List<Integer> getPagesFromRange(Integer aFrom, Integer aTo, Integer aActivePage){
        List<Integer> pPagesList = new LinkedList<>();

        if(aActivePage > aTo || aActivePage < aFrom)
            throw new IllegalStateException(MessageFormat.format("Active page is not in selected range! Range is <{0}, {1}> and active page is {2}", aFrom, aTo, aActivePage));
        
        for(int i = aFrom; i <= aTo; i++) {
            pPagesList.add(i);
        }

        return pPagesList;
    }

}
