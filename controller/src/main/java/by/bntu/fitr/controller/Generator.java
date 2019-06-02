package by.bntu.fitr.controller;

import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.book.BookDto;
import by.bntu.fitr.dto.book.BookmarkDto;
import by.bntu.fitr.dto.book.GenreDto;
import by.bntu.fitr.dto.book.LanguageDto;
import by.bntu.fitr.dto.book.OrganizationDto;
import by.bntu.fitr.dto.book.PublishingHouseDto;
import by.bntu.fitr.dto.news.NewsDto;
import by.bntu.fitr.dto.user.RoleDto;
import by.bntu.fitr.dto.user.UserDataDto;
import by.bntu.fitr.dto.user.UserDto;
import by.bntu.fitr.dto.user.UserMainDataDto;
import by.bntu.fitr.dto.user.order.OrderDetailDto;
import by.bntu.fitr.dto.user.order.OrderDto;
import by.bntu.fitr.dto.user.util.AddressDto;
import by.bntu.fitr.dto.user.util.CityDto;
import by.bntu.fitr.dto.user.util.CountryDto;
import by.bntu.fitr.dto.user.util.StateDto;
import by.bntu.fitr.model.book.Book;
import by.bntu.fitr.model.book.Bookmark;
import by.bntu.fitr.service.book.AuthorService;
import by.bntu.fitr.service.book.BookCoverService;
import by.bntu.fitr.service.book.BookService;
import by.bntu.fitr.service.book.GenreService;
import by.bntu.fitr.service.book.LanguageService;
import by.bntu.fitr.service.book.OrganizationService;
import by.bntu.fitr.service.book.PublishingHouseService;
import by.bntu.fitr.service.news.NewsCoverService;
import by.bntu.fitr.service.news.NewsService;
import by.bntu.fitr.service.user.BookmarkService;
import by.bntu.fitr.service.user.UserDataService;
import by.bntu.fitr.service.user.UserMainDataService;
import by.bntu.fitr.service.user.UserService;
import by.bntu.fitr.service.user.order.OrderService;
import by.bntu.fitr.service.user.util.AddressService;
import by.bntu.fitr.service.user.util.CityService;
import by.bntu.fitr.service.user.util.CountryService;
import by.bntu.fitr.service.user.util.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@RequestMapping(value = "/generator")
public class Generator {

    private List<String> words;
    private BookService bookService;
    private BookCoverService bookCoverService;
    private OrganizationService organizationService;
    private LanguageService languageService;
    private PublishingHouseService publishingHouseService;
    private GenreService genreService;
    private AuthorService authorService;

    private CountryService countryService;
    private StateService stateService;
    private CityService cityService;

    private UserMainDataService userMainDataService;
    private UserDataService userDataService;
    private UserService userService;

    private NewsService newsService;
    private NewsCoverService newsCoverService;

    private BookmarkService bookmarkService;
    private OrderService orderService;

    private AddressService addressService;

    private final static int ADMIN_PERCENT = 20;
    private final static int COUNT_COUNTRY = 10;
    private final static int COUNT = 500;
    private final static int MIN_ID = 1;
    private final static int MAX_ID = MIN_ID+COUNT-1;


    private final static int COUNT_RU = COUNT;
    private final static int MIN_ID_RU = MAX_ID+1;
    private final static int MAX_ID_RU = MIN_ID_RU+COUNT_RU-1;

    @Value("${file.back}")
    private String LIBRARY_BACK_END_PATH;
//    private final static String LIBRARY_BACK_END_PATH = "c:/dp/library-back-end";
//    private final static String LIBRARY_BACK_END_PATH = "/media/nikskonda/20B6EA8BB6EA60B0/homeProject/dp/library-back-end";

    private final static String VOCABULARY_EN = "/controller/src/main/resources/vocabulary.txt";
    private final static String VOCABULARY_RU = "/controller/src/main/resources/vocabulary_ru.txt";

