package by.bntu.fitr.model.news;

import by.bntu.fitr.model.user.User;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(News.class)
public abstract class News_ extends by.bntu.fitr.model.BaseEntity_ {

	public static volatile SingularAttribute<News, User> creator;
	public static volatile SingularAttribute<News, LocalDateTime> modificationDate;
	public static volatile SingularAttribute<News, String> pictureUrl;
	public static volatile SingularAttribute<News, String> text;
	public static volatile SingularAttribute<News, String> title;
	public static volatile SingularAttribute<News, LocalDateTime> creationDate;
	public static volatile SingularAttribute<News, String> thumbnailUrl;

	public static final String CREATOR = "creator";
	public static final String MODIFICATION_DATE = "modificationDate";
	public static final String PICTURE_URL = "pictureUrl";
	public static final String TEXT = "text";
	public static final String TITLE = "title";
	public static final String CREATION_DATE = "creationDate";
	public static final String THUMBNAIL_URL = "thumbnailUrl";

}

