package scratches.dddbook.author;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.transaction.annotation.Transactional;
import scratches.dddbook.book.Book;

/**
 * @author Rashidi Zin
 */
@RepositoryEventHandler
@AllArgsConstructor
public class AuthorRepositoryEventHandler {

    AuthorRepository repository;

    @HandleBeforeCreate
    @Transactional(readOnly = true)
    @SuppressWarnings("unused")
    public void verifyAuthorStatus(Book book) {
        repository.findByIdAndStatus(book.getAuthorId(), Author.Status.ACTIVE)
                .orElseThrow(() -> new IllegalArgumentException("author must be active"));
    }

}