    @Value("${file.uploadDir}")
    private String DATA_FOLDER;

//    private final static String DATA_FOLDER = "c:/dp/files/uploads/";
//    private final static String DATA_FOLDER = "media/nikskonda/20B6EA8BB6EA60B0/homeProject/dp/files/uploads/";

    private final static String BOOK_IMG = "book/img/";
    private final static String BOOK_TH = "book/th/";
    private final static String BOOK_PDF = "book/pdf/";
    private final static String BOOK_EPUB = "book/epub/";
    private final static String NEWS_IMG = "news/img/";
    private final static String NEWS_TH = "news/th/";

    private final static String USER_AVA = "user/avatar/";

    private final static String ENGLISH = "en-US";
    private final static String RUSSIAN = "ru-RU";

    private String getRandomFile(String str){
        Random rand = new Random();
//
//        File dir = new File(DATA_FOLDER+str);
//        File[] files = dir.listFiles();
//
//        File file = files[rand.nextInt(files.length)];
//        return str+(file.getName());

        if (str.equals(USER_AVA)){
            return str+(rand.nextInt(20)+1)+".png";
        }
        if (str.equals(BOOK_PDF)){
            return str+(rand.nextInt(20)+1)+".pdf";
        }
        if (str.equals(BOOK_EPUB)){
            return str+(rand.nextInt(6)+1)+".epub";
        }
        if (str.equals(BOOK_IMG)){
            return str+(rand.nextInt(22)+1)+".jpg";
        }
        if (str.equals(BOOK_TH)){
            return str+(rand.nextInt(22)+1)+".jpg";
        }
        return str+(rand.nextInt(20)+1)+".jpg";
    }

    @Autowired
    public Generator(BookService bookService, BookCoverService bookCoverService, OrganizationService organizationService, LanguageService languageService, PublishingHouseService publishingHouseService, GenreService genreService, AuthorService authorService, CountryService countryService, StateService stateService, CityService cityService, UserMainDataService userMainDataService, UserDataService userDataService, UserService userService, NewsService newsService, NewsCoverService newsCoverService, BookmarkService bookmarkService, OrderService orderService, AddressService addressService) {
        this.bookService = bookService;
        this.bookCoverService = bookCoverService;
        this.organizationService = organizationService;
        this.languageService = languageService;
        this.publishingHouseService = publishingHouseService;
        this.genreService = genreService;
        this.authorService = authorService;
        this.countryService = countryService;
        this.stateService = stateService;
        this.cityService = cityService;
        this.userMainDataService = userMainDataService;
        this.userDataService = userDataService;
        this.userService = userService;
        this.newsService = newsService;
        this.newsCoverService = newsCoverService;
        this.bookmarkService = bookmarkService;
        this.orderService = orderService;
        this.addressService = addressService;
    }

    @GetMapping
    public String generate() {

        generateLang();

        initStrings(LIBRARY_BACK_END_PATH+VOCABULARY_EN, Pattern.compile("[A-Za-z]+"));


        generateAuthors(COUNT);
        generateGenres(COUNT);
        generateOrg(COUNT);
        generatePH(COUNT);

        generateBooks(COUNT, MIN_ID, MAX_ID, getEngLang());

        generateCountry(COUNT_COUNTRY, MIN_ID, MAX_ID);

        generateDefaultUsers(MIN_ID, MAX_ID);

        generateUser(COUNT-4, MIN_ID, MAX_ID);

        generateNews(COUNT, MIN_ID, MAX_ID, getEngLang());
        generateOrder(COUNT, MIN_ID, MAX_ID);
        generateBookmark(COUNT, MIN_ID, MAX_ID);


        initStrings(LIBRARY_BACK_END_PATH+VOCABULARY_RU, Pattern.compile("[А-Яа-я]+"));

        generateAuthors(COUNT_RU);
        generateGenres(COUNT_RU);
        generateOrg(COUNT_RU);
        generatePH(COUNT_RU);

        generateBooks(COUNT_RU, MIN_ID_RU, MAX_ID_RU, getRuLang());

        generateCountry(COUNT_COUNTRY, MIN_ID_RU, MAX_ID_RU);

        generateUser(COUNT_RU, MIN_ID_RU, MAX_ID_RU);

        generateNews(COUNT_RU, MIN_ID_RU, MAX_ID_RU, getRuLang());
        generateOrder(COUNT_RU, MIN_ID_RU, MAX_ID_RU);
        generateBookmark(COUNT_RU, MIN_ID_RU, MAX_ID_RU);

        return "Success!";
    }

