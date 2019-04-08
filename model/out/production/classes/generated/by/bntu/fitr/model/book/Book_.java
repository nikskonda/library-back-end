package by.bntu.fitr.model.book;

import by.bntu.fitr.model.book.Book.Status;
import by.bntu.fitr.model.book.Book.Type;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Book.class)
public abstract class Book_ extends by.bntu.fitr.model.BaseEntity_ {

	public static volatile SingularAttribute<Book, Organization> importer;
	public static volatile SingularAttribute<Book, Integer> year;
	public static volatile SetAttribute<Book, Author> author;
	public static volatile SetAttribute<Book, Author> translator;
	public static volatile SingularAttribute<Book, String> pictureUrl;
	public static volatile SingularAttribute<Book, Integer> rating;
	public static volatile SingularAttribute<Book, String> description;
	public static volatile SingularAttribute<Book, Integer> weight;
	public static volatile SingularAttribute<Book, PublishingHouse> publishingHouse;
	public static volatile SingularAttribute<Book, Language> language;
	public static volatile SingularAttribute<Book, String> title;
	public static volatile SingularAttribute<Book, Type> type;
	public static volatile SingularAttribute<Book, String> pdfUrl;
	public static volatile SingularAttribute<Book, Integer> pages;
	public static volatile SingularAttribute<Book, String> size;
	public static volatile SingularAttribute<Book, String> ISBN;
	public static volatile SetAttribute<Book, Genre> genres;
	public static volatile SingularAttribute<Book, BigDecimal> price;
	public static volatile SingularAttribute<Book, String> ageRestriction;
	public static volatile SingularAttribute<Book, Organization> producer;
	public static volatile SingularAttribute<Book, Status> status;
	public static volatile SingularAttribute<Book, String> thumbnailUrl;

	public static final String IMPORTER = "importer";
	public static final String YEAR = "year";
	public static final String AUTHOR = "author";
	public static final String TRANSLATOR = "translator";
	public static final String PICTURE_URL = "pictureUrl";
	public static final String RATING = "rating";
	public static final String DESCRIPTION = "description";
	public static final String WEIGHT = "weight";
	public static final String PUBLISHING_HOUSE = "publishingHouse";
	public static final String LANGUAGE = "language";
	public static final String TITLE = "title";
	public static final String TYPE = "type";
	public static final String PDF_URL = "pdfUrl";
	public static final String PAGES = "pages";
	public static final String SIZE = "size";
	public static final String I_SB_N = "ISBN";
	public static final String GENRES = "genres";
	public static final String PRICE = "price";
	public static final String AGE_RESTRICTION = "ageRestriction";
	public static final String PRODUCER = "producer";
	public static final String STATUS = "status";
	public static final String THUMBNAIL_URL = "thumbnailUrl";

}

