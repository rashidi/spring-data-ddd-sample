package scratches.dddbook.book;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import scratches.dddbook.author.Author;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static scratches.dddbook.author.Author.Status.ACTIVE;
import static scratches.dddbook.author.Author.Status.INACTIVE;

/**
 * @author Rashidi Zin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
public class BookRepositoryRestTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager em;

    @Test
    public void createBook() throws Exception {
        Long authorId = em.persistAndGetId(Author.of(null, "Rudyard", "Kipling", ACTIVE), Long.class);

        JSONObject request = new JSONObject();

        request.put("authorId", authorId);
        request.put("title", "The Jungle Book");

        String bookUri = mvc.perform(
                post("/books").content(request.toString())
        )
                .andExpect(status().isCreated())
                .andReturn().getResponse().getHeader(LOCATION);

        mvc.perform(
                get(bookUri)
                        .param("projection", "inlineAuthor")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("The Jungle Book")))
                .andExpect(jsonPath("$.author.firstName", is("Rudyard")))
                .andExpect(jsonPath("$.author.lastName", is("Kipling")))
                .andExpect(jsonPath("$._links.author.href", containsString("/authors/" + authorId)));
    }

    @Test
    public void createBookWithInactiveAuthor() throws Exception {
        Long authorId = em.persistAndGetId(Author.of(null, "William", "Shakespeare", INACTIVE), Long.class);

        JSONObject request = new JSONObject();

        request.put("authorId", authorId);
        request.put("title", "The Jungle Book");

        mvc.perform(
                post("/books").content(request.toString())
        )
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("author must be active")));
    }

    @Test
    public void createBookWithoutTitle() throws Exception {
        Long authorId = em.persistAndGetId(Author.of(null, "Robert", "Frost", ACTIVE), Long.class);

        JSONObject request = new JSONObject();

        request.put("authorId", authorId);
        request.put("title", "");

        mvc.perform(
                post("/books").content(request.toString())
        )
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("book title is cannot be blank")));
    }

}