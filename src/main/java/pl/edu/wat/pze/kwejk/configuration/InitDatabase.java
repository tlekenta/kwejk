package pl.edu.wat.pze.kwejk.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.edu.wat.pze.kwejk.model.Article;
import pl.edu.wat.pze.kwejk.model.Picture;
import pl.edu.wat.pze.kwejk.model.User;
import pl.edu.wat.pze.kwejk.repository.ArticleRepository;
import pl.edu.wat.pze.kwejk.repository.PictureRepository;
import pl.edu.wat.pze.kwejk.repository.UserRepository;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class InitDatabase {

    ArticleRepository articleRepository;

    PictureRepository pictureRepository;

    UserRepository userRepository;

    @EventListener(ContextRefreshedEvent.class)
    public void initDb() {
        Picture[] pPictures = createPictures();

        Article[]  pArticles = createArticles(pPictures);

        pictureRepository.saveAll(Arrays.asList(pPictures));
        articleRepository.saveAll(Arrays.asList(pArticles));

        userRepository.saveAll(Arrays.asList(createUsers()));


        pPictures[3].setArticle(pArticles[0]);
        pPictures[4].setArticle(pArticles[1]);

        pictureRepository.saveAll(Arrays.asList(pPictures));

    }

    private User[] createUsers() {
        return new User[] {
                User.builder().username("czesiek").password("grzesiek").build()
        };
    }

    private Picture[] createPictures() {

        return new Picture[]{
                new Picture((long) 1, "1.jpg", "Obrazek pierwszy", "Opis obrazka pierwszego", null),
                new Picture((long) 2, "plakat.png", "Obrazek drugi", "Opis obrazka drugiego", null),
                new Picture((long) 3, "spd1.jpg", "Obrazek trzeci", "Opis obrazka trzeciego", null),
                new Picture((long) 4, "testo.jpg", "Obrazek czwarty", "Opis obrazka czwartego", null),
                new Picture((long) 5, "testo2.jpg", "Obrazek piąty", "Opis obrazka piątego", null)
        };
    }

    private Article[] createArticles(Picture[] pPictures) {

        return new Article[]{
                new Article((long) 1,
                        "Tytuł pierwszego artykułu",
                        "Jakiś bardzo długi tekst artykułu pierwszego. Lorem ipsum dolor sit amet nunc justo, " +
                                "eget quam nec diam eu euismod nec, imperdiet orci sem semper magna tincidunt sit amet quam purus, " +
                                "congue eu, tempor venenatis pede. Fusce iaculis leo, in elit. Donec euismod nulla ultricies ante. " +
                                "Maecenas tortor lacus nec lectus vestibulum lorem ligula, et wisi. Phasellus at neque. Suspendisse a neque tortor orci, " +
                                "sodales nulla in lacus tincidunt nulla. Phasellus urna fringilla neque placerat eget, dignissim sagittis a, dui. Morbi nibh. " +
                                "Ut sed ante eu pulvinar odio. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. " +
                                "Etiam leo at libero. Nam scelerisque in, ipsum. Nam eget est. Vivamus lacus. Aenean bibendum leo at interdum viverra. " +
                                "Mauris imperdiet sed, neque. Donec in augue. Donec magna auctor varius. In hac habitasse platea dictumst. " +
                                "Praesent est diam, venenatis arcu. In mollis, metus. Nullam fermentum sed, rutrum magna blandit malesuada. " +
                                "Ut bibendum blandit, enim arcu nec quam. Aliquam id eleifend velit. Integer posuere dui. " +
                                "Lorem ipsum primis in dictum at, bibendum eget, bibendum risus. Donec nunc. Etiam at justo at ipsum. " +
                                "Fusce facilisis eget, accumsan sit amet, felis. Pellentesque sodales quam. Praesent vitae " +
                                "sagittis lacus et erat eu libero eget magna. Praesent tincidunt sit amet augue. Fusce et ultrices posuere, " +
                                "odio. Ut libero ac diam. Aliquam feugiat lectus blandit sed, imperdiet sed, viverra venenatis lorem eget magna. " +
                                "Vestibulum dignissim vitae, dolor. Nam feugiat felis. Mauris pretium cursus. In pharetra. Morbi tellus. " +
                                "Integer lorem sit amet, diam. Etiam nibh. Ut eget tellus. Integer eget egestas consequat mollis faucibus, " +
                                "nulla ut pede. In hac habitasse platea dictumst. Proin quis justo. Vestibulum leo. Aenean id lacus. " +
                                "Integer tincidunt nec, dolor. Aliquam pharetra velit adipiscing felis ut tellus pede dictum velit, " +
                                "a leo eros, sagittis porttitor. Aenean lobortis metus. Nullam in faucibus orci diam, " +
                                "suscipit suscipit mauris. Aliquam ante ligula ut justo ac ipsum. Duis dictum. Fusce non metus " +
                                "nisl at augue. Fusce facilisis vel, viverra arcu, eget lectus est ullamcorper convallis. Donec " +
                                "vitae ante. Vivamus nibh nisl, a dolor. Duis aliquam, libero. Mauris vel ipsum dolor ullamcorper " +
                                "risus. Aliquam et magnis dis parturient montes, nascetur ridiculus mus. Fusce in aliquam convallis. " +
                                "Donec id justo nibh porta tellus sodales quam. Praesent rutrum. Pellentesque scelerisque sed, aliquet " +
                                "pulvinar, libero. Proin quis consectetuer nec, iaculis quis, vestibulum eget, dignissim erat eu libero. " +
                                "Morbi pede. Aliquam erat sit amet dignissim sagittis odio adipiscing arcu erat, nonummy ligula. " +
                                "Lorem ipsum dolor ullamcorper risus. Phasellus vitae metus wisi, eget sem. In hac habitasse platea " +
                                "dictumst. Praesent consequat. Morbi sem tincidunt vel, purus. Mauris leo. Integer aliquam at, bibendum " +
                                "risus. Morbi hendrerit. Fusce quam tempus euismod. Donec enim aliquam erat. Vivamus posuere cubilia " +
                                "Curae, Nulla porta eu, nisl. Curabitur volutpat eu, pulvinar nonummy diam lorem, pellentesque erat lacus, " +
                                "faucibus in, odio. Morbi aliquam ut, leo. Pellentesque laoreet hendrerit wisi. Suspendisse sed augue. " +
                                "Cum sociis natoque penatibus et nunc vitae ante. Vestibulum ante at eros sed ipsum ante, luctus augue. " +
                                "Aenean egestas sit amet neque tortor nibh, volutpat urna. Nunc elementum. Mauris ornare magna ipsum primis in.",
                        pPictures[3]
                ),
                new Article((long) 2,
                        "Tytuł drugiego artykułu",
                        "Litwo! Ojczyzno moja! Ty jesteś jak zdrowie. Ile cię stracił. Dziś człowieka nie znał polowania. " +
                                "On za domem urządzał wieczerzę. on zająca pochwycił. Asesor Krajczance a był to mówiąc, że przymiotów jego " +
                                "trwogi wszczęła rzecz długa, choć najwymowniejsza. Ale co dzień powszedni. Nóżek, choć zawsze służy której ramię " +
                                "z czego wybrać u Woźnego lepiej zna równie kłaść na krzesła poręczu rozpięta. A na kształt deski. Nogi miał czasu. " +
                                "Na piasku drobnym, suchym, białym na młodzież Tadeuszowi wrzasnął tuż za starszemi, a natenczas tam wódz gospodarstwa " +
                                "obmyśla wypraw w pułku gadano, jak wytnie dwa kruki jednym z Paryża a na pamiątkę, że przychodził już to mówiąc, " +
                                "że przymiotów jego postać tylko cichy smutek panów groni mają od ciemnej zieleni topoli, co Francuz wymyśli, " +
                                "to mówiąc, że nasi synowie i zabawiać lubił porównywać, a od płaczącej matki pod Napoleonem, demokrata przyjechał " +
                                "pan Rejent, na kształt śniegu, Ślad wyraźny, lecz na swym dworze. Nikt na czterech ławach cztery ich rzędy siedziało " +
                                "trzeba cenić, ten tylko cichy smutek panów dworu uprawne dobrze na sądy graniczne dla tylu, tak Suwarów w domu i trudno" +
                                " zaradzić wolał gości przeprosić i z Wereszczaką, Giedrojć z synowcem witania: dał mu słowo ciocia koło uch brzęczało" +
                                " ciągle Sędziemu tłumaczył dlaczego urządzenie pańskie przeinaczył we łzach i przysłonił chciał zamku, właśnie w pośrodku " +
                                "zamczyska którego progiem stanęła Podczaszyca dwókolna dryndulka która się tłocz i wznosi chmurę pyłu. dalej z córkami. " +
                                "Młodzież poszła do głębi. jeszcze gorzej! Teraz nie może. Widać, że nasi synowie i szukał komnaty nim odszedł, wyskoczył na.",
                        pPictures[4]
                )
        };
    }
}
