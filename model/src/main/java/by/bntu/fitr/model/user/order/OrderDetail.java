package by.bntu.fitr.model.user.order;

import by.bntu.fitr.model.BaseEntity;
import by.bntu.fitr.model.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "OrderDetail")
@Table(name = "order_detail", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "order_detail_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "order_detail_id"))
@DynamicUpdate
public class OrderDetail extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE }, fetch=FetchType.LAZY)
    @JoinColumn(name = "order_id")
//    @JsonBackReference
    private Order order;

    @ManyToOne(cascade = {CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

//    @Column(name = "order_detail_price", nullable = false)
//    private BigDecimal price;

    @Column(name = "order_detail_comment", length = 500)
    private String comment;

    @Column(name = "order_detail_count")
    private Integer count;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "book=" + book +
                ", comment='" + comment + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(book, that.book) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), book, comment, count);
    }
}
