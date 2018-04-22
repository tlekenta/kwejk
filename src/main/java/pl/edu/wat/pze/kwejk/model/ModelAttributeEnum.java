package pl.edu.wat.pze.kwejk.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ModelAttributeEnum {
    /*COMMON*/
    ACTIVE_VIEW("activeView"),

    /*PAGINATION*/
    ACTUAL_PAGE_NUMBER("actualPageNumber"),
    LAST_PAGE_NUMBER("lastPageNumber"),
    PAGES_NUMBERS_LIST("pagesNumbersList"),

    /*GALLERY*/
    PICTURES_LIST("picturesList"),

    /*ARTICLE*/
    ACTUAL_ARTICLE("actualArticle"),

    /*PICTURE*/
    ;


    private final String attributeName;

    @Override
    public String toString() {
        return attributeName;
    }

}
