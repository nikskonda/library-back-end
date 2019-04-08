package by.bntu.fitr.model.book;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Genre.class)
public abstract class Genre_ extends by.bntu.fitr.model.BaseEntity_ {

	public static volatile SingularAttribute<Genre, String> name;
	public static volatile SingularAttribute<Genre, Language> language;

	public static final String NAME = "name";
	public static final String LANGUAGE = "language";

}

