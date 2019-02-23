package scratches.dddbook.author;

import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.Wither;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Rashidi Zin
 */
@Entity
@Value(staticConstructor = "of")
public class Author {

    @Id
    @GeneratedValue
    @Wither(AccessLevel.PRIVATE)
    private Long id;

    private String firstName;

    private String lastName;

    private Status status;

    public enum Status {

        ACTIVE,

        INACTIVE

    }
}
