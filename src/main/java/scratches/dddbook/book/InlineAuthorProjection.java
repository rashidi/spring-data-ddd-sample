package scratches.dddbook.book;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import scratches.dddbook.author.Author;

/**
 * @author Rashidi Zin
 */
@Projection(name = "inlineAuthor", types = {Book.class})
public interface InlineAuthorProjection {

    String getTitle();

    @Value("#{@bookProjectionComponent.getAuthor(target.authorId)}")
    Author getAuthor();

}
