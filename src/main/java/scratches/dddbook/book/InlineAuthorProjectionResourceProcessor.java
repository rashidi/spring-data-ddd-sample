package scratches.dddbook.book;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;
import scratches.dddbook.author.Author;

/**
 * @author Rashidi Zin
 */
@Component
@AllArgsConstructor
public class InlineAuthorProjectionResourceProcessor implements ResourceProcessor<Resource<InlineAuthorProjection>> {

    private RepositoryEntityLinks entityLinks;

    @Override
    public Resource<InlineAuthorProjection> process(Resource<InlineAuthorProjection> resource) {
        Author author = resource.getContent().getAuthor();

        resource.add(
                entityLinks.linkForSingleResource(Author.class, author.getId()).withRel("author")
        );

        return resource;
    }

}