    private AddressDto getRandomAddressDto(String firstName, String lastName, int minId, int maxId){
//        List<AddressDto> list = addressService.findByUsername(username);
//        if (list.size()>0){
//            return list.get(generateInt(0, list.size()-1));
//        }
        AddressDto address = new AddressDto();
        address.setCity(getRandomCity(minId, maxId));
        address.setFirstName(firstName);
        address.setLastName(lastName);
        address.setPhone(generateInt(100000, 999999)+"");
        address.setPostalCode(generateInt(100000, 999999)+"");
        address.setAddress(generateUrl());
        return address;

    }



    private void generateBookmark(int count, int minId, int maxId){
        for(int i=0; i<count; i++){
            BookmarkDto bookmarkDto = new BookmarkDto();
            BookDto book = new BookDto();
            book.setId((long)generateInt(minId, maxId));
            bookmarkDto.setBook(book);
            bookmarkDto.setPage(generateInt(10, 1000));
            bookmarkDto.setType(getBookmarkType());
            PageableDto pageableDto =  new PageableDto();
            pageableDto.setNumber(0);
            pageableDto.setSize(count);
            boolean flag = true;
            String username = "";
            while (flag){
                flag = false;
                username = getRandomUserDataDto(minId, maxId).getUsername();
                Page<BookmarkDto> page = bookmarkService.findAll(username, pageableDto);
                for (BookmarkDto b : page){
                    if (b.getBook().getId().equals(bookmarkDto.getBook().getId())){
                        flag = true;
                        break;
                    }
                }
            }

            bookmarkService.save(bookmarkDto, username);
        }
    }

    private Bookmark.Type getBookmarkType(){
        return Bookmark.Type.values()[generateInt(0, Bookmark.Type.values().length)];
    }

    private void generateOrder(int count, int minId, int maxId){
        for(int i=0; i<count; i++){
            OrderDto orderDto = new OrderDto();
            orderDto.setUser(getRandomUserDataDto(minId, maxId));
            if (generateInt(0, 10)>5)
                orderDto.setAddress(getRandomAddressDto(orderDto.getUser().getFirstName(), orderDto.getUser().getLastName(), minId, maxId));
            else {
                if (orderDto.getUser().getRegistrationAddress()!=null && generateInt(0, 10)>6)
                    orderDto.setAddress(orderDto.getUser().getRegistrationAddress());
                else
                    orderDto.setAddress(getRandomAddressDto(getRandomWordWithFirstUpper(), getRandomWordWithFirstUpper(), minId, maxId));
            }

            orderDto.setDetails(new HashSet<>());

            int countBook = generateInt(1, 5);
            for (int j = 0; j< countBook; j++){
                OrderDetailDto orderDetailDto = new OrderDetailDto();

                BookDto bookDto = getRandomBook(minId, maxId);
                while (bookDto.isInLibraryUseOnly()){
                    bookDto = getRandomBook(minId, maxId);
                }
                orderDetailDto.setBook(bookDto);
                orderDetailDto.setCount(generateInt(1,5));
                if (generateInt(0, 10)>5)
                    orderDetailDto.setComment(getRandomWord(generateInt(5, 20)));
                orderDto.getDetails().add(orderDetailDto);
            }

            orderService.save(orderDto, orderDto.getUser().getUsername());
        }
    }

