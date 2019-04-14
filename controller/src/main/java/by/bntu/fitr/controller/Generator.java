package by.bntu.fitr.controller;

import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.book.BookDto;
import by.bntu.fitr.dto.book.GenreDto;
import by.bntu.fitr.dto.book.LanguageDto;
import by.bntu.fitr.dto.book.OrganizationDto;
import by.bntu.fitr.dto.book.PublishingHouseDto;
import by.bntu.fitr.model.book.Book;
import by.bntu.fitr.model.book.Genre;
import by.bntu.fitr.model.book.Language;
import by.bntu.fitr.service.book.AuthorService;
import by.bntu.fitr.service.book.BookCoverService;
import by.bntu.fitr.service.book.BookService;
import by.bntu.fitr.service.book.GenreService;
import by.bntu.fitr.service.book.LanguageService;
import by.bntu.fitr.service.book.OrganizationService;
import by.bntu.fitr.service.book.PublishingHouseService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final static int MIN_ID = 1;
    private final static int MAX_ID = 100;

    @Autowired
    public Generator(BookService bookService, BookCoverService bookCoverService, OrganizationService organizationService, LanguageService languageService, PublishingHouseService publishingHouseService, GenreService genreService, AuthorService authorService) {
        this.bookService = bookService;
        this.bookCoverService = bookCoverService;
        this.organizationService = organizationService;
        this.languageService = languageService;
        this.publishingHouseService = publishingHouseService;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    @GetMapping
    public String generate() {
        initStrings();
        generateAuthors(MAX_ID);
        generateGenres(MAX_ID);
        generateOrg(MAX_ID);
        generatePH(MAX_ID);
        generateLang();


        generateBooks(MAX_ID);
        return "Success!";
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

    private BookDto generateBook(){
        BookDto bookDto = new BookDto();
        bookDto.setTitle(getRandomWord(generateInt(1,6)));
        bookDto.setDescription(getRandomWord(generateInt(15, 50)));
        bookDto.setAuthor(getRandomAuthorSet(MIN_ID, MAX_ID, generateInt(1, 3)));
        if (generateInt(0, 10)>8){
            bookDto.setTranslator(getRandomAuthorSet(MIN_ID, MAX_ID, generateInt(1, 3)));
        }
        bookDto.setGenres(getRandomGenreSet(1, MAX_ID, generateInt(2, 5)));
        bookDto.setAgeRestriction("+"+generateInt(14, 22));
        if (generateInt(0, 10)>6){
            bookDto.setImporter(getRandomOrg(MIN_ID, MAX_ID));
        }
        if (generateInt(0, 10)>5){
            bookDto.setProducer(getRandomOrg(MIN_ID, MAX_ID));
        }
        bookDto.setLanguage(getEngLang());
        bookDto.setPages(generateInt(0, 500));
        if (generateInt(0, 10)>3){
            bookDto.setPublishingHouse(getRandomPH(MIN_ID, MAX_ID));
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

    private void generateBooks(int count){
        for (int i=0; i<count; i++){
            bookService.save(generateBook());
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
                authorDto.setDescription(getRandomWord(generateInt(10, 50)));
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

    private void initStrings() {
        Set<String> newWords = new HashSet<>();
        String text = readFromFile("/media/nikskonda/20B6EA8BB6EA60B0/homeProject/dp/library-back-end/controller/src/main/resources/vocabulary.txt");
        Pattern p = Pattern.compile("[A-Za-z]+");
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
