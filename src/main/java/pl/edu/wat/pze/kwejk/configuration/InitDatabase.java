package pl.edu.wat.pze.kwejk.configuration;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.edu.wat.pze.kwejk.model.Comment;
import pl.edu.wat.pze.kwejk.model.Picture;
import pl.edu.wat.pze.kwejk.model.User;
import pl.edu.wat.pze.kwejk.repository.CommentRepository;
import pl.edu.wat.pze.kwejk.repository.PictureRepository;
import pl.edu.wat.pze.kwejk.repository.UserRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@Component
public class InitDatabase {

    private final PictureRepository pictureRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private User admin;

    public InitDatabase(PictureRepository pictureRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void initDb() {
        admin = new User("admin", "admin");
        userRepository.save(admin);
        Comment[] pComments = createComments();
        commentRepository.saveAll(Arrays.asList(pComments));
        Picture[] pPictures = createPictures(pComments);
        pictureRepository.saveAll(Arrays.asList(pPictures));
    }

    private Comment[] createComments() {
        return new Comment[] {
                new Comment(
                        null,
                        admin,
                        "komentarz admina do obrazka 2",
                        LocalDate.of(2018, 5, 30)
                ),
                new Comment(
                        null,
                        admin,
                        "drugi komentarz admina do obrazka 2",
                        LocalDate.of(2018, 5, 30)
                ),
                new Comment(
                        null,
                        admin,
                        "komentarz admina do obrazka 3",
                        LocalDate.of(2018, 5, 30)
                ),
                new Comment(
                        null,
                        admin,
                        "komentarz admina do obrazka 4",
                        LocalDate.of(2018, 5, 30)
                )
        };
    }

    private Picture[] createPictures(Comment[] aComments) {

        return new Picture[]{
                new Picture((long) 1, "1.jpg", "Obrazek pierwszy", "Opis obrazka pierwszego", 1, new Date(), admin, Collections.emptyList(),
                        "oo " +
                        "jaki dlugi" +
                        " artykul, noniemoge"),
                new Picture((long) 2, "plakat.png", "Obrazek drugi", "Opis obrazka drugiego", 2, new Date(), admin, Arrays.asList(aComments[0], aComments[1]),
                null),
                new Picture((long) 3, "spd1.jpg", "Obrazek trzeci", "Opis obrazka trzeciego", 3, new Date(), admin, Collections.singletonList(aComments[2]),
                        "Jest to jedna z najaktywniejszych substancji psychodelicznych o właściwościach halucynogennych – 100 razy bardziej czynna biologicznie niż psylocybina i 4000 razy bardziej niż meskalina. Po raz pierwszy LSD zostało zsyntetyzowane w 1938 r. przez szwajcarskiego chemika Alberta Hofmanna w laboratoriach firmy Sandoz (obecnie Novartis). Początkowo planowano wykorzystać tę substancję jako lek działający pobudzająco na układ krwionośny i oddechowy. W trakcie dalszych badań poznano jego psychodeliczne właściwości. Lek był w latach 40. i 50. XX wieku stosowany w psychiatrii. W latach 60. i 70. XX w. jako narkotyk LSD uzyskało popularność w pewnych kręgach młodzieży szczególnie związanych z ruchem hipisowskim.\n" +
                        "\n" +
                        "LSD weszło w krąg zainteresowań CIA, która rozpoczęła w latach 50. XX w. eksperymenty z jej wywiadowczym i militarnym zastosowaniem. Projekt ten znany jest pod kryptonimem MKULTRA.\n" +
                        "\n" +
                        "Szkodliwość\n" +
                        "Zgodnie z wynikami badań prof. Davida Nutta z Bristol University i członka rządowej Komisji Doradczej ds. Nadużywania Narkotyków (ACMD) z 2007 r. LSD jest jedną z najmniej groźnych substancji psychoaktywnych. W jego klasyfikacji używek LSD zajmuje miejsce czternaste z dwudziestu, podczas gdy alkohol piąte. Pod uwagę wzięto 3 czynniki: szkody zdrowotne, ryzyko uzależnienia oraz szkodliwość społeczną[5]. W kolejnym badaniu Nutta z 2010 r. (gdzie liczbę kryteriów rozszerzono do 16) LSD zajmuje nawet niższe miejsce – osiemnaste z dwudziestu w ogólnym zestawieniu. Tuż obok znalazł się inny psychodelik tj. psylocybina. Ponadto używanie obu substancji niesie ze sobą najniższą szkodliwość dla otoczenia (na czele znalazł się alkohol)[6].\n" +
                        "\n" +
                        "Dawkowanie\n" +
                        "LSD jest jedną z najaktywniejszych pod względem aktywnej dawki dotychczas odkrytych substancji. Dawki LSD mierzy się w mikrogramach. W porównaniu z meskaliną czy MDMA, których dawki wahają się w przedziale od kilkudziesięciu do kilkuset miligramów, LSD jest pięćset do tysiąca razy aktywniejszy.\n" +
                        "\n" +
                        "Jednorazowa dawka LSD waha się w przedziale od 100 do 500 µg – jest to mniej więcej jedna dziesiąta masy ziarenka piasku. Efekt progowy występuje już przy 20 µg[7]. Maksymalne dawki przyjmowane przez ludzi to około 1 mg, jednak taka ilość może spowodować nasilenie nieprzyjemnych efektów fizycznych i psychicznych. Nie są znane przypadki uzależnienia ani fizycznego ani psychicznego od LSD. Silna tolerancja fizjologiczna przy codziennym zażywaniu LSD występuje po 2–3 dniach i utrzymuje się przez ok. 3 dni[8]. Występuje też zjawisko tolerancji krzyżowej LSD z psylocybiną[9] i meskaliną[10].\n" +
                        "\n" +
                        "Przypadkowe przedawkowanie LSD ze skutkiem śmiertelnym uważa się za mało prawdopodobne. Prawdopodobnie LD50 LSD dla człowieka zawiera się w przedziale 0,2–1 mg/kg. Znany jest przypadek śmiertelny, gdy w 1975 roku w Kentucky mężczyzna podał sobie dożylnie około 300 mg LSD[11][12]. Do roku 2008 zarejestrowano 8 przypadków omyłkowego donosowego zażycia bardzo dużych dawek LSD, po którym poziom substancji w osoczu wynosił 1–7 mg/100 ml, jednak wszystkie osoby zostały wyleczone[8].\n" +
                        "\n" +
                        "Działanie psychoaktywne LSD może zostać zniwelowane poprzez zażycie benzodiazepin, np. klonazepamu czy diazepamu. W znacznym stopniu hamują je też leki przeciwpsychotyczne, takie jak chloropromazyna. Istnieją teorie, że mleko, wapń czy cukier mogą regulować psychodeliczne działanie LSD, jednak nie znalazły one potwierdzenia w badaniach.\n" +
                        "\n" +
                        "Działanie\n" +
                        "LSD jest agonistą receptora 5-HT2A. LSD działa na zasadzie sprzężenia zwrotnego dodatniego, krótkotrwale obniża poziom serotoniny, co prowadzi do jej nadprodukcji[potrzebny przypis]. Po ok. 150–240 minutach LSD w wyniku rozpadu przestaje oddziaływać z komórkami i następuje nagły wzrost przewodnictwa – impulsy między neuronami są silniejsze, co prowadzi do wykorzystywania połączeń, które w normalnym stanie (bez nadmiaru neuroprzekaźnika) nie byłyby wykorzystane, bądź miałyby mniejszy wpływ na reakcję mózgu. Może to prowadzić – zależnie od ilości – do potęgowania odczuć, halucynacji, synestezji oraz do trwałych zmian w strukturze połączeń między neuronami, co może mieć zróżnicowane następstwa.\n" +
                        "\n" +
                        "Stosunkowo rzadkim zjawiskiem są reminiscencja – po tygodniach, miesiącach, nawet latach od zażycia środka przejawiają się epizodycznie niektóre efekty LSD, które trwają od kilku sekund do kilku godzin. Gdy te nawroty są chroniczne, mówi się o HPPD (Hallucinogen Persisting Perception Disorder) (według DSM-IV).\n" +
                        "\n" +
                        "Okres działania LSD nazywa się potocznie słowem trip (ang. trip – podróż). Pojawiają się efekty sensoryczne: wzmocnienie percepcji zmysłowej lub częściej jej zaburzenia (szczególnie wzrokowe, rzadziej dotykowe lub proprioceptywne), efekty synestetyczne, zaburzenia poczucia czasu. W większości przypadków pojawiają się bardzo przyjemne uczucia, często opisywane jako mistyczne (np. zjednoczenia z całym wszechświatem, bezpośredniego odczuwania obecności bóstwa). Po zażyciu LSD może pojawić się zjawisko zwane bad trip, często wiązane z negatywnym stanem emocjonalnym, wzmacnianym przez LSD.\n" +
                        "\n" +
                        "Fizyczne skutki działania LSD\n" +
                        "Fizyczne reakcje na LSD są bardzo różne, jednak najczęściej występują: hipertermia, gorączka, podniesiony poziom cukru we krwi, skurcze macicy, gęsia skórka, przyspieszony puls, nadprodukcja śliny, szczękościsk, pocenie się, rozszerzenie źrenic, bezsenność, parestezje, drgawki i synestezja[potrzebny przypis]. Zażywający LSD wymieniają też odrętwienie, roztrzęsienie, osłabienie i mdłości. W latach 60. XX wieku Eric Kast badał przydatność LSD w walce z silnymi bólami nowotworowymi[13]. Już niskie (niepsychoaktywne) dawki są równie skuteczne jak tradycyjnie używane opiaty, z tą różnicą, że działanie przeciwbólowe utrzymuje się dużo dłużej, nawet tydzień po zażyciu jednej dawki. Kast sądził, że efekt ten związany jest ze zmniejszeniem lęku i niepokoju. Prowadzone są badania nad tym zjawiskiem (od 2006 roku), z użyciem psychodelicznej tryptaminy, psylocybiny jako substancji zmniejszającej lęk i niepokój związany ze śmiertelnymi nowotworami.\n" +
                        "\n" +
                        "Wywiad przeprowadzony wśród osób cierpiących na klasterowy ból głowy (objawiającego się bardzo silnymi bólami głowy) wykazał, że u większości z nich zażycie LSD lub psylocybiny niwelowało ból; środki te wydłużały też okresy pomiędzy kolejnymi atakami[14]. Do celów leczniczych dla tego schorzenia zaproponowano pochodną bromową LSD (dietyloamid kwasu 2-bromolizergowego, BOL-148), która nie wykazuje działania halucynogennego[15].\n" +
                        "\n" +
                        "Zastosowania medyczne\n" +
                        "Alkoholizm\n" +
                        "W analizie randomizowanych badań klinicznych ze ślepą próbą, opublikowanych w latach 1967–1971, przeprowadzonych na dorosłych, prawie wyłącznie mężczyznach, którzy rozpoczęli leczenie alkoholizmu, stwierdzono zmniejszenie nadużywania alkoholu u 60% chorych, którym podano LSD w porównaniu do 40% z próby kontrolnej. Korzystny wpływ LSD stwierdzono w czasie kontroli 3 i 6 miesięcy po podaniu LSD. Po roku wpływ ten był niewielki i statystycznie nieznaczący. Znaczący statystycznie, korzystny wpływ LSD na utrzymanie abstynencji zanikł po 6 miesiącach od podania. Efektywność pojedynczej dawki LSD jest porównywalna do efektywności leków podawanych codziennie w leczeniu alkoholizmu, takich jak naltrekson, akamprozat czy disulfiram. Zastosowano dawki 210–800 µg. Mediana dawki wyniosła 500 µg[16].\n" +
                        "\n" +
                        "Choroby afektywne\n" +
                        "Szwajcarski psychiatra Peter Gasser jest prawdopodobnie jedynym lekarzem legalnie stosującym LSD w swojej prywatnej praktyce – zgodę uzyskał w 2014 r. Wcześniej, przez 7 lat razem ze współpracownikami prowadził badania (zaaprobowane przez Szwajcarskie Ministerstwo Zdrowia, a sponsorowane przez Multidisciplinary Association for Psychedelic Studies) nad wspomaganą LSD psychoterapią zaburzeń lękowych u osób nieuleczalnie chorych (głównie na raka). Wyniki opublikowane w 2014 r. wskazywały na korzyści, takie jak obniżenie lęku i podniesienie jakości życia. Poprawa utrzymywała się przez min. 12 miesięcy[17].\n" +
                        "\n" +
                        "Gasser stosuje LSD zarówno w terapii indywidualnej, jak i grupowej, w ramach tzw. „humanitarnego stosowania leków” (ang. compassionate use), które pozwala mu na używanie substancji także u pozostałych pacjentów (nie tylko nieuleczalnie chorych). Prawo zezwala mu na stosowanie dawki maks. 200 µg, jednak zwykle ogranicza ją do 100 µg, zwłaszcza podczas pierwszej sesji[18].\n" +
                        "\n" +
                        "Historia\n" +
                        "LSD zostało zsyntetyzowane z nadzieją, że będzie lekiem działającym rozkurczowo na macicę w trakcie porodu oraz że będzie miało działanie stymulujące. Podczas badań w laboratoriach Sandoz naukowcy zauważyli, że zwierzęta, którym podano LSD, były niespokojne. Fakt ten nie zwrócił ich szczególnej uwagi i Hofmann zarzucił badania nad LSD, by zająć się innymi pochodnymi kwasu lizergowego.\n" +
                        "\n" +
                        "Po pięciu latach z bliżej nieznanych przyczyn postanowił jednak wrócić do badań nad tym związkiem. Pewnego dnia podczas tych badań poczuł się nienaturalnie zmęczony i postanowił położyć się spać. Gdy leżał, stan zmęczenia i przyjemnego odurzenia się wzmagał i towarzyszyły temu niewyraźne halucynacje, stan ten po dwóch godzinach minął. Wywnioskował, że w jakiś sposób któraś z substancji, które aktualnie miał w laboratorium, dostała się do jego organizmu, podejrzenie padło właśnie na LSD. Po trzech dniach postanowił przeprowadzić eksperyment na samym sobie. Odmierzył sobie dawkę 0,25 mg (250 µg) i rozpuścił w wodzie.\n" +
                        "\n" +
                        "Jak później opisywał swoje doznania, „po godzinie demon opanował moją duszę”, był niezwykle przerażony i sądził, że umiera. „Wszyscy ludzie, których widziałem, mieli na sobie straszne maski”. Lekarz, który go zbadał, stwierdził jednak tylko trochę powiększone źrenice. Postanowił więc pojechać do domu – na rowerze, co stało się później legendą często przedstawianą na znaczkach nasączanych LSD[potrzebny przypis]. Odurzenie stopniowo ustępowało i uspokajał się.\n" +
                        "\n" +
                        "Później Albert Hofmann opowiadał znajomym o swoich doświadczeniach z LSD, w większości jednak nie chciano mu wierzyć, gdyż w tym czasie nie znano jeszcze związków, które w tak małej dawce mogą mieć tak silne działanie. Później jednak wielu chemików, psychologów i psychiatrów przeprowadziło na sobie eksperymenty z LSD, które w pełni potwierdziły obserwacje Hofmanna. Halucynacje wywoływane tą substancją inspirowały także artystów, m.in. niemieckiego pisarza Ernsta Jüngera, znajomego Hofmanna, który opisał swoje doświadczenia z LSD w książkach „Strahlungen” i „Drogen und Rausch”.\n" +
                        "\n" +
                        "Aldous Huxley na łożu śmierci kazał sobie podać dawkę 200 µg LSD[potrzebny przypis][19]\n" +
                        "\n" +
                        "Firma Sandoz zaczęła produkować LSD jako lek w formie tabletek 25 µg i ampułek 100 µg pod nazwą handlową Delysid. W latach 60. XX w. nasiliło się bardzo zjawisko pozamedycznego używania tego leku, zwłaszcza w środowisku hipisów. Po kilku latach dyrektor firmy Sandoz powiedział Hofmannowi, że lepiej by było, gdyby LSD nie został odkryty[20]. Kilka lat później (1967), FDA wykreśliła LSD z amerykańskiego lekospisu, co zmusiło firmę Sandoz do zaprzestania jego produkcji. Od tego czasu datuje się historia LSD jako nielegalnego narkotyku.\n" +
                        "\n" +
                        "W 2001 roku policja w Stanach Zjednoczonych odkryła laboratorium produkujące LSD w silosie po rakietach atomowych. Znaleziono w nim 40,1 kg LSD, czyli około 401 milionów dawek po 100 µg. Alexander Shulgin prowadził również badania nad LSD i efekty swoich badań opisał razem z żoną Ann Shulgin w książce TiHKAL (opisuje metody syntezy i działanie wielu tryptamin, w tym LSD).\n" +
                        "\n" +
                        "Innym badaczem wpływu LSD na umysł jest psychiatra Stanislav Grof. Efekty badań opisał w swoich książkach, z których w Polsce wydano „Przygoda odkrywania samego siebie”, „Poza mózg – narodziny, śmierć i transcendencja” oraz „Obszary nieświadomości. Raport z badań nad LSD”."),
                new Picture((long) 4, "testo.jpg", "Obrazek czwarty", "Opis obrazka czwartego", 4, new Date(), admin, Collections.singletonList(aComments[3]),
                        "Prohibicja w Stanach Zjednoczonych, znana także jako The Noble Experiment („Szlachetny Eksperyment”) trwała od 1919 do 1933 roku. W okresie tym sprzedaż, produkcja oraz transport alkoholu były zakazane na terenie całego kraju[1]. Prohibicję wprowadzała 18. poprawka do Konstytucji Stanów Zjednoczonych\n" +
                        "\n" +
                        "Senat Stanów Zjednoczonych zaproponował tę poprawkę 18 grudnia 1917; poparta przez 36 Stanów, została ratyfikowana 16 stycznia 1919, natomiast wprowadzona w życie została 17 stycznia 1920 roku[2]. Niektóre stany wprowadziły prohibicję zanim 18. poprawka została ratyfikowana.\n" +
                        "\n" +
                        "„Ustawa Volsteada”, jak potocznie nazywano Narodową Ustawę o Prohibicji, została uchwalona przez Kongres mimo weta prezydenta Woodrowa Wilsona 28 października 1919 roku i ustaliła definicję prawną alkoholi oraz kary za ich produkcję[3]. Choć ustawa zakazywała sprzedaży alkoholu, jego produkcji i transportu, rząd federalny podjął niewielkie kroki, by ustawa była respektowana. W 1925 roku w Nowym Jorku funkcjonowało od 30 do 100 tys. klubów, w których można było nielegalnie napić się alkoholu[4].\n" +
                        "\n" +
                        "Po wprowadzeniu zakazu ilość spożywanego alkoholu zmalała, jednak prohibicja doprowadziła do rozprzestrzenienia się organizacji przestępczych i rozkwitu podziemnego handlu[5]. W czasach Wielkiego Kryzysu (1929-1933) ustawa stawała się coraz bardziej niepopularna, szczególnie w dużych miastach. Do czasu Masakry w Dniu Świętego Walentego w 1929, Amerykanie uważali, pomimo niektórych niepowodzeń, że prohibicja działa i przynosi zamierzone efekty.\n" +
                        "\n" +
                        "W dniu 22 marca 1933 roku prezydent Franklin Roosevelt podpisał poprawkę do ustawy Volsteada, zwaną „Cullen-Harrison”, pozwalającą na produkcję i sprzedaż niektórych rodzajów napojów alkoholowych. 5 grudnia 1933 ratyfikowano 21. poprawkę do Konstytucji Stanów Zjednoczonych, która znosiła poprawkę 18. wprowadzającą prohibicję. Tym niemniej, prawo federalne Stanów Zjednoczonych nadal zakazuje produkcji wyrobów spirytusowych bez spełnienia licznych wymogów licencyjnych, które powodują, że legalna produkcja napojów spirytusowych na własny użytek jest ciągle niepraktyczna[6]. " +
                        "zyciu"),
                new Picture((long) 5, "testo2.jpg", "Obrazek piąty", "Opis obrazka piątego", 42, new Date(), admin, Collections.emptyList(),
                        "putinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputin ")
        };
    }

}