    private void generateNews(int count, int minId, int maxId, LanguageDto languageDto){
        for (int i=0; i<count; i++){
            NewsDto newsDto = new NewsDto();
            newsDto.setTitle(getRandomSentence(generateInt(4, 14)));
            String text = getRandomParagraphs(generateInt(4, 20));
            if (text.length()>=10000){
                text = text.substring(0, 9999);
            }
            newsDto.setLanguage(languageDto);
            newsDto.setText(text);
            newsDto.setCreator(getRandomUserDataDto(minId, maxId));
            newsDto.setPictureUrl(getRandomFile(NEWS_IMG));
            newsDto.setThumbnailUrl(newsDto.getPictureUrl().replace("img","th"));
            newsService.save(newsDto, newsDto.getCreator().getUsername());
        }
    }

    private UserMainDataDto getRandomUserMainDataDto(int minId, int maxId){
        if (generateInt(0, 100)<ADMIN_PERCENT){
            return userMainDataService.find("admin");
        }
        return userMainDataService.find((long)generateInt(minId, maxId));
    }

    private UserDataDto getRandomUserDataDto(int minId, int maxId){
        if (generateInt(0, 100)<ADMIN_PERCENT){
            return userDataService.find("admin");
        }
        return userDataService.find((long)generateInt(minId, maxId));
    }

    private UserDto getRandomUserDto(int minId, int maxId){
        if (generateInt(0, 100)<ADMIN_PERCENT){
            return userService.find("admin");
        }
        return userService.find((long)generateInt(minId, maxId));
    }

    private void generateDefaultUsers(int minId, int maxId){
        List<RoleDto> roleList = generateDefRole();
        UserDto userDto = new UserDto();
        userDto.setUsername("user");
        userDto.setPassword("user");
        Set<RoleDto> set = new HashSet<>();
        set.add(roleList.get(0));
        userDto.setAuthorities(set);
        String str = getRandomWord();
        userDto.setFirstName(str.substring(0, 1).toUpperCase() + str.substring(1));
        str = getRandomWord();
        userDto.setLastName(str.substring(0, 1).toUpperCase() + str.substring(1));
        userDto.setEmail(getRandomWord()+'@'+getRandomWord()+".com");
        userService.save(userDto);

        userDto = new UserDto();
        userDto.setUsername("courier");
        userDto.setPassword("courier");
        set = new HashSet<>();
        set.add(roleList.get(0));
        set.add(roleList.get(1));
        userDto.setAuthorities(set);
        str = getRandomWord();
        userDto.setFirstName(str.substring(0, 1).toUpperCase() + str.substring(1));
        str = getRandomWord();
        userDto.setLastName(str.substring(0, 1).toUpperCase() + str.substring(1));
        userDto.setEmail(getRandomWord()+'@'+getRandomWord()+".com");
        userService.save(userDto);

        userDto = new UserDto();
        userDto.setUsername("operator");
        userDto.setPassword("operator");
        set = new HashSet<>();
        set.add(roleList.get(0));
        set.add(roleList.get(2));
        userDto.setAuthorities(set);
        str = getRandomWord();
        userDto.setFirstName(str.substring(0, 1).toUpperCase() + str.substring(1));
        str = getRandomWord();
        userDto.setLastName(str.substring(0, 1).toUpperCase() + str.substring(1));
        userDto.setEmail(getRandomWord()+'@'+getRandomWord()+".com");
        userService.save(userDto);

        userDto = new UserDto();
        userDto.setUsername("journalist");
        userDto.setPassword("journalist");
        set = new HashSet<>();
        set.add(roleList.get(0));
        set.add(roleList.get(3));
        userDto.setAuthorities(set);
        str = getRandomWord();
        userDto.setFirstName(str.substring(0, 1).toUpperCase() + str.substring(1));
        str = getRandomWord();
        userDto.setLastName(str.substring(0, 1).toUpperCase() + str.substring(1));
        userDto.setEmail(getRandomWord()+'@'+getRandomWord()+".com");
        userService.save(userDto);

        userDto = new UserDto();
        userDto.setUsername("librarian");
        userDto.setPassword("librarian");
        set = new HashSet<>();
        set.add(roleList.get(0));
        set.add(roleList.get(4));
        userDto.setAuthorities(set);
        str = getRandomWord();
        userDto.setFirstName(str.substring(0, 1).toUpperCase() + str.substring(1));
        str = getRandomWord();
        userDto.setLastName(str.substring(0, 1).toUpperCase() + str.substring(1));
        userDto.setEmail(getRandomWord()+'@'+getRandomWord()+".com");
        userService.save(userDto);

        userDto = new UserDto();
        userDto.setUsername("administrator");
        userDto.setPassword("administrator");
        set = new HashSet<>();
        set.add(roleList.get(0));
        set.add(roleList.get(5));
        userDto.setAuthorities(set);
        str = getRandomWord();
        userDto.setFirstName(str.substring(0, 1).toUpperCase() + str.substring(1));
        str = getRandomWord();
        userDto.setLastName(str.substring(0, 1).toUpperCase() + str.substring(1));
        userDto.setEmail(getRandomWord()+'@'+getRandomWord()+".com");
        userService.save(userDto);

        userDto = new UserDto();
        userDto.setUsername("admin");
        userDto.setPassword("admin");
        set = new HashSet<>();
        set.add(roleList.get(0));
        set.add(roleList.get(1));
        set.add(roleList.get(2));
        set.add(roleList.get(3));
        set.add(roleList.get(4));
        set.add(roleList.get(5));
        userDto.setAuthorities(set);
        str = getRandomWord();
        userDto.setFirstName(str.substring(0, 1).toUpperCase() + str.substring(1));
        str = getRandomWord();
        userDto.setLastName(str.substring(0, 1).toUpperCase() + str.substring(1));
        userDto.setEmail(getRandomWord()+'@'+getRandomWord()+".com");
        userService.save(userDto);


    }

