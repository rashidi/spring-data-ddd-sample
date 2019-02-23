package scratches.dddbook.book;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.util.Assert;

/**
 * @author Rashidi Zin
 */
@RepositoryEventHandler
public class BookRepositoryEventHandler {

    @HandleBeforeCreate
    public void verifyBookTitle(Book book) {
        Assert.hasText(book.getTitle(), "book title is cannot be blank");
    }

}
