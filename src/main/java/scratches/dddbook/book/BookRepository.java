package scratches.dddbook.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Rashidi Zin
 */
@RepositoryRestResource(excerptProjection = InlineAuthorProjection.class)
public interface BookRepository extends JpaRepository<Book, Long> {
}
