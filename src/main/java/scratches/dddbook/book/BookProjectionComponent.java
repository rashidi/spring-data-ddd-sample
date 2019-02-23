package scratches.dddbook.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import scratches.dddbook.author.Author;
import scratches.dddbook.author.AuthorRepository;

/**
 * @author Rashidi Zin
 */
@Component
@AllArgsConstructor
public class BookProjectionComponent {

    private AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

}
