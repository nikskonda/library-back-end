package by.bntu.fitr.model.user;

import by.bntu.fitr.model.book.Book;
import by.bntu.fitr.model.user.Order.Status;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ extends by.bntu.fitr.model.BaseEntity_ {

	public static volatile SingularAttribute<Order, LocalDateTime> dateTime;
	public static volatile SingularAttribute<Order, Book> book;
	public static volatile SingularAttribute<Order, String> comment;
	public static volatile SingularAttribute<Order, UserData> user;
	public static volatile SingularAttribute<Order, LocalDateTime> modification;
	public static volatile SingularAttribute<Order, Status> status;

	public static final String DATE_TIME = "dateTime";
	public static final String BOOK = "book";
	public static final String COMMENT = "comment";
	public static final String USER = "user";
	public static final String MODIFICATION = "modification";
	public static final String STATUS = "status";

}

