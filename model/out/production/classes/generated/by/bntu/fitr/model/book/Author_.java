package by.bntu.fitr.model.book;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Author.class)
public abstract class Author_ extends by.bntu.fitr.model.BaseEntity_ {

	public static volatile SingularAttribute<Author, String> firstName;
	public static volatile SingularAttribute<Author, String> lastName;
	public static volatile SingularAttribute<Author, String> wikiLink;
	public static volatile SingularAttribute<Author, String> description;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String WIKI_LINK = "wikiLink";
	public static final String DESCRIPTION = "description";

}