    private void generateUser(int count, int minId, int maxId){
        UserDto userDto = new UserDto();
        String str;

        List<RoleDto> roleList = generateDefRole();

        List<String> usernames = new ArrayList<>(words);
        for (int i=0; i<count; i++){
            userDto = new UserDto();
            str = getRandomWord(usernames);
            userDto.setUsername(str);
            userDto.setPassword(str);
            Set<RoleDto> set = getRandomRoleSet(generateInt(1, 5), roleList);
            set.add(roleList.get(0));
            userDto.setAuthorities(set);
            str = getRandomWord();
            if (generateInt(0, 10)>5)
                userDto.setFirstName(str.substring(0, 1).toUpperCase() + str.substring(1));
            str = getRandomWord();
            if (generateInt(0, 10)>5)
                userDto.setLastName(str.substring(0, 1).toUpperCase() + str.substring(1));
            if (generateInt(0, 10)>5)
                userDto.setEmail(getRandomWord()+'@'+getRandomWord()+".com");
            if (generateInt(0, 10)>3)
                userDto.setAvatarUrl(getRandomFile(USER_AVA));
            if (generateInt(0, 10)>5)
                userDto.setRegistrationAddress(getRandomAddressDto(userDto.getFirstName(), userDto.getLastName(), minId, maxId));
            userService.save(userDto);
        }
    }

    private Set<RoleDto> getRandomRoleSet(int count, List<RoleDto> roleList){
        Set<RoleDto> set = new HashSet<>();
        for (int i=0; i<count; i++){
            set.add(roleList.get(generateInt(0, roleList.size())));
        }
        return set;
    }

    private List<RoleDto> generateDefRole() {
        List<RoleDto> list = new ArrayList<>();
        list.add(new RoleDto("USER", 0));
        list.add(new RoleDto("COURIER", 25));
        list.add(new RoleDto("OPERATOR", 30));
        list.add(new RoleDto("JOURNALIST", 50));
        list.add(new RoleDto("LIBRARIAN", 75));
        list.add(new RoleDto("ADMIN", 100));
        return list;
    }

