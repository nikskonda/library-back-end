package by.bntu.fitr.model.book;

import by.bntu.fitr.model.user.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Bookmark.class)
public abstract class Bookmark_ extends by.bntu.fitr.model.BaseEntity_ {

	public static volatile SingularAttribute<Bookmark, Book> book;
	public static volatile SingularAttribute<Bookmark, Language> language;
	public static volatile SingularAttribute<Bookmark, User> user;

	public static final String BOOK = "book";
	public static final String LANGUAGE = "language";
	public static final String USER = "user";

}

