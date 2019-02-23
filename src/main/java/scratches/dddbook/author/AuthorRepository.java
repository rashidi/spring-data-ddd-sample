package scratches.dddbook.author;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Rashidi Zin
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByIdAndStatus(Long id, Author.Status status);

}