    private List<RoleDto> generateRole(int count){
        Set<RoleDto> set = new HashSet<>();
        RoleDto roleDto;
        List<String> strings = new ArrayList<>(words);
        for (int i=0; i<count; i++){
            roleDto = new RoleDto();
            roleDto.setAuthority(getRandomWord(strings).toUpperCase());
            set.add(roleDto);
        }
        List<RoleDto> list = generateDefRole();
        list.addAll(new ArrayList<>(set));
        return list;
    }

    private CityDto getRandomCity(int minId, int maxId){
        return cityService.find(generateLong(minId, maxId));
    }

    private void generateCity(int count, int minId, int maxId, StateDto stateDto, List<String> city){
        List<String> strings = new ArrayList<>(words);
        for (int i=0; i<count; i++){
            if (city.size()==0) break;
            CityDto cityDto = new CityDto();
            String str = getRandomWord(city);
            cityDto.setName(str.substring(0, 1).toUpperCase() + str.substring(1));
            cityDto.setState(stateDto);
            cityService.save(cityDto);
        }
    }

    private void generateState(int count, int minId, int maxId, CountryDto countryDto, List<String> state, List<String> city){
        for (int i=0; i<count; i++){
            if (state.size()==0) break;
            StateDto stateDto = new StateDto();
            String str = getRandomWord(state);
            stateDto.setName(str.substring(0, 1).toUpperCase() + str.substring(1));
            stateDto.setCountry(countryDto);
            stateDto = stateService.save(stateDto);
            for (int j = 0; j< COUNT_COUNTRY; j++) {
                generateCity(1, minId, maxId, stateDto, city);
            }
        }
    }

    private void generateCountry(int count, int minId, int maxId){
        List<String> strings = new ArrayList<>(words);
        List<String> state = new ArrayList<>(words);
        List<String> city = new ArrayList<>(words);
        for (int i=0; i<count; i++){
            CountryDto countryDto = new CountryDto();
            String str = getRandomWord(strings);
            countryDto.setName(str.substring(0, 1).toUpperCase() + str.substring(1));
            countryDto = countryService.save(countryDto);
            for (int j = 0; j< COUNT_COUNTRY; j++) {
                generateState(1, minId, maxId, countryDto, state, city);
            }
        }
    }


    private BookDto getRandomBook(int minId, int maxId){
        return bookService.find(generateLong(minId, maxId));
    }

    private Set<BookDto> getRandomBookSet(int minId, int maxId, int count){
        Set<BookDto> bookDtos = new HashSet<>();
        for (int i =0; i<count; i++){
            bookDtos.add(getRandomBook(minId, maxId));
        }
        return bookDtos;
    }



    private BookDto generateBook(int minId, int maxId, LanguageDto languageDto){
        BookDto bookDto = new BookDto();
        bookDto.setTitle(getRandomWord(generateInt(1,6)));
        String text = getRandomParagraphs(generateInt(2,6));
        if (text.length()>=3000){
            text = text.substring(0, 2999);
        }
        bookDto.setDescription(text);
        bookDto.setAuthors(getRandomAuthorSet(minId, maxId, generateInt(1, 3)));
        if (generateInt(0, 10)>8){
            bookDto.setTranslators(getRandomAuthorSet(minId, maxId, generateInt(1, 3)));
        }
        bookDto.setGenres(getRandomGenreSet(minId, maxId, generateInt(2, 5)));
        if (generateInt(0, 10)>8) {
            bookDto.setAgeRestriction(generateInt(14, 22)+"+");
        }
        if (generateInt(0, 10)>6){
            bookDto.setImporter(getRandomOrg(minId, maxId));
        }
        if (generateInt(0, 10)>5){
            bookDto.setProducer(getRandomOrg(minId, maxId));
        }
        bookDto.setLanguage(languageDto);
        bookDto.setPages(generateInt(0, 500));
        if (generateInt(0, 10)>3){
            bookDto.setPublishingHouse(getRandomPH(minId, maxId));
        }
        bookDto.setType(getType());
        if (generateInt(0, 10)>3){
            bookDto.setRating(generateInt(0, 100));
        }
        if (generateInt(0, 10)>3){
            bookDto.setYear(generateInt(1800, 2019));
        }
        if (generateInt(0, 10)>6){
            bookDto.setWeight(generateInt(200, 5000));
        }
        if (generateInt(0, 10)>6){
            bookDto.setSize(generateInt(100, 300)+"x"+generateInt(100, 300));
        }
        bookDto.setPictureUrl(getRandomFile(BOOK_IMG));
        bookDto.setThumbnailUrl(bookDto.getPictureUrl().replace("img","th"));
        if (generateInt(0, 10)>4){
            bookDto.setPdfUrl(getRandomFile(BOOK_PDF));
        }
//        if (generateInt(0, 10)>6){
//            bookDto.setEPubUrl(getRandomFile(BOOK_EPUB));
//        }
        if (generateInt(0, 10)>7){
            bookDto.setInLibraryUseOnly(true);
        }else {
            bookDto.setInLibraryUseOnly(false);
        }
        if (generateInt(0, 10)>6){
            bookDto.setIsbn(generateInt(1000000, Integer.MAX_VALUE)+"");
        }
        bookDto.setCount(generateInt(1, 100));
        return bookDto;
    }

