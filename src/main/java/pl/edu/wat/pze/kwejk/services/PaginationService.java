package pl.edu.wat.pze.kwejk.services;

import org.springframework.stereotype.Service;
import pl.edu.wat.pze.kwejk.model.Page;

import java.util.LinkedList;
import java.util.List;

@Service
public class PaginationService {

    public List<Page> getPagesList(Integer aAcitivePage) {
        List<Page> pPagesList = new LinkedList<>();

        for(int i = 1; i <= 10; i++) {
            boolean isActive = aAcitivePage == i;
            pPagesList.add(new Page(i, isActive));
        }

        return pPagesList;
    }

}
