package by.bntu.fitr.model.user;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends by.bntu.fitr.model.BaseEntity_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Boolean> credentialsNonExpired;
	public static volatile SingularAttribute<User, Boolean> accountNonExpired;
	public static volatile SetAttribute<User, Role> authorities;
	public static volatile SingularAttribute<User, Boolean> enabled;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, Boolean> accountNonLocked;

	public static final String PASSWORD = "password";
	public static final String CREDENTIALS_NON_EXPIRED = "credentialsNonExpired";
	public static final String ACCOUNT_NON_EXPIRED = "accountNonExpired";
	public static final String AUTHORITIES = "authorities";
	public static final String ENABLED = "enabled";
	public static final String USERNAME = "username";
	public static final String ACCOUNT_NON_LOCKED = "accountNonLocked";

}

