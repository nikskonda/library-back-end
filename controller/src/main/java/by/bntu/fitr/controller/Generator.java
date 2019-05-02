package by.bntu.fitr.controller;

import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.book.*;
import by.bntu.fitr.dto.news.NewsDto;
import by.bntu.fitr.dto.user.*;
import by.bntu.fitr.dto.user.order.OrderDetailDto;
import by.bntu.fitr.dto.user.order.OrderDto;
import by.bntu.fitr.dto.user.util.AddressDto;
import by.bntu.fitr.dto.user.util.CityDto;
import by.bntu.fitr.dto.user.util.CountryDto;
import by.bntu.fitr.dto.user.util.StateDto;
import by.bntu.fitr.model.book.Book;
import by.bntu.fitr.model.book.Bookmark;
import by.bntu.fitr.model.user.order.OrderStatus;
import by.bntu.fitr.service.book.AuthorService;
import by.bntu.fitr.service.book.BookCoverService;
import by.bntu.fitr.service.book.BookService;
import by.bntu.fitr.service.book.GenreService;
import by.bntu.fitr.service.book.LanguageService;
import by.bntu.fitr.service.book.OrganizationService;
import by.bntu.fitr.service.book.PublishingHouseService;
import by.bntu.fitr.service.news.NewsCoverService;
import by.bntu.fitr.service.news.NewsService;
import by.bntu.fitr.service.user.*;
import by.bntu.fitr.service.user.order.OrderService;
import by.bntu.fitr.service.user.util.AddressService;
import by.bntu.fitr.service.user.util.CityService;
import by.bntu.fitr.service.user.util.CountryService;
import by.bntu.fitr.service.user.util.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private final static int COUNT = 10;
    private final static int MIN_ID = 1;
    private final static int MAX_ID = MIN_ID+COUNT-1;


    private final static int COUNT_RU = 10;
    private final static int MIN_ID_RU = MAX_ID+1;
    private final static int MAX_ID_RU = MIN_ID_RU+COUNT_RU-1;



    @Autowired
    public Generator(List<String> words, BookService bookService, BookCoverService bookCoverService, OrganizationService organizationService, LanguageService languageService, PublishingHouseService publishingHouseService, GenreService genreService, AuthorService authorService, CountryService countryService, StateService stateService, CityService cityService, UserMainDataService userMainDataService, UserDataService userDataService, UserService userService, NewsService newsService, NewsCoverService newsCoverService, BookmarkService bookmarkService, OrderService orderService, AddressService addressService) {
        this.words = words;
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

        initStrings("c:/dp/library-back-end/controller/src/main/resources/vocabulary.txt", Pattern.compile("[A-Za-z]+"));
//        initStrings("/media/nikskonda/20B6EA8BB6EA60B0/homeProject/dp/library-back-end/controller/src/main/resources/vocabulary.txt", Pattern.compile("[A-Za-z]+"));


        generateAuthors(COUNT);
        generateGenres(COUNT);
        generateOrg(COUNT);
        generatePH(COUNT);

        generateBooks(COUNT, MIN_ID, MAX_ID, getEngLang());

        generateCountry(COUNT);
        generateState(COUNT, MIN_ID, MAX_ID);
        generateCity(COUNT, MIN_ID, MAX_ID);

        generateDefaultUsers(MIN_ID, MAX_ID);

        generateUser(COUNT-3, MIN_ID, MAX_ID);

        generateNews(COUNT, MIN_ID, MAX_ID, getEngLang());
        generateAddress(COUNT, MIN_ID, MAX_ID);
        generateOrder(COUNT, MIN_ID, MAX_ID);
        generateBookmark(COUNT, MIN_ID, MAX_ID);


        initStrings("c:/dp/library-back-end/controller/src/main/resources/vocabulary_ru.txt", Pattern.compile("[А-Яа-я]+"));
//        initStrings("/media/nikskonda/20B6EA8BB6EA60B0/homeProject/dp/library-back-end/controller/src/main/resources/vocabulary_ru.txt", Pattern.compile("[А-Яа-я]+"));

        generateAuthors(COUNT_RU);
        generateGenres(COUNT_RU);
        generateOrg(COUNT_RU);
        generatePH(COUNT_RU);

        generateBooks(COUNT_RU, MIN_ID_RU, MAX_ID_RU, getRuLang());

        generateCountry(COUNT_RU);
        generateState(COUNT_RU, MIN_ID_RU, MAX_ID_RU);
        generateCity(COUNT_RU, MIN_ID_RU, MAX_ID_RU);

        generateUser(COUNT_RU, MIN_ID_RU, MAX_ID_RU);

        generateNews(COUNT_RU, MIN_ID_RU, MAX_ID_RU, getRuLang());
        generateAddress(COUNT_RU, MIN_ID_RU, MAX_ID_RU);
        generateOrder(COUNT_RU, MIN_ID_RU, MAX_ID_RU);
        generateBookmark(COUNT_RU, MIN_ID_RU, MAX_ID_RU);

        return "Success!";
    }

    private AddressDto getRandomAddressDto(int minId, int maxId){
        return addressService.find((long)generateInt(minId, maxId), "admin");
    }

    private void generateAddress(int count, int minId, int maxId){
        for(int i=0; i<count; i++){
            AddressDto address = new AddressDto();
            address.setCity(getRandomCity(minId, maxId));
            address.setUser(getRandomUserDto(minId, maxId));
            address.setFirstName(address.getUser().getFirstName());
            address.setLastName(address.getUser().getLastName());
            address.setEmail(getRandomWord()+'@'+getRandomWord()+".com");
            address.setPostalCode(generateInt(100000, 999999));
            address.setAddress(generateUrl());
            address.setCity(getRandomCity(minId, maxId));
            addressService.save(address, address.getUser().getUsername());
        }
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
            orderDto.setAddress(getRandomAddressDto(minId, maxId));
            orderDto.setDetails(new HashSet<>());

            int countBook = generateInt(1, 5);
            for (int j = 0; j< countBook; j++){
                OrderDetailDto orderDetailDto = new OrderDetailDto();

                BookDto bookDto = getRandomBook(minId, maxId);
                while (bookDto.getPrice()==null){
                    bookDto = getRandomBook(minId, maxId);
                }
                orderDetailDto.setBook(bookDto);
                orderDetailDto.setCount(generateInt(1,5));
                if (generateInt(0, 10)>5)
                    orderDetailDto.setComment(getRandomWord(generateInt(5, 20)));
                orderDto.getDetails().add(orderDetailDto);
            }

            orderService.save(orderDto, orderDto.getAddress().getUser().getUsername());
        }
    }

    private OrderStatus.Status getOrderStatus(){
        return OrderStatus.Status.values()[generateInt(0, OrderStatus.Status.values().length)];
    }

    private void generateNews(int count, int minId, int maxId, LanguageDto languageDto){
        for (int i=0; i<count; i++){
            NewsDto newsDto = new NewsDto();
            newsDto.setTitle(getRandomWord(generateInt(4, 14)));
            String text = getRandomWord(generateInt(500, 1000));
            if (text.length()>=10000){
                text = text.substring(0, 9999);
            }
            newsDto.setLanguage(languageDto);
            newsDto.setText(text);
            newsDto.setCreator(getRandomUserDataDto(minId, maxId));
            newsDto.setPictureUrl(generateUrl());
            newsDto.setThumbnailUrl(generateUrl());
            newsService.save(newsDto, newsDto.getCreator().getUsername());
        }
    }

    private UserMainDataDto getRandomUserMainDataDto(int minId, int maxId){
        return userMainDataService.find((long)generateInt(minId, maxId));
    }

    private UserDataDto getRandomUserDataDto(int minId, int maxId){
        return userDataService.find((long)generateInt(minId, maxId));
    }

    private UserDto getRandomUserDto(int minId, int maxId){
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
        userDto.setUsername("librarian");
        userDto.setPassword("librarian");
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
        userDto.setUsername("admin");
        userDto.setPassword("admin");
        set = new HashSet<>();
        set.add(roleList.get(0));
        set.add(roleList.get(1));
        set.add(roleList.get(2));
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

        List<RoleDto> roleList = generateRole(count);

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
        RoleDto roleDto = new RoleDto();
        roleDto.setAuthority("USER");
        list.add(roleDto);
        roleDto = new RoleDto();
        roleDto.setAuthority("ADMIN");
        list.add(roleDto);
        roleDto = new RoleDto();
        roleDto.setAuthority("LIBRARIAN");
        list.add(roleDto);
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

    private void generateCity(int count, int minId, int maxId){
        List<String> strings = new ArrayList<>(words);
        for (int i=0; i<count; i++){
            CityDto cityDto = new CityDto();
            String str = getRandomWord(strings);
            cityDto.setName(str.substring(0, 1).toUpperCase() + str.substring(1));
            cityDto.setState(getRandomState(minId, maxId));
            cityService.save(cityDto);
        }
    }

    private StateDto getRandomState(int minId, int maxId){
        return stateService.find(generateLong(minId, maxId));
    }

    private void generateState(int count, int minId, int maxId){
        List<String> strings = new ArrayList<>(words);
        for (int i=0; i<count; i++){
            StateDto stateDto = new StateDto();
            String str = getRandomWord(strings);
            stateDto.setName(str.substring(0, 1).toUpperCase() + str.substring(1));
            stateDto.setCountry(getRandomCountry(minId, maxId));
            stateService.save(stateDto);
        }
    }

    private CountryDto getRandomCountry(int minId, int maxId){
        return countryService.find(generateLong(minId, maxId));
    }

    private void generateCountry(int count){
        List<String> strings = new ArrayList<>(words);
        for (int i=0; i<count; i++){
            CountryDto countryDto = new CountryDto();
            String str = getRandomWord(strings);
            countryDto.setName(str.substring(0, 1).toUpperCase() + str.substring(1));
            countryService.save(countryDto);
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
        String text = getRandomWord(generateInt(200, 300));
        if (text.length()>=3000){
            text = text.substring(0, 2999);
        }
        bookDto.setDescription(text);
        bookDto.setAuthors(getRandomAuthorSet(minId, maxId, generateInt(1, 3)));
        if (generateInt(0, 10)>8){
            bookDto.setTranslators(getRandomAuthorSet(minId, maxId, generateInt(1, 3)));
        }
        bookDto.setGenres(getRandomGenreSet(minId, maxId, generateInt(2, 5)));
        bookDto.setAgeRestriction("+"+generateInt(14, 22));
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
        bookDto.setStatus(getStatus());
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
        bookDto.setPictureUrl(generateUrl());
        bookDto.setThumbnailUrl(generateUrl());
        if (generateInt(0, 10)>6){
            bookDto.setPdfUrl(generateUrl());
        }
        if (generateInt(0, 10)>6){
            bookDto.setPrice(generateBigDecimal(0, 40));
        }
        if (generateInt(0, 10)>6){
            bookDto.setIsbn(generateInt(1000000, Integer.MAX_VALUE)+"");
        }
        return bookDto;
    }

    private Book.Type getType(){
        return Book.Type.values()[generateInt(0, Book.Type.values().length)];
    }

    private Book.Status getStatus(){
        return Book.Status.values()[generateInt(0, Book.Status.values().length)];
    }

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
        List<String> strings = new ArrayList<>(words);
        for (int i=0; i<count; i++){
            AuthorDto authorDto = new AuthorDto();
            String str = getRandomWord();
            authorDto.setFirstName(str.substring(0, 1).toUpperCase() + str.substring(1));
            str = getRandomWord();
            authorDto.setLastName(str.substring(0, 1).toUpperCase() + str.substring(1));
            if (generateInt(0, 10)>5){
                String text = getRandomWord(generateInt(150, 300));
                if (text.length()>=3000){
                    text = text.substring(0, 2999);
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
        return languageService.findByTag("US_en");
    }

    private LanguageDto getRuLang(){
        return languageService.findByTag("RU_ru");
    }

    private void generateLang(){
        LanguageDto dto = new LanguageDto();
        dto.setName("English");
        dto.setTag("US_en");
        languageService.save(dto);
        dto.setName("Руский");
        dto.setTag("RU_ru");
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