    private Book.Type getType(){
        return Book.Type.values()[generateInt(0, Book.Type.values().length)];
    }

//    private Book.Status getStatus(){
//        return Book.Status.values()[generateInt(0, Book.Status.values().length)];
//    }

    private void generateBooks(int count, int minId, int maxId, LanguageDto languageDto){
        for (int i=0; i<count; i++){
            bookService.save(generateBook(minId, maxId, languageDto));
        }
    }

    private AuthorDto getRandomAuthor(int minId, int maxId){
        return authorService.find(generateLong(minId, maxId));
    }

    private Set<AuthorDto> getRandomAuthorSet(int minId, int maxId, int count){
        Set<AuthorDto> authorDtos = new HashSet<>();
        for (int i =0; i<count; i++){
            authorDtos.add(getRandomAuthor(minId, maxId));
        }
        return authorDtos;
    }

    private void generateAuthors(int count){
        for (int i=0; i<count; i++){
            AuthorDto authorDto = new AuthorDto();
            String str = getRandomWord();
            authorDto.setFirstName(str.substring(0, 1).toUpperCase() + str.substring(1));
            str = getRandomWord();
            authorDto.setLastName(str.substring(0, 1).toUpperCase() + str.substring(1));
            if (generateInt(0, 10)>5){
                String text = getRandomWord(generateInt(150, 300));
                if (text.length()>=500){
                    text = text.substring(0, 499);
                }
                authorDto.setDescription(text);
                authorDto.setWikiLink(generateLink());
            }
            authorService.save(authorDto);
        }
    }


    private GenreDto getRandomGenre(int minId, int maxId){
        return genreService.find(generateLong(minId, maxId));
    }

    private Set<GenreDto> getRandomGenreSet(int minId, int maxId, int count){
        Set<GenreDto> genres = new HashSet<>();
        for (int i =0; i<count; i++){
            genres.add(getRandomGenre(minId, maxId));
        }
        return genres;
    }

    private void generateGenres(int count){
        List<String> strings = new ArrayList<>(new HashSet<>(words));
        for (int i=0; i<count; i++){
            GenreDto genreDto = new GenreDto();
            genreDto.setName(getRandomWord(strings));
            genreService.save(genreDto);
        }
    }


    private LanguageDto getEngLang(){
        return languageService.findByTag(ENGLISH);
    }

    private LanguageDto getRuLang(){
        return languageService.findByTag(RUSSIAN);
    }

    private void generateLang(){
        LanguageDto dto = new LanguageDto();
        dto.setName("English");
        dto.setTag(ENGLISH);
        languageService.save(dto);
        dto.setName("Руский");
        dto.setTag(RUSSIAN);
        languageService.save(dto);

    }

    private PublishingHouseDto getRandomPH(int minId, int maxId){
        return publishingHouseService.find(generateLong(minId, maxId));
    }

