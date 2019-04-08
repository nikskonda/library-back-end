package by.bntu.fitr.model.news;

import by.bntu.fitr.model.user.User;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NewsCover.class)
public abstract class NewsCover_ extends by.bntu.fitr.model.BaseEntity_ {

	public static volatile SingularAttribute<NewsCover, User> creator;
	public static volatile SingularAttribute<NewsCover, LocalDateTime> modificationDate;
	public static volatile SingularAttribute<NewsCover, String> title;
	public static volatile SingularAttribute<NewsCover, LocalDateTime> creationDate;
	public static volatile SingularAttribute<NewsCover, String> thumbnailUrl;

	public static final String CREATOR = "creator";
	public static final String MODIFICATION_DATE = "modificationDate";
	public static final String TITLE = "title";
	public static final String CREATION_DATE = "creationDate";
	public static final String THUMBNAIL_URL = "thumbnailUrl";

}

