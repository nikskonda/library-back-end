package by.bntu.fitr.model.book;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PublishingHouse.class)
public abstract class PublishingHouse_ extends by.bntu.fitr.model.BaseEntity_ {

	public static volatile SingularAttribute<PublishingHouse, String> description;
	public static volatile SingularAttribute<PublishingHouse, String> siteLink;
	public static volatile SingularAttribute<PublishingHouse, String> title;
	public static volatile SingularAttribute<PublishingHouse, String> logoUrl;

	public static final String DESCRIPTION = "description";
	public static final String SITE_LINK = "siteLink";
	public static final String TITLE = "title";
	public static final String LOGO_URL = "logoUrl";

}