    private void generatePH(int count){
        for (int i=0; i<count; i++){
            PublishingHouseDto dto = new PublishingHouseDto();
            dto.setTitle(getRandomWord(generateInt(2,5)));
            if (dto.getTitle().length()>39){
                dto.setTitle(dto.getTitle().substring(0, 39));
            }
            dto.setDescription(getRandomWord(generateInt(10,50)));
            if (generateInt(0, 10)>5){
                dto.setLogoUrl(generateUrl());
                dto.setSiteLink(generateLink());
            }
            publishingHouseService.save(dto);
        }
    }

    private OrganizationDto getRandomOrg(int minId, int maxId){
        return organizationService.find(generateLong(minId, maxId));
    }

    private void generateOrg(int count){
        for (int i=0; i<count; i++){
            OrganizationDto dto = new OrganizationDto();
            dto.setTitle(getRandomWord(generateInt(2,5)));
            organizationService.save(dto);
        }
    }

    private String generateUrl(){
        return "url/"+replaceSpacesOnSlash();
    }

    private String generateLink(){
        return "link/"+replaceSpacesOnSlash();
    }

    private String replaceSpacesOnSlash(){
        return getRandomWord(generateInt(3, 6)).replaceAll(" ","/");
    }

    private String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            for (String line : lines) {
                stringBuilder.append(line).append(" ");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return stringBuilder.toString();
    }

    private void initStrings(String fileName, Pattern pattern) {
        Set<String> newWords = new HashSet<>();
        String text = readFromFile(fileName);
        Pattern p = pattern;
        Matcher m = p.matcher(text);
        while (m.find()) {
            newWords.add(text.substring(m.start(), m.end()).toLowerCase());
        }
        this.words = new ArrayList<>(newWords);
    }

    private String getRandomWordWithFirstUpper(){
        String str = getRandomWord();
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private String getRandomParagraphs(int count){
        StringBuilder stringBuilder = new StringBuilder(getRandomParagraph(generateInt(1, 10)));
        for (int i=0; i<count-1; i++){
            stringBuilder.append("\n").append(getRandomParagraph(generateInt(1, 10)));
        }
        return stringBuilder.toString();
    }

    private String getRandomParagraph(int countSentences){
        StringBuilder stringBuilder = new StringBuilder(getRandomSentence(generateInt(3, 15)));
        for (int i=0; i<countSentences-1; i++){
            stringBuilder.append(" ").append(getRandomSentence(generateInt(3, 15)));
        }
        return stringBuilder.toString();
    }

    private String getRandomSentence(int words){
        StringBuilder stringBuilder = new StringBuilder(getRandomWordWithFirstUpper());
        for (int i=0; i<words-1; i++){
            stringBuilder.append(" ").append(getRandomWord());
        }
        stringBuilder.append(".");
        return stringBuilder.toString();
    }

    private String getRandomWord(List<String> words){
        String str = words.get(generateInt(0, words.size()-1));
        words.remove(str);
        return str;
    }

    private String getRandomWord(){
        return this.words.get(generateInt(0, this.words.size()-1));
    }

    private String getRandomWord(int count){
        StringBuilder stringBuilder = new StringBuilder(getRandomWord());
        for (int i=0; i<count-1; i++){
            stringBuilder.append(" ").append(getRandomWord());
        }
        return stringBuilder.toString();
    }

    private int generateInt(int min, int max) {
        if (min>=max) return min;
        Random random = new Random();
        return min + random.nextInt(max - min);
    }

    private long generateLong(long min, long max) {
        Random random = new Random();
        return min + random.nextInt((int)(max - min));
    }

    private double generateDouble(int min, int max) {
        Random random = new Random();
        return generateInt(min, max - 1) + random.nextDouble();
    }

    private BigDecimal generateBigDecimal(int min, int max) {
        return new BigDecimal(generateDouble(min, max));
    }

    private char generateChar() {
        Random r = new Random();
        return (char) (r.nextInt(26) + 'a');
    }



}
