package by.bntu.fitr.model.book;

import by.bntu.fitr.model.book.BookCover.Status;
import by.bntu.fitr.model.book.BookCover.Type;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BookCover.class)
public abstract class BookCover_ extends by.bntu.fitr.model.BaseEntity_ {

	public static volatile SingularAttribute<BookCover, Integer> year;
	public static volatile SetAttribute<BookCover, Genre> genres;
	public static volatile SetAttribute<BookCover, Author> author;
	public static volatile SingularAttribute<BookCover, BigDecimal> price;
	public static volatile SetAttribute<BookCover, Author> translator;
	public static volatile SingularAttribute<BookCover, Integer> rating;
	public static volatile SingularAttribute<BookCover, String> ageRestriction;
	public static volatile SingularAttribute<BookCover, Language> language;
	public static volatile SingularAttribute<BookCover, String> title;
	public static volatile SingularAttribute<BookCover, Type> type;
	public static volatile SingularAttribute<BookCover, Status> status;
	public static volatile SingularAttribute<BookCover, String> thumbnailUrl;

	public static final String YEAR = "year";
	public static final String GENRES = "genres";
	public static final String AUTHOR = "author";
	public static final String PRICE = "price";
	public static final String TRANSLATOR = "translator";
	public static final String RATING = "rating";
	public static final String AGE_RESTRICTION = "ageRestriction";
	public static final String LANGUAGE = "language";
	public static final String TITLE = "title";
	public static final String TYPE = "type";
	public static final String STATUS = "status";
	public static final String THUMBNAIL_URL = "thumbnailUrl";

}

