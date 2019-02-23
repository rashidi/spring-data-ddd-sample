package scratches.dddbook.book;

import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Rashidi Zin
 */
@Entity
@Value(staticConstructor = "of")
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private Long authorId;

    private String title;
}
