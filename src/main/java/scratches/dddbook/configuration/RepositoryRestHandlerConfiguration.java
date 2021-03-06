package scratches.dddbook.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import scratches.dddbook.author.AuthorRepository;
import scratches.dddbook.author.AuthorRepositoryEventHandler;
import scratches.dddbook.book.BookRepositoryEventHandler;

/**
 * @author Rashidi Zin
 */
@Configuration
public class RepositoryRestHandlerConfiguration {

    @Bean
    public AuthorRepositoryEventHandler authorRepositoryEventHandler(AuthorRepository repository) {
        return new AuthorRepositoryEventHandler(repository);
    }

    @Bean
    public BookRepositoryEventHandler bookRepositoryEventHandler() {
        return new BookRepositoryEventHandler();
    }

}
