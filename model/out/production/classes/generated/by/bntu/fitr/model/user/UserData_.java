package by.bntu.fitr.model.user;

import by.bntu.fitr.model.user.util.City;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserData.class)
public abstract class UserData_ extends by.bntu.fitr.model.user.User_ {

	public static volatile SingularAttribute<UserData, String> firstName;
	public static volatile SingularAttribute<UserData, String> lastName;
	public static volatile SingularAttribute<UserData, String> address;
	public static volatile SingularAttribute<UserData, City> city;
	public static volatile SingularAttribute<UserData, Integer> postalCode;
	public static volatile SingularAttribute<UserData, String> email;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String ADDRESS = "address";
	public static final String CITY = "city";
	public static final String POSTAL_CODE = "postalCode";
	public static final String EMAIL = "email";

}

